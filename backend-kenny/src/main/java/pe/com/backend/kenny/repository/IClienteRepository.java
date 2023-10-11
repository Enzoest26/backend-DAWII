package pe.com.backend.kenny.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.backend.kenny.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
    List<Cliente> findByCorreo(String correo);
}
