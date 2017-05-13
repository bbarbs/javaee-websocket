package com.web.app.websocket;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.ejb.app.session.EquipmentBeanLocal;

public class EquipmentDecoder implements Decoder.Text<List<?>>{

	@Inject
	private EquipmentBeanLocal equip;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<?> decode(String arg0) throws DecodeException {
		List<?> list = equip.getAllList();
		return list;
	}

	@Override
	public boolean willDecode(String arg0) {
		return true;
	}

}
