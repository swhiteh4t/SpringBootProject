package main.java.com;

import java.time.ZonedDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import main.java.com.tools.ZonedDateTimeDeserializer;
import main.java.com.tools.ZonedDateTimeSerializer;

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
  
  @Bean
  public static ObjectMapper objectMapper() {
	  SimpleModule module = new SimpleModule();
	  module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
	  module.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
	  ObjectMapper mapper = new ObjectMapper();
	  mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	  mapper.registerModule(module);
      return mapper;
  }

}
