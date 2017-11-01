package br.com.jmslogappender.loglayouts;

import br.com.jmslogappender.models.Log;
import br.com.jmslogappender.models.TypeFile;

public class LogJmsLayout {
	
	private String typeLog;
	
	
	public LogJmsLayout(String typeLog) {
		this.typeLog = typeLog;
	}
	
	public String format(Log log) {
		String logFile = null;
		for(TypeFile type : TypeFile.values()){
            if(type.getType().equals(typeLog.toUpperCase())){
            	logFile = type.getRegra().generate(log);            	
            }
		}
		return logFile;
	}	
}
