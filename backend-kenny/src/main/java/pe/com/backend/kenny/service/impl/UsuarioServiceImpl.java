package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.exception.ItemNoEncontradoException;
import pe.com.backend.kenny.model.Usuario;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.repository.IUsuarioRepository;
import pe.com.backend.kenny.service.ITipoUsuarioService;
import pe.com.backend.kenny.service.IUsuarioService;
import pe.com.backend.kenny.util.Constantes;

@Service
public class UsuarioServiceImpl implements IUsuarioService
{
	private @Autowired IUsuarioRepository usuarioRepository;
	
	private @Autowired ITipoUsuarioService tipoUsuarioService;
	
	private @Autowired PasswordEncoder passwordEncoder;
	
	@Override
	public List<Usuario> listarUsuarios() {
		return this.usuarioRepository.findAll();
	}

	@Override
	public Usuario registrarUsuario(Usuario usuario) {
		usuario.setTipoUsuario(this.tipoUsuarioService.buscarTipoUsuarioPorID(usuario.getIdTipoUsuario()));
		usuario.setClave(this.passwordEncoder.encode(usuario.getClave()));
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		if(this.usuarioRepository.existsById(usuario.getIdUsuario()))
		{
			usuario.setTipoUsuario(this.tipoUsuarioService.buscarTipoUsuarioPorID(usuario.getIdTipoUsuario()));
			usuario.setClave(this.passwordEncoder.encode(usuario.getClave()));
			return this.usuarioRepository.save(usuario);
			/*
			return BaseResponse.builder()
					.codRespuesta(Constantes.CODIGO_EXITO_ACTUALIZACION)
					.msjRespuesta(Constantes.MENSAJE_EXITO_ACTUALIZACION)
					.build();
					*/
		}
		throw new ItemNoEncontradoException("Usuario no encontrado");
	}

	@Override
	public BaseResponse eliminarUsuario(Integer id) {
		if(this.usuarioRepository.existsById(id))
		{
			Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
			if(usuario != null)
			{
				usuario.setEstado(0);
				this.usuarioRepository.save(usuario);
				return BaseResponse.builder()
						.codRespuesta(Constantes.CODIGO_EXITO_ELIMINACION)
						.msjRespuesta(Constantes.MENSAJE_EXITO_ELIMINACION)
						.build();
			}
			
		}
		throw new ItemNoEncontradoException("Usuario no encontrado");
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
		if(usuario == null)
			throw new ItemNoEncontradoException("Usuario no encontrado");
		return usuario;
	}

}
