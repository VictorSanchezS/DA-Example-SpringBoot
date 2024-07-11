package com.uss.facturacion.almacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uss.facturacion.almacen.entity.Aula;
import java.util.List;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer>{
	public Aula findByNombre(String nombre);
	public List<Aula> findByNombreContaining (String nombre);	
}
