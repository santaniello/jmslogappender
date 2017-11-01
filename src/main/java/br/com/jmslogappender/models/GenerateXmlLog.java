package br.com.jmslogappender.models;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

public class GenerateXmlLog implements GenerateFormatedLog{
	
	@Override
	public String generate(Log log) {
		StringWriter writer = new StringWriter();
		JAXB.marshal(log, writer);
		return writer.toString();
	}

}
