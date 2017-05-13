package com.web.app.websocket;

import java.util.List;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(
		value = "/equip/websocket",
		encoders = EquipmentEncoder.class,
		decoders = EquipmentDecoder.class
		)
public class EquipmentEndPoint {

	@OnMessage
	public void onMessage(List<?> list, Session sess) {
		for(Session s : sess.getOpenSessions()) {
			s.getAsyncRemote().sendObject(list);
		}
	}
}
