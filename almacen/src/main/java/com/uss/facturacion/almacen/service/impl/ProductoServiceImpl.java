package com.uss.facturacion.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uss.facturacion.almacen.entity.Categoria;
import com.uss.facturacion.almacen.entity.Producto;
import com.uss.facturacion.almacen.exception.GeneralServiceException;
import com.uss.facturacion.almacen.exception.ValidateServiceException;
import com.uss.facturacion.almacen.repository.CategoriaRepository;
import com.uss.facturacion.almacen.repository.ProductoRepository;
import com.uss.facturacion.almacen.service.ProductoService;
import com.uss.facturacion.almacen.validator.ProductoValidator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		try {
			return repository.findAll();		
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(int id) {
		try {
	        Producto productoDb = repository.findById(id)
	                .orElseThrow(() -> new ValidateServiceException("No hay un registro con ese ID"));
	        
	        return productoDb;
	        
	    } catch (ValidateServiceException e) {
	        throw e; 	                 
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error en el servidor");
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public Producto create(Producto obj) {
		try {
			ProductoValidator.save(obj);
			Producto producto=findByNombre(obj.getNombre());
			if(producto!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			Categoria categoria = categoriaRepository.findById(obj.getCategoria().getId())
	                .orElseThrow(() -> new ValidateServiceException("Categoria no encontrada"));
	        obj.setCategoria(categoria);
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
	public Producto update(Producto obj) {
		try {
			ProductoValidator.save(obj);
			Producto productoDb=findById(obj.getId());
			//Validamos si ya existe el registro con ese nombre
			Producto producto=findByNombre(obj.getNombre());
			if(producto!=null && obj.getId()!=producto.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			productoDb.setNombre(obj.getNombre());	
			productoDb.setDescripcion(obj.getDescripcion());	
			productoDb.setPrecio(obj.getPrecio());	
			productoDb.setCantidad(obj.getCantidad());	
			
			Categoria categoria = categoriaRepository.findById(obj.getCategoria().getId())
	                .orElseThrow(() -> new ValidateServiceException("Categoria no encontrada"));
	        productoDb.setCategoria(categoria);
			
			
			return repository.save(productoDb);
			
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
			Producto productoDb= findById(id);
			if(productoDb==null) {
				return 0;
			}else {
				repository.delete(productoDb);
				return 1;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
