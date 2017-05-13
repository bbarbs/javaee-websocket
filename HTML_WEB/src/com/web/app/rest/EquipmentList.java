package com.web.app.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ejb.app.session.EquipmentBeanLocal;
import com.google.gson.Gson;

@Path("/equip/list/")
public class EquipmentList {

	/**
	 * Inject EquipmentBeanLocal session bean local interface.
	 */
	@Inject
	private EquipmentBeanLocal equip;
	
	/**
	 * Get list of equipment.
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getList() {				
		List<?> list = null;	
		// Return as string.
		String ret = null;
		
		try {			
			// Excute session.
			list = equip.getAllList();
			
			// Parse string to json.
			Gson gson = new Gson();					
			ret = gson.toJson(list);		
		} 
		catch (Exception e) {			
			e.printStackTrace();
			return Response.status(500).entity("Server error").build();
		}
		return Response.ok(ret).build();		
	}
}
