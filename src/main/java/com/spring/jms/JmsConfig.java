package com.spring.jms;

import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {

	@Bean
	public ActiveMQConnectionFactory senderConnectionFactory() {
		
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
		return activeMQConnectionFactory;

	}
	
//	@Bean
//	  public Destination getDestination() {
//	    return new ActiveMQQueue("TEST.FOO");
//	  }

}
