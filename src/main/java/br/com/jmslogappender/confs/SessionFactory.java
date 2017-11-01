package br.com.jmslogappender.confs;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.log4j.Logger;


/**
 * Classe que gerencia sessões JMS 
 * 
 * A Session no JMS abstrai o trabalho transacional e confirmação do recebimento da mensagem. Além disso,
 * também serve para produzir o MessageConsumer!  
 * 
 * */
public  class SessionFactory {	
	
	private  static Logger logger = Logger.getLogger(ConnectionFactory.class);		
	public static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();	
	
	public static Session getSession(Connection connection){
		logger.info("init getSession()...");
		if(threadSession.get() == null){
			try {
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				threadSession.set(session);				
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		logger.info("end getSession()...");
		return threadSession.get();		
	}	
	
	public static void freeSession(){		
		logger.info("init freeConnection()...");
		if(threadSession.get() != null){
			try {
				threadSession.get().close();				
			} catch (JMSException e) {				
				e.printStackTrace();
			}finally{
				threadSession.remove();
			}
		}
		logger.info("end freeConnection()...");
	}

}
