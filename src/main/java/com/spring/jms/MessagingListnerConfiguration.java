package com.spring.jms;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
 
@Configuration
@EnableJms
public class MessagingListnerConfiguration {
 
    @Autowired
    ConnectionFactory connectionFactory;
     
    @Bean
	public SampleJmsErrorHandler sampleJmsErrorHandler() {
		return new SampleJmsErrorHandler();
	}
    
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            factory.setConnectionFactory(connectionFactory);
            factory.setErrorHandler(sampleJmsErrorHandler());
            factory.setConcurrency("1-1");
            return factory;
    }
 
}