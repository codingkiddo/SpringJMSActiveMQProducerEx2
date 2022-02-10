package com.spring.jms;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

@Configuration
public class JmsConfig {

	@Bean
	public ActiveMQConnectionFactory senderConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
		return activeMQConnectionFactory;
	}

	@Bean
	public Queue getDestination() {
		return new ActiveMQQueue("simple-queue");
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(senderConnectionFactory());
		jmsTemplate.setDefaultDestination(getDestination());
		return jmsTemplate;
	}
	
	@Bean
	public JmsQueueSender jmsQueueSender() {
		JmsQueueSender jmsQueueSender = new JmsQueueSender();
		jmsQueueSender.setJmsTemplate(jmsTemplate());
		return jmsQueueSender;
	}
	
	@Bean
	public ExampleListener exampleListener() {
		return new ExampleListener();
	}
	
	@Bean
	public SessionAwareMessageListenerExample sessionAwareMessageListenerExample() {
		return new SessionAwareMessageListenerExample();
	}
	
	@Bean
	public SimpleMessageListenerContainer jmsContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(senderConnectionFactory());
		container.setDestination(getDestination());
		container.setupMessageListener(sessionAwareMessageListenerExample());
		container.setMessageListener(exampleListener());
		return container;
	}
}
