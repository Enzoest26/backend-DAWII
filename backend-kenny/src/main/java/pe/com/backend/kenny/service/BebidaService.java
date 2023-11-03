package pe.com.backend.kenny.service;

import java.util.List;

import pe.com.backend.kenny.model.Bebida;
import pe.com.backend.kenny.model.request.BebidaActualizarRequest;
import pe.com.backend.kenny.model.request.BebidaRegistrarRequest;
import pe.com.backend.kenny.model.response.BaseResponse;

public interface BebidaService {

	//obtener todas las bebidas con estado 0(inactivo) y 1(activo)
	public List<Bebida> listadoTodasBebidas();
	
	//obtener todas las bebidas con estado 1(activo)
	public List<Bebida> listadoBebidasEstadoActivo();
	
	public Bebida registrarBebida(BebidaRegistrarRequest bebida);
	
	public Bebida actualizarBebida(String idBebida, BebidaActualizarRequest request);
	
	public BaseResponse eliminarBebida(String idBebida);
}
