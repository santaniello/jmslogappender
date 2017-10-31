package br.com.jmslogappender.models;

public enum TypeLog {
	XML("XML",new GenerateXmlLog()),
	JSON("JSON",new GenerateJsonLog());

	private GenerateFormatedLog regra;
	private String type;

	TypeLog(String type, GenerateFormatedLog regra) {
		this.regra = regra;
		this.type = type;
	}

	public GenerateFormatedLog getRegra() {
		return this.regra;
	}
	
	public String getType() {
		return this.type;
	}

	public static TypeLog fromValue(String value) {
		if (value.toUpperCase().equals("XML")) {
			return XML;
		} else if (value.toUpperCase().equals("JSON")) {
			return JSON;
		}
		throw new RuntimeException("Tipo de arquivo inválido !");
	}

}
