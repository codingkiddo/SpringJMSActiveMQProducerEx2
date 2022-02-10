package com.spring.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JmsMain {

	public static void main(String[] args) {

		try {
			ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JmsConfig.class);

			JmsQueueSender jmsQueueSender = applicationContext.getBean(JmsQueueSender.class);
			jmsQueueSender.simpleSend("Fuck You All !!!! Guys");

			((AnnotationConfigApplicationContext) applicationContext).close();
		} catch (Exception e) {
			System.out.println("ApplicationContextException - Message : " + e);
			e.printStackTrace();
		}

	}
}
