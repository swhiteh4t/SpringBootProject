package main.java.com.tools;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import main.java.com.entity.Client;
import main.java.com.entity.Construction;

public class ClientSerializer extends JsonSerializer<Client>{
	
	private int findActiveConstructions(Set<Construction> l) {
		int value = 0;
		Iterator<Construction> it = l.iterator();
	     while(it.hasNext()){
	        if(it.next().getLicense().getState().equals("Approved")) {
	        	value++;
	        }
	     }
	     return value;
	}

	@Override
	public void serialize(Client value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		int active = findActiveConstructions(value.getConstructions());
		gen.writeStartObject();
		gen.writeStringField("id", value.getId());
		gen.writeStringField("name", value.getName());
		gen.writeStringField("surname", value.getSurname());
		gen.writeStringField("address", value.getAddress());
		gen.writeStringField("phoneNumber", value.getPhoneNumber());
		gen.writeStringField("accountNumber", value.getAccountNumber());
		gen.writeNumberField("activeConstructions", active);
		gen.writeEndObject();
	}


}
