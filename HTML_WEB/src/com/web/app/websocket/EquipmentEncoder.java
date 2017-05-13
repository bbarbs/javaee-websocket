package com.web.app.websocket;

import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class EquipmentEncoder implements Encoder.Text<List<?>> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(List<?> list) throws EncodeException {
		Gson gson = new Gson();		
		return gson.toJson(list).toString();
	}

}
