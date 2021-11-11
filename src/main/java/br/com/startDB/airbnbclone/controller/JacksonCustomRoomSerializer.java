package br.com.startDB.airbnbclone.controller;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.startDB.airbnbclone.model.Room;

public class JacksonCustomRoomSerializer extends StdSerializer<Room> {

	public JacksonCustomRoomSerializer() {
		this(null);
	}

	protected JacksonCustomRoomSerializer(Class<Room> t) {
		super(t);
	}

	@Override
	public void serialize(Room room, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject(); // room
		if (room.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeStringField("id", room.getId().toString());
		}
		jgen.writeStringField("title", room.getTitle());
		jgen.writeStringField("description", room.getDescription());

		if (room.getHost().getId() == null) {
			jgen.writeNullField("host");
		} else {
			jgen.writeObjectFieldStart("host");
			jgen.writeStringField("id", room.getHost().getId().toString());
			jgen.writeStringField( "name", room.getHost().getName());
			jgen.writeStringField( "lastName", room.getHost().getLastName());
			jgen.writeEndObject();
		}
		jgen.writeNumberField("price", room.getPrice());
		jgen.writeEndObject(); // room
	}
}
