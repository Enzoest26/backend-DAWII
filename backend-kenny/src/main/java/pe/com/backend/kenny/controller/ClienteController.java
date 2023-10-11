package pe.com.backend.kenny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.service.IClienteService;

@RestController
// @RequestMapping("/cliente")
public class ClienteController {
    
    private @Autowired IClienteService clienteService;

    @GetMapping("/cliente")
    public List<Cliente> listarClientes(){
        return clienteService.listarCliente();
    }

    @PostMapping("/registrar")
    @ResponseBody
    public Cliente registrarCliente(@RequestBody Cliente cliente)
	{
		return clienteService.registrarCliente(cliente);
	}


}
