package com.asistencia.empleados.validator;

import com.asistencia.empleados.entity.Area;
import com.asistencia.empleados.exception.ValidateServiceException;

public class AreaValidator {
	public static void save(Area categoria) {
		if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if (categoria.getNombre().length() > 100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
	}

}
