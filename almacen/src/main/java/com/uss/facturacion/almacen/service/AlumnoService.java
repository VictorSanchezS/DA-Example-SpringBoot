package com.uss.facturacion.almacen.service;

import java.util.List;

import com.uss.facturacion.almacen.entity.Alumno;

public interface AlumnoService {
	public List<Alumno> findAll();
	public Alumno findById(int id);
	public Alumno findByNombre(String nombre);
	public List<Alumno> findByNombreContaining(String nombre);
    public Alumno create(Alumno obj);
    public Alumno update(Alumno obj);
    public int delete(int id);
}
