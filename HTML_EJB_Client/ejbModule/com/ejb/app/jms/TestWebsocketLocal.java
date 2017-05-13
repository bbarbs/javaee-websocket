package com.ejb.app.jms;

import javax.ejb.Local;

@Local
public interface TestWebsocketLocal {

	/**
	 * Jms communication with websocket.
	 * @param message
	 */
	public void sendMessage(String message);
}
