package com.blue.wappsender.api.model.campaing.serializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.blue.wappsender.core.formats.DateFormatter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ActivationDateSerializer extends StdSerializer<LocalDateTime>{

	public ActivationDateSerializer() {
		super(LocalDateTime.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(value.format(DateTimeFormatter.ofPattern(DateFormatter.pattern())));
		
	}

}
