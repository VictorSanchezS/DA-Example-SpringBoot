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
import com.uss.facturacion.almacen.entity.Alumno;
import com.uss.facturacion.almacen.repository.AulaRepository;
import com.uss.facturacion.almacen.service.AlumnoService;
import java.util.List;


@RestController
@RequestMapping("/api/alumnos") //www.localhost:8081/api/alumnos
public class AlumnoController {

	@Autowired
	private AlumnoService service;
	
	@GetMapping()
	public ResponseEntity<List<Alumno>> getAll(){
		List<Alumno> alumnos = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(alumnos);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Alumno> getById(@PathVariable("id") int id) {
		Alumno alumno = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(alumno);
	}
	
	@PostMapping
	public ResponseEntity<Alumno> create(@RequestBody Alumno alumno) {
		Alumno alumnoDb=service.create(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
	}
	
	@PutMapping
	public ResponseEntity<Alumno> update(@RequestBody Alumno alumno) {
		Alumno alumnoDb=service.update(alumno);
		return ResponseEntity.status(HttpStatus.OK).body(alumnoDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
	
}
