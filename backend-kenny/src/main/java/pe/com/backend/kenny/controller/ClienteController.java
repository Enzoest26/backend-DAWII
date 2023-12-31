package pe.com.backend.kenny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.model.Usuario;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.service.IClienteService;

@RestController
@RequestMapping("/intranet/cliente")
public class ClienteController {
    
    private @Autowired IClienteService clienteService;

    @GetMapping("/buscarClientes")
    public List<Cliente> listarClientes(){
        return clienteService.listarCliente();
    }

    @PostMapping("/registrar")
    public Cliente registrarCliente(@RequestBody Cliente cliente)
	{
		return this.clienteService.registrarCliente(cliente);
	}

    @PutMapping("/actualizar")
    public Cliente actualizarCliente(@RequestBody Cliente cliente){
        return this.clienteService.actualizarCliente(cliente);
    }

    @DeleteMapping("/eliminar/{idcliente}")
	public BaseResponse eliminarCliente(@PathVariable("idcliente") Integer idcliente) {
		return clienteService.eliminarCliente(idcliente);
	}

    @GetMapping("/buscarPorId/{id}")
    public Cliente buscarPorId(@PathVariable Integer id) {
    	return this.clienteService.buscarPorId(id);
    }


}
