package com.ejb.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipment implements Serializable {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int pk;
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	
	/** Column name. */
	@Column(length=10)
	public String equipName;
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	
	/** Column License. */
	@Column(length=8)
	public String license;
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	
	/** Column State. */
	@Column(length=30)
	public String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	/** Column Efficiency. */
	@Column(length=10)
	public Integer efficiency;
	public Integer getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(Integer efficiency) {
		this.efficiency = efficiency;
	}
	
	/** Column Activity. */
	@Column(length=50)
	public String activity;
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	/** Column Operator. */
	@Column(length=20)
	public String operator;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
