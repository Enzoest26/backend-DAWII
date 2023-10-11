package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.repository.IClienteRepository;
import pe.com.backend.kenny.service.IClienteService;

@Service
public class IClienteServiceImpl implements IClienteService{

    private @Autowired IClienteRepository clienteRepo;

    @Override
    public Cliente registrarCliente(Cliente objCliente) {
        // registrar cliente
        return this.clienteRepo.save(objCliente);
    }

    @Override
    public Cliente actualizarCliente(Cliente objCliente) {
        // actualizar cliente
        Cliente objClienteAct = clienteRepo.findById(objCliente.getId_cliente()).orElse(null);
        objClienteAct.setNombre_cliente(objCliente.getNombre_cliente());
        objClienteAct.setApellidos_cliente(objCliente.getApellidos_cliente());
        objClienteAct.setDni_cliente(objCliente.getDni_cliente());
        objClienteAct.setFec_nac_cliente(objCliente.getFec_nac_cliente());
        objClienteAct.setEdad_cliente(objCliente.getEdad_cliente());
        objClienteAct.setCorreo(objCliente.getCorreo());
        objClienteAct.setClave(objCliente.getClave());
        objClienteAct.setEstado_cliente(objCliente.getEstado_cliente());
        return clienteRepo.save(objClienteAct);
    }

    @Override
    public void eliminarCliente(int idcliente) {
        // eliminar cliente
        clienteRepo.deleteById(idcliente);
    }

    @Override
    public Boolean existeCliente(Integer id) {
        // el cliente existe?
        return this.clienteRepo.findById(id).isEmpty();
    }

    @Override
    public List<Cliente> listarCliente() {
        // listar clientes
        return clienteRepo.findAll();
    }

    @Override
	public List<Cliente> buscarClientePorCorreo(String correo) 
	{
		return this.clienteRepo.findByCorreo(correo);
	}

    
}
