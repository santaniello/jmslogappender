package br.com.jmslogappender.models;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.log4j.Logger;

public class Producer {
	
	private Session session;
	private Destination destination;
	private MessageProducer producer;
	
	private  static Logger logger = Logger.getLogger(Producer.class);		

	public Producer(Session session) {
		this.session = session;
	}
	
	public void send(String queue, String content){
		logger.info("init send()...");
	    try {			            
			Message message = session.createTextMessage(content);
			Destination destination = getDestination(queue);
			MessageProducer producer = getProducer(destination);			
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	    logger.info("end send()...");
	}	
	
	private Destination getDestination(String queue){
		logger.info("init getDestination()...");
		if(this.destination == null){
			try {
				this.destination = this.session.createQueue(queue);
				return this.destination;
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		logger.info("end getDestination()...");
		throw new RuntimeException("Erro ao criar Destination");		
	} 
	
	private MessageProducer getProducer(Destination destination){
		logger.info("init getProducer()...");
		if(this.producer == null){
			try {
				this.producer =  session.createProducer(destination);
				return this.producer;
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		logger.info("end getProducer()...");
		throw new RuntimeException("Erro ao criar Producer");		
	} 

	
}
