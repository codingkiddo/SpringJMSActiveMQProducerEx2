package com.spring.jms;

import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
public class SampleJmsErrorHandler implements ErrorHandler {


    @Override
    public void handleError(Throwable t) {
        System.out.println("In default jms error handler...");
        System.out.println("Error Message : {}"+ t.getMessage());
    }

}