package com.iktpreobuka.elektronskidnevnik1.serialize;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateSerializer1 extends StdSerializer<Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	//private static final long serialVersionUID = 1L;
   // private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
  //  private static final int MAX_DEPTH = 1000; 


	public DateSerializer1() {
		this(null);
	}

	public DateSerializer1(Class<Date> t) {
		super(t);
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		gen.writeString(formatter.format(value));
	}
	
//	 @Override
//	    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//	        if (gen.getOutputContext().getGenerationDepth() <= 1000) { // Provera dubine ugnježđenja
//	            gen.writeString(formatter.format(value));
//	        } else {
//	            throw new IOException("Maksimalna dubina ugnježđenja premašena.");
//	        }
//	 @Override
//	    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//	        serializeWithDepth(value, gen, serializers, 0);
//	    }
//
//	    private void serializeWithDepth(Date value, JsonGenerator gen, SerializerProvider serializers, int depth) throws IOException {
//	        if (depth <= MAX_DEPTH) {
//	            gen.writeString(formatter.format(value));
//	        } else {
//	            throw new IOException("Maksimalna dubina ugnježđenja premašena.");
//	        }
//	    }
//	 
	 }

