package com.uss.facturacion.almacen.validator;

import com.uss.facturacion.almacen.entity.Aula;
import com.uss.facturacion.almacen.exception.ValidateServiceException;

public class AulaValidator {
	public static void save (Aula aula) {
		if(aula.getNombre()==null || aula.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(aula.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
	}

}
