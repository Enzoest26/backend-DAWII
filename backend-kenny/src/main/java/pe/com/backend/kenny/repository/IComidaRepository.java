package pe.com.backend.kenny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.backend.kenny.model.Comida;

public interface IComidaRepository extends JpaRepository<Comida, String> {
	
	@Query(value = "SELECT c.idComida from Comida c " + 
				   "ORDER BY c.idComida DESC LIMIT 1")
	String getUltimoIdComida();
}
