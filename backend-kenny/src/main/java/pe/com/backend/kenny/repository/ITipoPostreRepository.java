package pe.com.backend.kenny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.backend.kenny.model.TipoPostre;

public interface ITipoPostreRepository extends JpaRepository<TipoPostre, Integer> {

}
