package pe.com.backend.kenny.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.backend.kenny.model.Bebida;

public interface BebidaRepository extends JpaRepository<Bebida, String>{
	
	List<Bebida> findByEstadoBebida(int estado);
	
	@Query(value = "SELECT b.idBebida FROM Bebida b " +
			       "ORDER BY b.idBebida DESC LIMIT 1")	
	String getUltimoIdBebida();
	
	Page<Bebida> findByEstadoBebida(int estado, Pageable pageable);
}
