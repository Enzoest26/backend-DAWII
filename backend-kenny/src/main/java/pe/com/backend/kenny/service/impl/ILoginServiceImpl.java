package pe.com.backend.kenny.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.backend.kenny.config.service.JwtService;
import pe.com.backend.kenny.exception.LoginFailedException;
import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.model.Usuario;
import pe.com.backend.kenny.model.request.Login;
import pe.com.backend.kenny.model.response.LoginResponse;
import pe.com.backend.kenny.service.IClienteService;
import pe.com.backend.kenny.service.ILoginService;
import pe.com.backend.kenny.service.IUsuarioService;

@Service
@RequiredArgsConstructor
public class ILoginServiceImpl implements ILoginService{

    private @Autowired PasswordEncoder passwordEncoder;
	private @Autowired IClienteService clienteService;
	private @Autowired IUsuarioService usuarioService;
	@Qualifier("userDetailsServiceImpl")
	private @Autowired UserDetailsService userDetailsService;
	private @Autowired JwtService jwtService;

    @Override
    public LoginResponse verificarIngreso(Login login) 
    {
    	
        List<Cliente> cliente = this.clienteService.buscarClientePorEmail(login.getIdUsuario());
        if(!cliente.isEmpty())
        {
        	if(passwordEncoder.matches(login.getClave(), cliente.get(0).getClave_cliente()))
            {
        		//UserDetails user = this.userDetailsService.loadUserByUsername(cliente.get(0).getEmailCliente());
        		//String token = jwtService.obtenerToken(new HashMap<>(), user);
                return LoginResponse.builder()
                		.token("Sin Token")
                		.rol("USER")
                		.build();
            }
        }
        
        List<Usuario> usuario = this.usuarioService.buscarClientePorEmail(login.getIdUsuario());
        if(!usuario.isEmpty())
        {
        	if(passwordEncoder.matches(login.getClave(), usuario.get(0).getClave()))
            {
        		UserDetails user = this.userDetailsService.loadUserByUsername(usuario.get(0).getCorreo());
                		String token = jwtService.obtenerToken(new HashMap<>(), user);
                        return LoginResponse.builder()
                        		.token(token)
                        		.rol(usuario.get(0).getTipoUsuario().getDescripcion())
                        		.build();
            }
        }
        throw new LoginFailedException("Usuario y/o contraseña incorrectos");
    }
    
}
