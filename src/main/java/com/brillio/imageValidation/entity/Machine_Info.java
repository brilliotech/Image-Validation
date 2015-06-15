/**
 * ----------------------------
 * Machine_info.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 
 * Contains Machine Information
 *
 */
@Entity(name="MACHINE_INFO")
public class Machine_Info {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="MC_ID")
	private Integer MC_ID;
	
	private String Platform_name;
	private String plaform_version;

	private String Browser_name;
	private String Browser_version;
	
	@OneToOne(mappedBy = "info")

	
	
/*	public Integer getMC_ID() {
		return MC_ID;
	}
	public void setMC_ID(Integer mC_ID) {
		MC_ID = mC_ID;
	}*/

	/**
	 * getter
	 * 
	 * @return Platform_name
	 */
	public String getPlatform_name() {
		return Platform_name;
	}
	
	/**
	 * setter
	 * 
	 * @param platform_name
	 */
	public void setPlatform_name(String platform_name) {
		Platform_name = platform_name;
	}

	/**
	 * getter
	 * 
	 * @return plaform_version
	 */
	public String getPlaform_version() {
		return plaform_version;
	}
	
	/**
	 * setter
	 * 
	 * @param plaform_version
	 */
	public void setPlaform_version(String plaform_version) {
		this.plaform_version = plaform_version;
	}
	

	/**
	 * getter
	 * 
	 * @return Browser_name
	 */
	public String getBrowser_name() {
		return Browser_name;
	}
	
	/**
	 * setter
	 * 
	 * @param browser_name
	 */
	public void setBrowser_name(String browser_name) {
		Browser_name = browser_name;
	}

	/**
	 * getter
	 * 
	 * @return Browser_version
	 */
	public String getBrowser_version() {
		return Browser_version;
	}
	
	/**
	 * setter
	 * 
	 * @param browser_version
	 */
	public void setBrowser_version(String browser_version) {
		Browser_version = browser_version;
	}

}