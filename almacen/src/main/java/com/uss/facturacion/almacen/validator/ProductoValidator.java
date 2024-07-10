package com.uss.facturacion.almacen.validator;


import com.uss.facturacion.almacen.entity.Producto;
import com.uss.facturacion.almacen.exception.ValidateServiceException;

public class ProductoValidator {
	public static void save (Producto producto) {
		if(producto.getNombre()==null || producto.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(producto.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
	}
}
