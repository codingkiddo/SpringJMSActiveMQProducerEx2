package com.websystique.spring.configuration;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloWorldConsumer implements Runnable, ExceptionListener {
	public void run() {
		try {

			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			

			connection.setExceptionListener(this);
			connection.start();
			System.out.println("Thread sleeping ...");
			Thread.sleep(25L * 1000);
	        System.out.println("Thread wokeup ...");
			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("TEST.FOO");

			// Create a MessageConsumer from the Session to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);

			// Wait for a message
			Message message = consumer.receive(100);

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Received Text: " + text);
			} else {
				System.out.println("Received: " + message);
			}

			consumer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}

	public void onException(JMSException ex) {
		System.out.println("JMS Exception occured.  Shutting down client.");
	}
}