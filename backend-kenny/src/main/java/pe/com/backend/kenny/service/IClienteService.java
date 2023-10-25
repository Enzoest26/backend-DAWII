package pe.com.backend.kenny.service;


import java.util.List;

import pe.com.backend.kenny.model.Cliente;

public interface IClienteService {
    public List<Cliente> listarCliente();
    public Cliente registrarCliente(Cliente objCliente);
    public Cliente actualizarCliente(Cliente objCliente);
    public void eliminarCliente(int idcliente);
    public Boolean existeCliente(Integer id);
    public List<Cliente> buscarClientePorEmail(String email);
}
