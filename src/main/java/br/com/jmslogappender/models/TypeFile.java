package br.com.jmslogappender.models;

public enum TypeFile {
	XML("XML",new GenerateXmlLog()),
	JSON("JSON",new GenerateJsonLog());

	private GenerateFormatedLog regra;
	private String type;

	TypeFile(String type, GenerateFormatedLog regra) {
		this.regra = regra;
		this.type = type;
	}

	public GenerateFormatedLog getRegra() {
		return this.regra;
	}
	
	public String getType() {
		return this.type;
	}
}
