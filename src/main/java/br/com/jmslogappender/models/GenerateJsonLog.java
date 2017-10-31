package br.com.jmslogappender.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GenerateJsonLog implements GenerateFormatedLog{

	@Override
	public String generate(LogJms log) {
		try {
			ObjectMapper mp = new ObjectMapper();
			mp.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);		
			return mp.writeValueAsString(log);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Erro ao gerar o log no formato JSON!");		
	}

}
