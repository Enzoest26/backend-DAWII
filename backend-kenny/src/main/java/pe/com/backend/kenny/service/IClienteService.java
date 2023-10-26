package pe.com.backend.kenny.service;


import java.util.List;

import pe.com.backend.kenny.model.Cliente;
import pe.com.backend.kenny.model.response.BaseResponse;

public interface IClienteService {
    public List<Cliente> listarCliente();

    public Cliente registrarCliente(Cliente objCliente);

    public BaseResponse actualizarCliente(Cliente objCliente);

    public BaseResponse eliminarCliente(int idcliente);

    public Boolean existeCliente(Integer id);

    public List<Cliente> buscarClientePorEmail(String email);

}
