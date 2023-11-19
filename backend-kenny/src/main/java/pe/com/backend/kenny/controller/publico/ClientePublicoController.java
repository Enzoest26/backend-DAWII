package pe.com.backend.kenny.controller.publico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.service.IClienteService;

@RestController
@RequestMapping("/publico/cliente")
public class ClientePublicoController {

	@Autowired 
	private IClienteService clienteService;
	
	@PostMapping("/registrar")
    public Cliente registrarCliente(@RequestBody Cliente cliente)
	{
		return this.clienteService.registrarCliente(cliente);
	}
}
