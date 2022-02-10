package com.spring.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class SessionAwareMessageListenerExample implements SessionAwareMessageListener<Message> {

	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		if (message instanceof TextMessage) {
			try {
				System.out.println("SessionAwareMessageListenerExample: " + ((TextMessage) message).getText());
			} catch (JMSException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
		
	}

}
