package br.com.jmslogappender.confs;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Classe que fabrica conexôes JMS
 * 
 */

public class ConnectionFactory {
	private static  ActiveMQConnectionFactory connectionFactory;
	public static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	private static ActiveMQConnectionFactory getConnectionFactory(String urlProvider) {
		if(connectionFactory == null){	
		   connectionFactory = new ActiveMQConnectionFactory(urlProvider);
		}
		return connectionFactory;
	}
	
	public static Connection getConnection(String user, String password,String urlProvider){
		if(threadConnection.get() == null){
			try {
				ActiveMQConnectionFactory connectionFactory = getConnectionFactory(urlProvider);
				Connection connection = connectionFactory.createConnection(user,password);
				connection.start();
				threadConnection.set(connection);				
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return threadConnection.get();		
	}
	
	public static void freeConnection(){
		if(threadConnection.get() != null){
			try {
				Connection connection = threadConnection.get();
				connection.stop();
				connection.close();
			} catch (JMSException e) {				
				e.printStackTrace();
			}finally{
				threadConnection.remove();
			}
		}
	}

}
