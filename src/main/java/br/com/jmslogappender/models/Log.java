package br.com.jmslogappender.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.log4j.spi.LoggingEvent;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jmslogappender.serializers.JsonLocalDateTimeSerializer;
import br.com.jmslogappender.serializers.XmlLocalDateTimeSerializer;

@JsonRootName(value="log")
@XmlRootElement(name = "log")
@XmlAccessorType(XmlAccessType.FIELD)
public class Log {
	private String application;	
	private String level;
	private String message;
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	@XmlJavaTypeAdapter(value = XmlLocalDateTimeSerializer.class)
	private LocalDateTime dateHour;
		
	public Log() {}
	
	public Log(String application, LoggingEvent event) {
		this.application = application;
		this.level = event.getLevel().toString();
		this.message = event.getMessage().toString();
		this.dateHour = parseLocalDateTime(LoggingEvent.getStartTime());
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getDateHour() {
		return dateHour;
	}
	public void setDateHour(LocalDateTime dateHour) {
		this.dateHour = dateHour;
	}
	public void setDateHour(long dateHour) {
		this.dateHour =  parseLocalDateTime(dateHour);  
	}
	
	private LocalDateTime parseLocalDateTime(long dateHour){
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateHour),
				 					   TimeZone.getDefault().toZoneId());
	}
}
