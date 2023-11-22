package pe.com.backend.kenny.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import pe.com.backend.kenny.model.Boleta;
import pe.com.backend.kenny.model.request.OrdenCompraRequest;
import pe.com.backend.kenny.model.response.BaseResponse;

public interface IBoletaService {
	
	public BaseResponse registrarOrden(OrdenCompraRequest ordenCompraRequest);
	
	List<Boleta> listarBoletas();
	
	List<Boleta> listarPorFiltros(LocalDate fechaInicio, LocalDate fechaFin, Integer idCliente);
	
	public void exportarExcel(LocalDate fechaInicio, LocalDate fechaFin, Integer idCliente, HttpServletResponse response) throws IOException;
}
