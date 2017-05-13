package com.ejb.app.jms;

import javax.ejb.Local;

@Local
public interface SenderMessageLocal {

	/**
	 * Jms communication.
	 * @param message
	 */
	public void sendMessage(String message);
}
