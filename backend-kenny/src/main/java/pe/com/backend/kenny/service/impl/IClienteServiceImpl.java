package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import pe.com.backend.kenny.exception.ItemNoEncontradoException;
import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.repository.IClienteRepository;
import pe.com.backend.kenny.service.IClienteService;
import pe.com.backend.kenny.util.Constantes;

@Service
public class IClienteServiceImpl implements IClienteService{

    private @Autowired IClienteRepository clienteRepo;

    private @Autowired PasswordEncoder passwordEncoder;

    @Override
    public Cliente registrarCliente(Cliente objCliente) {
        // registrar cliente
        objCliente.setClave_cliente(this.passwordEncoder.encode(objCliente.getClave_cliente()));
        return this.clienteRepo.save(objCliente);
    }

    @Override
    public BaseResponse actualizarCliente(Cliente objCliente) {
        // actualizar cliente
        if(this.clienteRepo.existsById(objCliente.getId_cliente()))
        {
            objCliente.setClave_cliente(this.passwordEncoder.encode(objCliente.getClave_cliente()));
            this.clienteRepo.save(objCliente);
            return BaseResponse.builder()
                    .codRespuesta(Constantes.CODIGO_EXITO_ACTUALIZACION)
                    .msjRespuesta(Constantes.MENSAJE_EXITO_ACTUALIZACION)
                    .build();
        }
        throw new ItemNoEncontradoException("Cliente no encontrado");
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
	public List<Cliente> buscarClientePorEmail(String email) 
	{
		return this.clienteRepo.findByEmailCliente(email);
	}

    
}
