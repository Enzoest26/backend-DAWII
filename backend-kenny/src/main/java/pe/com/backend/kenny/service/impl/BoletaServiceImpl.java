package pe.com.backend.kenny.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void exportarExcel(LocalDate fechaInicio, LocalDate fechaFin, Integer idCliente, HttpServletResponse response) {
		
		
	}
}
	
