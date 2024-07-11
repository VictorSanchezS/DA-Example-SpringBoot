package com.uss.facturacion.almacen.service;

import java.util.List;

import com.uss.facturacion.almacen.entity.Aula;

public interface AulaService {
	public List<Aula> findAll();
	public Aula findById(int id);
	public Aula findByNombre(String nombre);
	public List<Aula> findByNombreContaining(String nombre);
    public Aula create(Aula obj);
    public Aula update(Aula obj);
    public int delete(int id);
}
