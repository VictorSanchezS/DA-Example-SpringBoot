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

import com.uss.facturacion.almacen.entity.Aula;
import com.uss.facturacion.almacen.service.AulaService;
import java.util.List;

@RestController
@RequestMapping("/api/aulas") //www.localhost:8081/api/aulas
public class AulaController {
	@Autowired
	private AulaService service;
	
	@GetMapping()
	public ResponseEntity<List<Aula>> getAll(){
		List<Aula> aulas= service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(aulas);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Aula> getById(@PathVariable("id") int id) {
		Aula aula = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(aula);
	}
	
	@PostMapping
	public ResponseEntity<Aula> create(@RequestBody Aula aula) {
		Aula aulaDb=service.create(aula);
		return ResponseEntity.status(HttpStatus.CREATED).body(aulaDb);
	}
	
	@PutMapping
	public ResponseEntity<Aula> update(@RequestBody Aula aula) {
		Aula aulaDb=service.update(aula);
		return ResponseEntity.status(HttpStatus.OK).body(aulaDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
