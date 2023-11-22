package pe.com.backend.kenny.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.backend.kenny.model.Boleta;

@Repository
public interface IBoletaRepository extends JpaRepository<Boleta, Integer>{
	
	@Query("SELECT b FROM Boleta b WHERE (:fechaInicio IS NULL OR :fechaFin IS NULL OR (b.fechaBoleta BETWEEN :fechaInicio AND :fechaFin)) AND "
			+ "(:idCliente IS NULL OR b.idCliente.id_cliente = :idCliente)")
	List<Boleta> buscarPorFiltros(LocalDate fechaInicio, LocalDate fechaFin, Integer idCliente);
}
