package com.uss.facturacion.almacen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uss.facturacion.almacen.entity.Categoria;
import com.uss.facturacion.almacen.entity.Producto;
import com.uss.facturacion.almacen.repository.CategoriaRepository;
import com.uss.facturacion.almacen.service.ProductoService;
import java.util.List;


@RestController
@RequestMapping("/api/productos") //www.localhost:8081/api/productos
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	@GetMapping()
	public ResponseEntity<List<Producto>> getAll(){
		List<Producto> productos = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(productos);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Producto> getById(@PathVariable("id") int id) {
		Producto producto = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(producto);
	}
	
	@PostMapping
	public ResponseEntity<Producto> create(@RequestBody Producto producto) {
		Producto productoDb=service.create(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productoDb);
	}
	
	@PutMapping
	public ResponseEntity<Producto> update(@RequestBody Producto producto) {
		Producto productoDb=service.update(producto);
		return ResponseEntity.status(HttpStatus.OK).body(productoDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
	
}
