package com.websystique.spring.configuration;

import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {
	private static final Logger log = Logger.getLogger(MessagingConfiguration.class.getName());
	
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/test";
    private static final String DEFAULT_MESSAGE_COUNT = "1";
    private static final String DEFAULT_USERNAME = "quickstartUser";
    private static final String DEFAULT_PASSWORD = "quickstartPwd1!";
    private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
    
	@Bean
	public ConnectionFactory connectionFactory() {
		final Properties env = new Properties();
		String userName = System.getProperty("username", DEFAULT_USERNAME);
        String password = System.getProperty("password", DEFAULT_PASSWORD);
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
        env.put(Context.SECURITY_PRINCIPAL, userName);
        env.put(Context.SECURITY_CREDENTIALS, password);

	    try {
	        final Context ctx = new InitialContext(env);

	        final ConnectionFactory factory = (ConnectionFactory) ctx.lookup(DEFAULT_CONNECTION_FACTORY);
	        ctx.close();
	        return factory;
	    }
	    catch (final NamingException e) {
	        log.info(String.format("Error while connecting to JMS. %s", e));
	    }
	    return null;
	}

}
