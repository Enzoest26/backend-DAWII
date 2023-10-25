package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.model.request.Login;
import pe.com.backend.kenny.service.IClienteService;
import pe.com.backend.kenny.service.ILoginService;

@Service
public class ILoginServiceImpl implements ILoginService{

    private @Autowired PasswordEncoder passwordEncoder;
	private @Autowired IClienteService clienteService;

    @Override
    public boolean verificarIngreso(Login login) {
        List<Cliente> cliente =
        this.clienteService.buscarClientePorEmail(login.getIdUsuario());
        if(cliente.isEmpty()){
            return false;
        }
        if(!passwordEncoder.matches(login.getClave(), cliente.get(0).getClave_cliente())){
            return false;
        }
        return true;
    }
    
}
