package com.uss.facturacion.almacen.validator;


import com.uss.facturacion.almacen.entity.Alumno;
import com.uss.facturacion.almacen.exception.ValidateServiceException;

public class AlumnoValidator {
	public static void save (Alumno alumno) {
		if(alumno.getNombre()==null || alumno.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(alumno.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
	}
}
