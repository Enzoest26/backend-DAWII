package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.Usuario;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.repository.IUsuarioRepository;
import pe.com.backend.kenny.service.ITipoUsuarioService;
import pe.com.backend.kenny.service.IUsuarioService;

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
	public BaseResponse actualizarUsuario(Usuario usuario) {
		if(this.usuarioRepository.existsById(usuario.getIdUsuario()))
		{
			usuario.setTipoUsuario(this.tipoUsuarioService.buscarTipoUsuarioPorID(usuario.getIdTipoUsuario()));
			usuario.setClave(this.passwordEncoder.encode(usuario.getClave()));
			this.usuarioRepository.save(usuario);
			return BaseResponse.builder()
					.codRespuesta("0")
					.msjRespuesta("Actualización Exitosa")
					.build();
		}
		return BaseResponse.builder()
				.codRespuesta("1")
				.msjRespuesta("Usuario no existe")
				.build();
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
						.codRespuesta("0")
						.msjRespuesta("Eliminación Exitosa")
						.build();
			}
			
		}
		return BaseResponse.builder()
				.codRespuesta("1")
				.msjRespuesta("Usuario no existe")
				.build();
	}

}
