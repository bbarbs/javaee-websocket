package com.web.app.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.ejb.app.cdi.JMSMessage;
import com.ejb.app.jms.TestWebsocketLocal;
import com.ejb.app.logger.LoggerOut;

@ServerEndpoint("/jms/websocket")
public class TestWebSocket {

	/**
	 * Inject TestWebsocketLocal local interface.
	 */
	@Inject
    private TestWebsocketLocal jmsBean;
	
	/**
	 * Websocket session.
	 */
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
    	// Logger.
    	LoggerOut log = new LoggerOut(this.getClass().getSimpleName(), new Exception().getStackTrace()[0].getMethodName());
        try {   
        	// Add notification to session.
            session.getBasicRemote().sendText("Connected to Server.........");
            sessions.add(session);
            
            // Print logs.
            log.print("JMS and Websocket Connect", true);            
        } catch (Exception ex) {
        	log.print("Error", false);
        }
    }

    @OnMessage
    public void onMessage(String message, Session client) {
    	// Logger.
    	LoggerOut log = new LoggerOut(this.getClass().getSimpleName(), new Exception().getStackTrace()[0].getMethodName());
    	try {
    		// Display notification to client.
            client.getBasicRemote().sendText("Sending Message to JMS SessionBean..... ");
            
            // Send message to jms session bean.
            jmsBean.sendMessage(message);       
            
            // Print log
            log.print("JMS and Websocket Sending Message", true);
        } catch (IOException ex) {
            log.print("Error", false);            
        }        
    }

    @OnClose
    public void onClose(CloseReason close, Session session) {
    	// Logger.
    	LoggerOut log = new LoggerOut(this.getClass().getSimpleName(), new Exception().getStackTrace()[0].getMethodName());
    	try {
    		// Notification.
            session.getBasicRemote().sendText("Disconnected from Server............");
            sessions.remove(session);
            
            // Log.
            log.print("JMS and Websocket Disconnect", true);
        } catch (Exception ex) {
           log.print("Error", false);
        }
    }
    
    @OnError
    public void onError(Throwable t, Session session) {
    	// Logger.
    	LoggerOut log = new LoggerOut(this.getClass().getSimpleName(), new Exception().getStackTrace()[0].getMethodName());
    	try {
    		// Notification.
            session.getBasicRemote().sendText("Error............");
            sessions.remove(session);
            
            // Log.
            log.print("JMS and Websocket Error", true);
            
            t.printStackTrace();
        } catch (Exception ex) {
           log.print("Error", false);
        }
    }

    /**
     * Listener when event fire the message from mdb.
     * @param msg
     */
    public void onJMSMessage(@Observes @JMSMessage Message msg) {
    	// Logger.
    	LoggerOut log = new LoggerOut(this.getClass().getSimpleName(), new Exception().getStackTrace()[0].getMethodName());
    	
    	TextMessage txtmsg = (TextMessage) msg;
    	try {
    		// Loop to sessions
    		for(Session sess : sessions) {
    			// Add notification when message receive from jms.
    			sess.getBasicRemote().sendText(log.print("JMS Message Recieve: " + txtmsg.getText() , true));
    		}
    		// Log.
    		log.print("JMS and Websocket Message Receive", true);
    		} catch (IOException e) {
    			log.print("Error", false);
    		} catch (Exception e) {
    			log.print("Error", false);
			}
    	}
}
