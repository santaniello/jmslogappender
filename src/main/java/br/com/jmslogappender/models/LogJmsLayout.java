package br.com.jmslogappender.models;

public class LogJmsLayout {
	
	private String typeLog;
	
	
	public LogJmsLayout(String typeLog) {
		this.typeLog = typeLog;
	}
	
	public String format(LogJms log) {
		String logFile = null;
		for(TypeLog type : TypeLog.values()){
            if(type.getType().equals(typeLog.toUpperCase())){
            	logFile = type.getRegra().generate(log);            	
            }
		}
		return logFile;
	}	
}
