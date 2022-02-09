package com.spring.jms;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JmsConfig.class);
		
		ActiveMQConnectionFactory connectionFactory = applicationContext.getBean(ActiveMQConnectionFactory.class);
//		System.out.println(connectionFactory.getBrokerURL());
		
		// Create a Connection
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
		
			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("simple-queue");
		
			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
			// Create a messages
			String text = "Hello world! From: " + Thread.currentThread().getName() ;
			TextMessage message = session.createTextMessage(text);
		
			// Tell the producer to send the message
			System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
			producer.send(message);
		
			// Clean up
			session.close();
			connection.close();
		} catch (JMSException e) {
			// TODO: handle exception
		}
		
		((AnnotationConfigApplicationContext) applicationContext).close();
		
	}
}
