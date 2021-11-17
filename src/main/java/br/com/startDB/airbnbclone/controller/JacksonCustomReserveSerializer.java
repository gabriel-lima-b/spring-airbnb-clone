package br.com.startDB.airbnbclone.controller;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.startDB.airbnbclone.model.Reserve;

public class JacksonCustomReserveSerializer extends StdSerializer<Reserve> {

	public JacksonCustomReserveSerializer() {
		this(null);
	}

	protected JacksonCustomReserveSerializer(Class<Reserve> t) {
		super(t);
	}

	@Override
	public void serialize(Reserve reserve, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		jgen.writeStartObject(); // guest

		if (reserve.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeStringField("id", reserve.getId().toString());
		}
		jgen.writeStringField("checkIn", formatter.format(reserve.getCheckIn()));
		jgen.writeStringField("checkOut",formatter.format(reserve.getCheckOut()));
		jgen.writeObjectFieldStart("room"); // start room
		if (reserve.getRoom().getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeStringField("id", reserve.getRoom().getId().toString());
			jgen.writeStringField("title",reserve.getRoom().getTitle());
			jgen.writeNumberField("price", reserve.getRoom().getPrice());
		}
		jgen.writeEndObject(); // end room
		jgen.writeObjectFieldStart("guest"); // start guest
		if (reserve.getRoom().getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeStringField("id", reserve.getGuest().getId().toString());
			jgen.writeStringField("name" , reserve.getGuest().getName());
			jgen.writeStringField("lastName" , reserve.getGuest().getLastName());
			jgen.writeStringField("email" , reserve.getGuest().getEmail());
			jgen.writeStringField("phone" , reserve.getGuest().getPhone());			
		}
		jgen.writeEndObject(); // end guest
		
		
		jgen.writeEndObject(); // end Reserve
	}

}
