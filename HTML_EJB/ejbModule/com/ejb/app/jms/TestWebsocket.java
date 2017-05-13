package com.ejb.app.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

/**
 * Session Bean implementation class TestWebsocket
 */
@Stateless
public class TestWebsocket implements TestWebsocketLocal {

	/**
	 * Use CDI to inject jmscontext using default connection.
	 */
	@Inject
	JMSContext jmsContext;
	
	/**
	 * Set topic destination.
	 */
	@Resource(mappedName="java:/jms/topic/testWebsocket")
	Topic topic;
	    
	@Override
	public void sendMessage(String message) {
		// Create producer to send message.
		jmsContext.createProducer().send(topic, message);
	}

}
