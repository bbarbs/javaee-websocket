package com.ejb.app.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.ejb.app.logger.LoggerOut;

/**
 * Message-Driven Bean implementation class for: TestTopic
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic"), @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "testTopic")
		}, 
		mappedName = "testTopic")
public class TestTopic implements MessageListener {

	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	LoggerOut log = new LoggerOut(this.getClass().getSimpleName(), new Exception().getStackTrace()[0].getMethodName());
		
		TextMessage textMessage = (TextMessage) message;
		
		// Trace in server log only.
		try { 
			log.print("JMS message: "+ 
					textMessage.getText()
					, true);			
		} 
		catch (JMSException e) { 
			log.print("JMS message", false);
			e.printStackTrace();
		}
    }

}
