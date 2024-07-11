package com.uss.facturacion.almacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uss.facturacion.almacen.entity.Alumno;
import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
	public Alumno findByNombre(String nombre);
	public List<Alumno> findByNombreContaining (String nombre);
}
