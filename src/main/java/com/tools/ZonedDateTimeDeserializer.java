package main.java.com.tools;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeWithZoneIdSerializer;

public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser,
                                     DeserializationContext deserializationContext)
                                     throws IOException {

    	//System.out.println(jsonParser.getText());
        //DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss").withZone(ZoneId.of("UTC")));
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").withZone(ZoneId.of("UTC"));
        return ZonedDateTime.parse(jsonParser.getText(),formatter);
    }
}