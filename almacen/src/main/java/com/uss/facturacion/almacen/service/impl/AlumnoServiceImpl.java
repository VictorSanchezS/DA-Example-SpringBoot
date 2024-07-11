package com.uss.facturacion.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uss.facturacion.almacen.entity.Aula;
import com.uss.facturacion.almacen.entity.Alumno;
import com.uss.facturacion.almacen.exception.GeneralServiceException;
import com.uss.facturacion.almacen.exception.ValidateServiceException;
import com.uss.facturacion.almacen.repository.AulaRepository;
import com.uss.facturacion.almacen.repository.AlumnoRepository;
import com.uss.facturacion.almacen.service.AlumnoService;
import com.uss.facturacion.almacen.validator.AlumnoValidator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Service
public class AlumnoServiceImpl implements AlumnoService{
	
	@Autowired
	private AlumnoRepository repository;
	
	@Autowired
	private AulaRepository aulaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findAll() {
		try {
			return repository.findAll();		
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno findById(int id) {
		try {
	        Alumno alumnoDb = repository.findById(id)
	                .orElseThrow(() -> new ValidateServiceException("No hay un registro con ese ID"));
	        
	        return alumnoDb;
	        
	    } catch (ValidateServiceException e) {
	        throw e; 	                 
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error en el servidor");
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public Alumno create(Alumno obj) {
		try {
			AlumnoValidator.save(obj);
			Alumno alumno=findByNombre(obj.getNombre());
			if(alumno!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			Aula aula = aulaRepository.findById(obj.getAula().getId())
	                .orElseThrow(() -> new ValidateServiceException("Aula no encontrada"));
	        obj.setAula(aula);
			//obj.setActivo(true);
			return repository.save(obj);			
		} catch (ValidateServiceException e) {
			throw new ValidateServiceException(e.getMessage());
		} catch (Exception e) {
			throw new GeneralServiceException("Error en el servidor");
		}
	}

	@Override
	@Transactional
	public Alumno update(Alumno obj) {
		try {
			AlumnoValidator.save(obj);
			Alumno alumnoDb=findById(obj.getId());
			//Validamos si ya existe el registro con ese nombre
			Alumno alumno=findByNombre(obj.getNombre());
			if(alumno!=null && obj.getId()!=alumno.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			alumnoDb.setNombre(obj.getNombre());	
			alumnoDb.setDescripcion(obj.getDescripcion());	
			alumnoDb.setPeso(obj.getPeso());	
			alumnoDb.setEdad(obj.getEdad());	
			
			Aula aula = aulaRepository.findById(obj.getAula().getId())
	                .orElseThrow(() -> new ValidateServiceException("Aula no encontrada"));
	        alumnoDb.setAula(aula);
			
			
			return repository.save(alumnoDb);
			
		} catch (ValidateServiceException e) {
			throw new ValidateServiceException(e.getMessage());
		} catch (Exception e) {
			throw new GeneralServiceException("Error en el servidor");
		}
	}

	@Override
	@Transactional
	public int delete(int id) {
		try {
			Alumno alumnoDb= findById(id);
			if(alumnoDb==null) {
				return 0;
			}else {
				repository.delete(alumnoDb);
				return 1;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
