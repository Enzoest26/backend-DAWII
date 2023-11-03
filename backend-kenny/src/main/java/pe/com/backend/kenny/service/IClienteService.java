package pe.com.backend.kenny.service;


import java.util.List;

import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.model.response.BaseResponse;

public interface IClienteService {
    public List<Cliente> listarCliente();

    public Cliente registrarCliente(Cliente objCliente);

    public Cliente actualizarCliente(Cliente objCliente);

    public BaseResponse eliminarCliente(Integer idcliente);

    public Boolean existeCliente(Integer id);

    public List<Cliente> buscarClientePorEmail(String email);
    
    public Cliente buscarPorId(Integer id);

}
