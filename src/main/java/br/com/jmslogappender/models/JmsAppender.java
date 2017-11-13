package br.com.jmslogappender.models;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class JmsAppender extends AppenderSkeleton{
	
	private String application;
	private String urlProvider;
	private String destinyQueue;
	private String user;
	private String password;
	private String monitorLevels;
	private String logFormat;	
	private Messenger messenger;
	
	public JmsAppender() {}
			
	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getUrlProvider() {
		return urlProvider;
	}

	public void setUrlProvider(String urlProvider) {
		this.urlProvider = urlProvider;
	}

	public String getDestinyQueue() {
		return destinyQueue;
	}

	public void setDestinyQueue(String destinyQueue) {
		this.destinyQueue = destinyQueue;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMonitorLevels() {
		return monitorLevels;
	}

	public void setMonitorLevels(String monitorLevels) {
		this.monitorLevels = monitorLevels;
	}

	public String getLogFormat() {
		return logFormat;
	}

	public void setLogFormat(String logFormat) {
		this.logFormat = logFormat;
	}

	@Override
	public void close(){}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(LoggingEvent event) {		
		if(this.messenger == null){
			this.messenger = new Messenger(this);
		}
		this.messenger.send(event);	

	}	 
}
