package pe.com.backend.kenny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.backend.kenny.model.Postre;
import pe.com.backend.kenny.model.Sandwich;

public interface ISandwichRepository extends JpaRepository<Sandwich, Integer>{
	List<Sandwich> findByIdComida(String idComida);
}
