package br.com.jmslogappender.models;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.NamingException;

import br.com.jmslogappender.confs.ConnectionFactory;
import br.com.jmslogappender.confs.SessionFactory;



public class TestConsumer {	
	public static void main(String[] args) throws JMSException, NamingException, InterruptedException {		
		 Connection connection = ConnectionFactory.getConnection("", "","tcp://localhost:61616");
		 Session session =  SessionFactory.getSession(connection);             
         Consumer consumer = new Consumer(session);
         consumer.consumes("fila.log");
         Thread.sleep(10000);
        
	}

}
