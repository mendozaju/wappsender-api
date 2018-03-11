package com.blue.wappsender.core.validators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;

/**
 * Clase encargada de realizar la validacion del bean
 * @author jmendoza
 *
 */
public class BeanValidator {


	static Validator validator = null;
	
	static{
		validator = Validation.buildDefaultValidatorFactory().getValidator();		
	}
	

	/**
	 * Realiza la validacion del bean, lanzando una excepcion de validacion si no es valido
	 * @param bean
	 */

	public static <T> void validate(T bean) {
		Set<ConstraintViolation<T>> violations = validator.validate(bean);
		if(!violations.isEmpty()) {		
			throw new ValidationException(violations.iterator().next().getMessage());
		}
	}


}