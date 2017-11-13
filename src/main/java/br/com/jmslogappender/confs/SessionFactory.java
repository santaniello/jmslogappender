package br.com.jmslogappender.confs;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;


/**
 * Classe que gerencia sessões JMS 
 * 
 * A Session no JMS abstrai o trabalho transacional e confirmação do recebimento da mensagem. Além disso,
 * também serve para produzir o MessageConsumer!  
 * 
 * */
public  class SessionFactory {	
	
	public static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();	
	
	public static Session getSession(Connection connection){
		if(threadSession.get() == null){
			try {
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				threadSession.set(session);				
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return threadSession.get();		
	}	
	
	public static void freeSession(){		
		if(threadSession.get() != null){
			try {
				threadSession.get().close();				
			} catch (JMSException e) {				
				e.printStackTrace();
			}finally{
				threadSession.remove();
			}
		}
	}

}
