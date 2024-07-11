package com.uss.facturacion.almacen.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uss.facturacion.almacen.entity.Aula;
import com.uss.facturacion.almacen.exception.GeneralServiceException;
import com.uss.facturacion.almacen.exception.NoDataServiceException;
import com.uss.facturacion.almacen.exception.ValidateServiceException;
import com.uss.facturacion.almacen.repository.AulaRepository;
import com.uss.facturacion.almacen.service.AulaService;
import com.uss.facturacion.almacen.validator.AulaValidator;

@Service
public class AulaServiceImpl implements AulaService {
	@Autowired
	private AulaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Aula> findAll() {
		try {
			return repository.findAll();		
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Aula findById(int id) {
		try {
	        Aula aulaDb = repository.findById(id)
	                .orElseThrow(() -> new ValidateServiceException("No hay un registro con ese ID"));
	        
	        return aulaDb;
	        
	    } catch (ValidateServiceException e) {
	        throw e; 	                 
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error en el servidor");
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public Aula findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Aula> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public Aula create(Aula obj) {
		try {
			AulaValidator.save(obj);
			Aula aula=findByNombre(obj.getNombre());
			if(aula!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			obj.setActivo(true);
			return repository.save(obj);			
		} catch (ValidateServiceException e) {
			throw new ValidateServiceException(e.getMessage());
		} catch (Exception e) {
			throw new GeneralServiceException("Error en el servidor");
		}	
	}

	@Override
	@Transactional
	public Aula update(Aula obj) {
		try {
			AulaValidator.save(obj);
			Aula aulaDb=findById(obj.getId());
			//Validamos si ya existe el registro con ese nombre
			Aula aula=findByNombre(obj.getNombre());
			if(aula!=null && obj.getId()!=aula.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			aulaDb.setNombre(obj.getNombre());			
			return repository.save(aulaDb);
			
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
			Aula aulaDb= findById(id);
			if(aulaDb==null) {
				return 0;
			}else {
				repository.delete(aulaDb);
				return 1;
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
