package pe.com.backend.kenny.service;

import java.util.List;

import pe.com.backend.kenny.model.TipoUsuario;

public interface ITipoUsuarioService {
	
	public TipoUsuario buscarTipoUsuarioPorID(Integer id);
	
	public List<TipoUsuario> buscarTodos();
}
