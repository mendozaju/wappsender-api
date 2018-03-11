package com.blue.wappsender.core.validators.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.blue.wappsender.core.formats.DateFormatter;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

	@Override
	public void initialize(DateFormat contactNumber) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext cxt) {

		try {
			LocalDateTime.parse(value, DateTimeFormatter.ofPattern(DateFormatter.pattern()));
		}catch(DateTimeParseException e) {
			return false;
		}	
		return true;
	}

}