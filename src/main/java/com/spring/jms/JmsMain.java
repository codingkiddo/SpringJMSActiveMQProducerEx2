package com.spring.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JmsMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		JmsQueueSender jmsQueueSender = applicationContext.getBean(JmsQueueSender.class);
		jmsQueueSender.simpleSend("Fuck You All !!!! Guys");
//		Thread.sleep(10L * 1000);
		System.out.println("Sent Message !!!!");
		
		((AnnotationConfigApplicationContext) applicationContext).close();

	}
}
