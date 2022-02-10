package com.spring.jms;

import javax.jms.JMSException;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
 
    private static final String ORDER_RESPONSE_QUEUE = "simple-queue";
     
    @JmsListener(destination = ORDER_RESPONSE_QUEUE)
    public void receiveMessage(String message) throws JMSException {
        System.out.println("Message Is : " + message);
    }
}
