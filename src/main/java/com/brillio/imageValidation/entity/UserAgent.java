/**
 * ----------------------------
 * UserAgent.java
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

/**
 * 
 * Contain User Agent Details
 *
 */
@Entity(name = "UserAgent_tb")
public class UserAgent {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "userAgent_id")
	private int userAgent_id;
	private String userAgent_name;
	private String browser_name;

	/**
	 * getter
	 * 
	 * @return userAgent_name
	 */
	public String getUserAgent_name() {
		return userAgent_name;
	}

	/**
	 * setter
	 * 
	 * @param userAgent_name
	 */
	public void setUserAgent_name(String userAgent_name) {
		this.userAgent_name = userAgent_name;
	}

	/**
	 * getter
	 * 
	 * @return browser_name
	 */
	public String getBrowser_name() {
		return browser_name;
	}

	/**
	 * setter
	 * 
	 * @param browser_name
	 */
	public void setBrowser_name(String browser_name) {
		this.browser_name = browser_name;
	}

	/**
	 * getter
	 * 
	 * @return userAgent_id
	 */
	public int getUserAgent_id() {
		return userAgent_id;
	}

}
