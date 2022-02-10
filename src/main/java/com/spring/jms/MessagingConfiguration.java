package com.spring.jms;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfiguration {

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
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
		JmsTemplate jmsTemplate =  new  JmsTemplate(connectionFactory());
		jmsTemplate.setDefaultDestination(getDestination());
		return jmsTemplate;
	}
	
	@Bean
	public  JmsQueueSender  jmsQueueSender () {
		JmsQueueSender jmsQueueSender = new JmsQueueSender();
		jmsQueueSender.setJmsTemplate(jmsTemplate());
		return jmsQueueSender;
	}

//	@Bean
//	public SampleJmsErrorHandler sampleJmsErrorHandler() {
//		return new SampleJmsErrorHandler();
//	}
	
//	@Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setErrorHandler(sampleJmsErrorHandler());
//        return factory;
//    }
}
