/**
 * ----------------------------
 * BuildModel.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

/**
 * 
 * Contains Build Details
 *
 */
public class BuildModel {
    int id;
	String name;
	String time;
	String status;
	String mode;
	

	/**
	 * getter
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * setter
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setter
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * setter
	 * 
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * setter
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getMode() {
		return mode;
	}
	
	/**
	 * setter
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
}
