package pe.com.backend.kenny.service;

import java.util.List;

import pe.com.backend.kenny.model.Boleta;
import pe.com.backend.kenny.model.request.OrdenCompraRequest;
import pe.com.backend.kenny.model.response.BaseResponse;

public interface IBoletaService {
	
	public BaseResponse registrarOrden(OrdenCompraRequest ordenCompraRequest);
	
	List<Boleta> listarBoletas();
}
