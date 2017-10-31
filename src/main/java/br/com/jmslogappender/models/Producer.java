package br.com.jmslogappender.models;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class Producer {
	
	private Session session;
	private Destination destination;
	private MessageProducer producer;

	public Producer(Session session) {
		this.session = session;
	}
	
	public void send(String queue, String content){
		try {			            
			Message message = session.createTextMessage(content);
			Destination destination = getDestination(queue);
			MessageProducer producer = getProducer(destination);			
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}	
	
	private Destination getDestination(String queue){
		if(this.destination == null){
			try {
				this.destination = this.session.createQueue(queue);
				return this.destination;
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		throw new RuntimeException("Erro ao criar Destination");		
	} 
	
	private MessageProducer getProducer(Destination destination){
		if(this.producer == null){
			try {
				this.producer =  session.createProducer(destination);
				return this.producer;
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		throw new RuntimeException("Erro ao criar Producer");		
	} 

	
}
