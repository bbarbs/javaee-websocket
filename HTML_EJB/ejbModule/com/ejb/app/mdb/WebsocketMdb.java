package com.ejb.app.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.ejb.app.cdi.JMSMessage;

/**
 * Message-Driven Bean implementation class for: WebsocketMdb
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic"), @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "testWebsocket")
		}, 
		mappedName = "testWebsocket")
public class WebsocketMdb implements MessageListener {

	/**
	 * Inject event together with CDI qualifier.
	 */
	@Inject
	@JMSMessage
	Event<Message> mdbMessage;
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	// Fire message.
       mdbMessage.fire(message);       
    }

}
