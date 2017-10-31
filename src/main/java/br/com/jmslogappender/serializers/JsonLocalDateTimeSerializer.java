package br.com.jmslogappender.serializers;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class JsonLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsonLocalDateTimeSerializer() {
		this(null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JsonLocalDateTimeSerializer(Class t) {
		super(t);
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonGenerationException {
		gen.writeString(value.toString());
	}

}
