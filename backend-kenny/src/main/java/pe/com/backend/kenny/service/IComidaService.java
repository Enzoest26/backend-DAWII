package pe.com.backend.kenny.service;

import java.util.List;

import pe.com.backend.kenny.model.Comida;
import pe.com.backend.kenny.model.request.ComidaRegistrarRequest;
import pe.com.backend.kenny.model.response.BaseResponse;

public interface IComidaService {
	public List<Comida>listarComida();
	public Comida obtenerComida(String idComida);
	public Comida insertarComida(ComidaRegistrarRequest objComida);
	public BaseResponse actualizarComida(Comida objComida);
	public BaseResponse eliminarComida(String idComida);

}
