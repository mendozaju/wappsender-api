package com.blue.wappsender.api.model.campaing.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ValidationException;

import com.blue.wappsender.core.formats.DateFormatter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ActivationDateDeserializer extends StdDeserializer<LocalDateTime>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatter.pattern());
	
	public ActivationDateDeserializer() {
		super(LocalDate.class);
	}

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);			
		if( node != null) {
			String date = node.asText();
			try {
			return LocalDateTime.parse(date, formatter);
			}catch(DateTimeParseException e) {
				throw new ValidationException("El formato de la fecha debe ser:["+DateFormatter.pattern()+"]");
			}
		}
		return null;
	}


}
