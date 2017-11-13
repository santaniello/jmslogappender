package br.com.jmslogappender.models;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer {

	private Session session;
	private Destination destination;
	private MessageConsumer consumer;

	public Consumer(Session session) {
		this.session = session;
	}

	public void consumes(String queue) {
		try {
			Destination destination = getDestination(queue);
			MessageConsumer consumer = getConsumer(destination);	
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					TextMessage textMessage = (TextMessage) message;
					try {
						System.out.println(textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
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
	
	private MessageConsumer getConsumer(Destination destination){
		if(this.consumer == null){
			try {
				this.consumer =  session.createConsumer(destination);
				return this.consumer;
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		throw new RuntimeException("Erro ao criar Consumer");		
	} 
}
