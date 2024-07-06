package com.asistencia.empleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asistencia.empleados.entity.Area;
import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
	public Area findByNombre(String nombre);
	public List<Area> findByNombreContaining(String nombre);
}
