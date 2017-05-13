package com.ejb.app.session;

import java.util.List;

import javax.ejb.Local;

@Local
public interface EquipmentBeanLocal {

	public void persistData();
	
	public void init();
	
	public boolean isInit();
	
	public String hello();
	
	public List<?> getAllList();
	
	public List<?> getEquipByPk(int pk);
	
	public List<?> getEquipByName(String equipName);
	
	public List<?> getActivityAndOperator(String activity, String operator);
	
	public int insertEntity(String equipName, String license, String state, int efficiency, String activity, String operator);
	
	public int deleteState(int pk);
	
	public int updateEquip(int pk, String equipName, String license, String state, int efficiency, String activity, String operator);
}
