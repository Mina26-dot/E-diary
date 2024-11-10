package com.iktpreobuka.elektronskidnevnik1.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class CustomObjectSerializer extends StdSerializer<Object> {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomObjectSerializer() {
	        this(null);
	    }

	    protected CustomObjectSerializer(Class<Object> t) {
	        super(t);
	    }

	    @Override
	    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
	        // Implementirajte svoju logiku za serijsaciju objekta `value`
	        gen.writeStartObject();  // Primer: Početak objekta
	        gen.writeStringField("fieldName", "fieldValue");  // Primer: Polje sa string vrednošću
	        gen.writeEndObject();  // Primer: Kraj objekta
	    }
}