package pe.com.backend.kenny.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import pe.com.backend.kenny.model.Boleta;
import pe.com.backend.kenny.model.DetalleBoleta;
import pe.com.backend.kenny.model.request.DetalleCompraRequest;
import pe.com.backend.kenny.model.request.OrdenCompraRequest;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.repository.IBoletaRepository;
import pe.com.backend.kenny.repository.IDetalleBoletaRepository;
import pe.com.backend.kenny.service.BebidaService;
import pe.com.backend.kenny.service.IBoletaService;
import pe.com.backend.kenny.service.IClienteService;
import pe.com.backend.kenny.service.IComidaService;

@Service
public class BoletaServiceImpl implements IBoletaService{
	
	private @Autowired IBoletaRepository boletaRepository;
	private @Autowired IDetalleBoletaRepository detalleBoletaRepository;
	private @Autowired IComidaService comidaService;
	private @Autowired BebidaService bebidaService;
	private @Autowired IClienteService clienteService;

	@Override
	@Transactional
	public BaseResponse registrarOrden(OrdenCompraRequest ordenCompraRequest) 
	{
		Boleta boleta = new Boleta();
		boleta.setFechaBoleta(LocalDate.now());
		boleta.setIdCliente(this.clienteService.buscarPorId(ordenCompraRequest.getIdUsuario()));
		
		Double totalPagar = 0.0;
		
		for (DetalleCompraRequest request : ordenCompraRequest.getDetalleCompra()) {
			totalPagar += request.getPrecio() * request.getCantidad();
		}
		boleta.setTotalMonto(totalPagar);
		Boleta ultimaBoleta = this.boletaRepository.save(boleta);
		List<DetalleBoleta> detalleBoleta = new ArrayList<>();
		ordenCompraRequest.getDetalleCompra().stream().forEach(s ->{
			DetalleBoleta detBoleta = new DetalleBoleta();
			String tipoProducto = s.getIdProducto().substring(0,1);
			if(tipoProducto.equals("B")) {
				detBoleta.setBebida(this.bebidaService.buscarPorId(s.getIdProducto()));
				detBoleta.setCantidadBebida(s.getCantidad());
			}else {
				detBoleta.setComida(this.comidaService.obtenerComida(s.getIdProducto()));
				detBoleta.setCantidadComida(s.getCantidad());
			}
			detBoleta.setImporte(s.getCantidad() * s.getPrecio());
			detBoleta.setPrecio(s.getCantidad() * s.getPrecio());
			detBoleta.setNumBoleta(ultimaBoleta.getNumBoleta());
			detalleBoleta.add(detBoleta);
			//this.detalleBoletaRepository.save(detBoleta);
		});
		this.detalleBoletaRepository.saveAll(detalleBoleta);
		
		return BaseResponse.builder()
				.codRespuesta("0")
				.msjRespuesta("Orden Exitosa").build();
	}

	@Override
	public List<Boleta> listarBoletas() {
		return boletaRepository.findAll();
	}
	
	@Override
	public List<Boleta> listarPorFiltros(LocalDate fechaInicio, LocalDate fechaFin, Integer idCliente) {
		return this.boletaRepository.buscarPorFiltros(fechaInicio, fechaFin, idCliente);
	}

	
	

