
package br.com.startDB.airbnbclone.controller;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.startDB.airbnbclone.model.Guest;

public class JacksonCustomGuestSerializer extends StdSerializer<Guest> {

	public JacksonCustomGuestSerializer() {
		this(null);
	}

	protected JacksonCustomGuestSerializer(Class<Guest> t) {
		super(t);
	}

	@Override
	public void serialize(Guest guest, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		jgen.writeStartObject(); // guest
		if (guest.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeStringField("id", guest.getId().toString());
		}
		jgen.writeStringField("name", guest.getName());
		jgen.writeStringField("lastName", guest.getLastName());
		jgen.writeStringField("email" , guest.getEmail());
		jgen.writeStringField("password" , guest.getPassword());
		jgen.writeStringField("phone" , guest.getPhone());

		jgen.writeEndObject(); // guest
	}

}
