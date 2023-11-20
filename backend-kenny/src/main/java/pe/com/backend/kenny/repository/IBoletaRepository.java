package pe.com.backend.kenny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.backend.kenny.model.Boleta;

@Repository
public interface IBoletaRepository extends JpaRepository<Boleta, Integer>{

}
