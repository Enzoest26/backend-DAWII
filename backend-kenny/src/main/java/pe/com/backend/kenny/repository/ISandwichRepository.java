package pe.com.backend.kenny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.backend.kenny.model.Sandwich;

public interface ISandwichRepository extends JpaRepository<Sandwich, Integer>{

}
