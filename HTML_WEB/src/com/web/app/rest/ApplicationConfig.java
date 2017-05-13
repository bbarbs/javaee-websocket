package com.web.app.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> rootResources = new HashSet<Class<?>>();
		rootResources.add(EquipmentList.class);
		rootResources.add(EquipmentGet.class);
		rootResources.add(EquipmentSess.class);
		
		return rootResources;
	}
}
