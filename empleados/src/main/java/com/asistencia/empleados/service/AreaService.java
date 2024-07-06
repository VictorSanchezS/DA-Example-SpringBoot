package com.asistencia.empleados.service;

import java.util.List;

import com.asistencia.empleados.entity.Area;


public interface AreaService {
	public List<Area> findAll();

	public Area findById(int id);

	public Area findByNombre(String nombre);

	public List<Area> findByNombreContaining(String nombre);

	public Area create(Area obj);

	public Area update(Area obj);

	public int delete(int id);
}
