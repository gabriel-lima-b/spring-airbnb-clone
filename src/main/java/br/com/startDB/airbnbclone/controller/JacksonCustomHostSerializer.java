
package br.com.startDB.airbnbclone.controller;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Room;


public class JacksonCustomHostSerializer extends StdSerializer<Host> {

	public JacksonCustomHostSerializer() {
		this(null);
	}

	protected JacksonCustomHostSerializer(Class<Host> t) {
		super(t);
	}

	@Override
	public void serialize(Host host, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject(); // host
		if (host.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeStringField("id", host.getId().toString());
		}
		jgen.writeStringField("name", host.getName());
		jgen.writeStringField("lastName", host.getLastName());
		jgen.writeStringField("email" , host.getEmail());
		jgen.writeStringField("password" , host.getPassword());
		jgen.writeStringField("phone" , host.getPhone());
		//write rooms array
		jgen.writeArrayFieldStart("rooms");
		for (Room room : host.getRooms()) {
			jgen.writeStartObject(); // room
			if (room.getId() == null) {
				jgen.writeNullField("id");
			} else {
				jgen.writeStringField("id", room.getId().toString());
			}
			jgen.writeStringField( "title", room.getTitle());
			jgen.writeStringField( "description", room.getDescription());
			jgen.writeStringField( "city", room.getCity());
			if (room.getHost().getId() == null) {
				jgen.writeNullField("id");
			} else {
				jgen.writeStringField("host", room.getHost().getId().toString());
			}
			jgen.writeNumberField("price", room.getPrice());
			jgen.writeEndObject(); // room
		}
		jgen.writeEndArray(); // rooms
		jgen.writeEndObject(); // host
	}

}
