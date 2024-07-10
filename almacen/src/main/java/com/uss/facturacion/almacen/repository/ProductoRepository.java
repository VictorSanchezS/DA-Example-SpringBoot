package com.uss.facturacion.almacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uss.facturacion.almacen.entity.Producto;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	public Producto findByNombre(String nombre);
	public List<Producto> findByNombreContaining (String nombre);
}
