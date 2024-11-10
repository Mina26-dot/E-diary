package com.iktpreobuka.elektronskidnevnik1.serialize;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateDeserialize extends StdDeserializer<Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

	public DateDeserialize() {
		this(null);
	}

	public DateDeserialize(Class<?> vc) {
		super(vc);
	}

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
		String date = jsonparser.getText();
		try {
			return (Date) formatter.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
