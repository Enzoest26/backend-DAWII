package pe.com.backend.kenny.service;

import java.util.List;

import pe.com.backend.kenny.model.Usuario;
import pe.com.backend.kenny.model.response.BaseResponse;

public interface IUsuarioService {
	
	public List<Usuario> listarUsuarios();
	
	public Usuario buscarPorId(Integer id);
	
	public Usuario registrarUsuario(Usuario usuario);
	
	public Usuario actualizarUsuario(Usuario usuario);
	
	public List<Usuario> buscarClientePorEmail(String email);
	
	public BaseResponse eliminarUsuario(Integer id);
}
