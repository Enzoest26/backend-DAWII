package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.model.Usuario;
import pe.com.backend.kenny.model.request.Login;
import pe.com.backend.kenny.service.IClienteService;
import pe.com.backend.kenny.service.ILoginService;
import pe.com.backend.kenny.service.IUsuarioService;

@Service
public class ILoginServiceImpl implements ILoginService{

    private @Autowired PasswordEncoder passwordEncoder;
	private @Autowired IClienteService clienteService;
	private @Autowired IUsuarioService usuarioService;

    @Override
    public boolean verificarIngreso(Login login) 
    {
        List<Cliente> cliente = this.clienteService.buscarClientePorEmail(login.getIdUsuario());
        if(!cliente.isEmpty())
        {
        	if(passwordEncoder.matches(login.getClave(), cliente.get(0).getClave_cliente()))
            {
                return true;
            }
        }
        
        List<Usuario> usuario = this.usuarioService.buscarClientePorEmail(login.getIdUsuario());
        if(!usuario.isEmpty())
        {
        	if(passwordEncoder.matches(login.getClave(), usuario.get(0).getClave()))
            {
                return true;
            }
        }
        return false;
    }
    
}
