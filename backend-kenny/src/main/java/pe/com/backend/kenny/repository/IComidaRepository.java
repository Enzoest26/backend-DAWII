package pe.com.backend.kenny.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.backend.kenny.model.Comida;

public interface IComidaRepository extends JpaRepository<Comida, String> {
	
	@Query(value = "SELECT c.idComida from Comida c " + 
				   "ORDER BY c.idComida DESC LIMIT 1")
	String getUltimoIdComida();
	
	/*
	*Metodo de retornar las comidas con un estado con un pagina
	*
	*/
	@Query("SELECT c FROM Comida c INNER JOIN Postre p ON p.idComida = c.idComida "
			+ "WHERE c.estadoComida = 1 "
			+ "UNION ALL "
			+ "SELECT c FROM Comida c INNER JOIN Sandwich s ON s.idComida = c.idComida "
			+ "WHERE c.estadoComida = 1 "
			+ "ORDER BY c.idComida DESC")
	Page<Comida> buscarEstadoComidaActivos(Pageable pageable);
}
