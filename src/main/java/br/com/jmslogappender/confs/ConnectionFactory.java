package br.com.jmslogappender.confs;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

/**
 * Classe que fabrica conexôes JMS
 * 
 */
public class ConnectionFactory {
	
	private  static Logger logger = Logger.getLogger(ConnectionFactory.class);		
	private static  ActiveMQConnectionFactory connectionFactory;
	public static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	private static ActiveMQConnectionFactory getConnectionFactory(String urlProvider) {	
		logger.info("init getConnectionFactory()...");
		if(connectionFactory == null){	
		   connectionFactory = new ActiveMQConnectionFactory(urlProvider);
		}
		logger.info("end getConnectionFactory()...");
		return connectionFactory;
	}
	
	public static Connection getConnection(String user, String password,String urlProvider){
		logger.info("init getConnection()...");
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
		logger.info("end getConnection()...");
		return threadConnection.get();		
	}
	
	public static void freeConnection(){
		logger.info("init freeConnection()...");
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
		logger.error("end freeConnection()...");
	}

}