	@Override
	public void exportarExcel(LocalDate fechaInicio, LocalDate fechaFin, Integer idCliente, HttpServletResponse response) throws IOException {
		String tituloExcel = "Reporte_Ventas";
		response.setContentType("application/octet-stream");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + tituloExcel + ".xlsx");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ServletOutputStream servletOutputStream = response.getOutputStream();
		try {
			SXSSFWorkbook sxssfWorkbook = null;
			XSSFWorkbook workbook = new XSSFWorkbook();
	        workbook.createSheet(tituloExcel);
	        sxssfWorkbook = new SXSSFWorkbook(workbook, SXSSFWorkbook.DEFAULT_WINDOW_SIZE);
	        List<Boleta> datosBoleta = this.listarPorFiltros(fechaInicio, fechaFin, idCliente);
	        
	        this.construirExportacionBoleta(fechaInicio, fechaFin, idCliente, datosBoleta, sxssfWorkbook, workbook);
	        
	        byte[] bytes = byteArrayOutputStream.toByteArray();
			servletOutputStream.write(bytes);
			servletOutputStream.flush();
			
			sxssfWorkbook.write(byteArrayOutputStream);
			
			response.getOutputStream().write(byteArrayOutputStream.toByteArray());
			response.flushBuffer();
			sxssfWorkbook.close();
		} 
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
	}
	
	private void construirExportacionBoleta(LocalDate fechaInicio, LocalDate fechaFin, Integer idCliente, List<Boleta> datosBoleta, SXSSFWorkbook sxssfWorkbook, XSSFWorkbook workbook) {
	
		Sheet hoja = sxssfWorkbook.getSheetAt(0);
		hoja.setDefaultRowHeight((short) 380);
		try {
			
			
			
			Row fila;
			Cell celda;
			hoja.setColumnWidth(0, 2000);
			
			//Estilo cabezera
			CellStyle estiloCab = sxssfWorkbook.createCellStyle();
	        estiloCab.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
	        estiloCab.setVerticalAlignment(VerticalAlignment.CENTER);
	        estiloCab.setBorderTop(BorderStyle.THIN);
	        estiloCab.setBorderRight(BorderStyle.THIN);
	        estiloCab.setBorderBottom(BorderStyle.THIN);
	        estiloCab.setBorderLeft(BorderStyle.THIN);
	        
	        CellStyle estiloNormal = sxssfWorkbook.createCellStyle();
	        estiloNormal.setVerticalAlignment(VerticalAlignment.CENTER);
	        estiloNormal.setBorderTop(BorderStyle.THIN);
	        estiloNormal.setBorderRight(BorderStyle.THIN);
	        estiloNormal.setBorderBottom(BorderStyle.THIN);
	        estiloNormal.setBorderLeft(BorderStyle.THIN);
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
	        
	        
	        hoja.addMergedRegion(new CellRangeAddress(1, 2 , 1, 7));
	        fila = hoja.createRow(0);
	        celda = fila.createCell(1);
	        
	        Font fontBold = workbook.createFont();
	        fontBold.setUnderline(Font.U_SINGLE);
	        fontBold.setBold(true);
	        
	        CellStyle estiloTitulo = workbook.createCellStyle();
	        estiloTitulo.setVerticalAlignment(VerticalAlignment.CENTER);
	        estiloTitulo.setAlignment(HorizontalAlignment.CENTER);
	        estiloTitulo.setFont(fontBold);
	        
	        celda.setCellStyle(estiloTitulo);
	        celda.setCellValue("REPORTE DE VENTAS");
			//Criterios
			fila = hoja.createRow(4);
			
			celda = fila.createCell(1);
			celda.setCellStyle(estiloCab);
			celda.setCellValue("Fecha Inicio");
			
			celda = fila.createCell(2);
			celda.setCellStyle(estiloNormal);
			celda.setCellValue(fechaFin.format(formatter));
			
			celda = fila.createCell(4);
			celda.setCellStyle(estiloCab);
			celda.setCellValue("Fecha Fin");
			
			celda = fila.createCell(5);
			celda.setCellStyle(estiloNormal);
			celda.setCellValue(fechaFin.format(formatter));
			
			
			celda = fila.createCell(7);
			celda.setCellStyle(estiloCab);
			celda.setCellValue("ID Cliente");
			
			celda = fila.createCell(8);
			celda.setCellStyle(estiloNormal);
			celda.setCellValue(idCliente);
			
			
			//Cabezeras
			fila = hoja.createRow(6);
			
			celda = fila.createCell(1);
			celda.setCellStyle(estiloCab);
			celda.setCellValue("Número de Boleta");
			
			celda = fila.createCell(2);
			celda.setCellStyle(estiloCab);
			celda.setCellValue("Fecha");
			
			celda = fila.createCell(3);
			celda.setCellStyle(estiloCab);
			celda.setCellValue("Cliente");
			
			celda = fila.createCell(4);
			celda.setCellStyle(estiloCab);
			celda.setCellValue("Total");
			
			int filaActual = 7;
			//Datos
			for (int i = 0; i < datosBoleta.size(); i++) {
				
				fila = hoja.createRow(filaActual + i);
				
				celda = fila.createCell(1);
				celda.setCellStyle(estiloNormal);
				celda.setCellValue(datosBoleta.get(i).getNumBoleta());
				
				celda = fila.createCell(2);
				celda.setCellStyle(estiloNormal);
				celda.setCellValue(datosBoleta.get(i).getFechaBoleta().format(formatter));
				
				celda = fila.createCell(3);
				celda.setCellStyle(estiloNormal);
				celda.setCellValue(datosBoleta.get(i).getIdCliente().getNombre_cliente());
				
				celda = fila.createCell(4);
				celda.setCellStyle(estiloNormal);
				celda.setCellValue(datosBoleta.get(i).getTotalMonto());
				
				filaActual ++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
	
