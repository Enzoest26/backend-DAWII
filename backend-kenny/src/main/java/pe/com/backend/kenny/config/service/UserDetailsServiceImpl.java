package pe.com.backend.kenny.config.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.model.Usuario;
import pe.com.backend.kenny.repository.IClienteRepository;
import pe.com.backend.kenny.repository.IUsuarioRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final IUsuarioRepository usuarioRepository;
	private final IClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		//Buscar Usuario
		List<Usuario> usuario = usuarioRepository.findByCorreo(username);
		if(!usuario.isEmpty())
		{
			return User.builder()
					.username(username)
					.password(usuario.get(0).getClave())
					.roles(usuario.get(0).getTipoUsuario().getDescripcion())
					.build();
		}
		List<Cliente> cliente = clienteRepository.findByEmailCliente(username);
		if(!cliente.isEmpty())
		{
			return User.builder()
					.username(username)
					.password(cliente.get(0).getClave_cliente())
					.roles("USER") //Rol de Cliente
					.build();
		}
		throw new UsernameNotFoundException("Usuario no existente");
	}

}
