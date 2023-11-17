package pe.com.backend.kenny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.backend.kenny.model.Postre;

public interface IPostreRepository extends JpaRepository<Postre, Integer>{
	
	List<Postre> findByIdComida(String idComida);

}
