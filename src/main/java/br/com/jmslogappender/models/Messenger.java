package br.com.jmslogappender.models;

import javax.jms.Connection;
import javax.jms.Session;

import org.apache.log4j.spi.LoggingEvent;

import br.com.jmslogappender.confs.ConnectionFactory;
import br.com.jmslogappender.confs.SessionFactory;

public class Messenger {
	
	private JmsAppender appender;
	private String[] levels;
		
	public Messenger(JmsAppender appender) {
		this.appender = appender;
		getMonitorLevels();
	}
	
	public void send(LoggingEvent event){		  
	    for (String level : this.levels) {			
		    if(level.toUpperCase().equals(event.getLevel().toString())){
				Connection connection = ConnectionFactory.getConnection(appender.getUser(), appender.getPassword(),this.appender.getUrlProvider());
				Session session =  SessionFactory.getSession(connection);             
			    Producer producer = new Producer(session);             
			    producer.send(this.appender.getDestinyQueue(), new LogJmsLayout(this.appender.getLogFormat()).format(new LogJms(appender.getApplication(), event)));
			    SessionFactory.freeSession();
			    ConnectionFactory.freeConnection();
			}	
		}   
	}
	
	private void getMonitorLevels(){
		if(this.levels == null){
		   String monitorLevels = this.appender.getMonitorLevels();
		   this.levels = monitorLevels.split(",");
		}   
	}
	
}
