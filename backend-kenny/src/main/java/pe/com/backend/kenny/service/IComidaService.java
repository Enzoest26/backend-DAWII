package pe.com.backend.kenny.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import pe.com.backend.kenny.model.Comida;
import pe.com.backend.kenny.model.dto.DtoComidaCatalogo;
import pe.com.backend.kenny.model.request.ComidaRegistrarRequest;
import pe.com.backend.kenny.model.response.BaseResponse;

public interface IComidaService {
	public List<Comida>listarComida();
	public Comida obtenerComida(String idComida);
	public Comida insertarComida(ComidaRegistrarRequest objComida);
	public Comida actualizarComida(Comida objComida);
	public BaseResponse eliminarComida(String idComida);
	public Page<Comida> listarComidaActivosPaginado(Integer pagina);
	public Map<String, Object> listarComidasCatalogo(Integer pagina);
}
