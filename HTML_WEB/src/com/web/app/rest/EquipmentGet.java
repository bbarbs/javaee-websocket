package com.web.app.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ejb.app.session.EquipmentBeanLocal;
import com.google.gson.Gson;

@Path("/equip/get/")
public class EquipmentGet {

	/**
	 * Inject EquipmentBeanLocal session bean local interface.
	 */
	@Inject
	private EquipmentBeanLocal equip;
			
	/**
	 * Search data by equipment name.
	 * @param equipName
	 * @return
	 */
	@Path("/search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnEquipNameByQuery(@QueryParam("equipName") String equipName) {		
		List<?> list = null;	
		// Return as string.
		String ret = null;
		
		try {			
			// Return if equipment name is missing. 
			if(equipName==null) 
				return Response.status(400).entity("Error: Specify equipment name.").build();
					
			// Execute session.
			list = equip.getEquipByName(equipName);
			
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
	
	@Path("/equipment/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnEquipNameByPath(@PathParam("name") String equipName) {		
		List<?> list = null;	
		// Return as string.
		String ret = null;
		
		try {	
			// Return if equipment name is missing. 
			if(equipName==null) 
				return Response.status(400).entity("Error: Specify equipment name.").build();
				
			// Execute session.
			list = equip.getEquipByName(equipName);
			
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
	
	@Path("/{activity}/{operator}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnSpecificBrandItem(
				@PathParam("activity") String activity,
				@PathParam("operator") String operator) {		
		List<?> list = null;	
		//Return as string.
		String ret = null;
		
		try {	
			// Return if activity or operator name is missing. 
			if(activity==null) 
				return Response.status(400).entity("Error: Specify activity name.").build();				
			else if(operator==null) 
				return Response.status(400).entity("Error: Specify operator name.").build();
						
			// Execute session.	
			list = equip.getActivityAndOperator(activity, operator);
			
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
