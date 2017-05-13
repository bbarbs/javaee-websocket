package com.web.app.rest;

import java.io.StringReader;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.ejb.app.session.EquipmentBeanLocal;
import com.google.gson.Gson;


@Path("/equip") // Path of this session.
public class EquipmentSess {

	/**
	 * Inject EquipmentBeanLocal session bean local interface.
	 */
	@Inject
	private EquipmentBeanLocal equip;
	
	/**
	 * Retrieve data by pk.
	 * @param pk
	 * @return
	 */
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnByEquipPk(@PathParam("id") String pk) {		
		List<?> list = null;
		// Return as string.
		String ret = null;
		
		try {	
			// Return if pk is missing. 
			if(pk==null) 
				return Response.status(400).entity("Error: Specify id value.").build();
			
			// Execute session.
			list = equip.getEquipByPk(Integer.parseInt(pk));
			
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
	
	/**
	 * Saving data.
	 * @param incomingData
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response persistEntity(String incomingData) {		
		// Return as string.
		String ret = null;
		
		try {
			// Assign to data to jsonObject for parsing.
			JSONObject obj = new JSONObject(incomingData);
			
			// Insert entity.
			int http_code = equip.insertEntity(obj.optString("equipName"),
											obj.optString("license"), 
											obj.optString("state"),
											obj.optInt("efficiency"),
											obj.optString("activity"),
											obj.optString("operator"));	
			
			// Return if internal server error.
			if(http_code==500) {				
				return Response.status(500).entity("Error inserting data.").build();
			}			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server error").build();
		}		
		return Response.ok(ret).build();
	}
	
	/**
	 * Update data.
	 * @param incomingData
	 * @return
	 */
	@Path("/{pk}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEntity(@PathParam("pk") int pk, String incomingData) {		
		// Return as string.
		String ret = null;
		
		try {
			// Assign to data to jsonObject for parsing.
			JSONObject obj = new JSONObject(incomingData); 
			
			// Parse pk.
			//int pk = obj.optInt("pk");
			
			// Update.
			int http_code = equip.updateEquip(pk,
					  				obj.optString("equipName"),
					  				obj.optString("license"), 
					  				obj.optString("state"),
					  				obj.optInt("efficiency"),
					  				obj.optString("activity"),
					  				obj.optString("operator"));
			
			// Return if internal server error.
			if(http_code==500) {				
				return Response.status(500).entity("Error inserting data.").build();
			}			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server error").build();
		}		
		return Response.ok(ret).build();
	}
	
	/**
	 * Delete data.
	 * @param pk
	 * @return
	 */
	@Path("/{pk}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEntity(@PathParam("pk") int pk) {				
		// Http code.
		int http_code;
		// Return as string.
		String ret = null;
			
		try {
			// Delete data.
			http_code = equip.deleteState(pk);
			
			// Return if internal server error.
			if(http_code==500) {				
				return Response.status(500).entity("Error inserting data.").build();
			}			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server error").build();
		}		
		return Response.ok(ret).build();
	}
}
