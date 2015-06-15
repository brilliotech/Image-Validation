/**
 * ----------------------------
 * BaicURLInfo.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

/**
 * 
 * Contains Basic Url Information
 *
 */
public class BasicURLInfo {

	private boolean ckeckbox;
	private String basicUrl;
	private String domain;
	private String tag;
	private String path;
	private String userAgent;

	/**
	 * getter
	 * 
	 * @return
	 */
	public boolean isCkeckbox() {
		return ckeckbox;
	}

	/**
	 * setter
	 * 
	 * @param ckeckbox
	 */
	public void setCkeckbox(boolean ckeckbox) {
		this.ckeckbox = ckeckbox;
	}


	/**
	 * getter
	 * 
	 * @return
	 */
	public String getBasicUrl() {
		return basicUrl;
	}

	/**
	 * setter
	 * 
	 * @param basicUrl
	 */
	public void setBasicUrl(String basicUrl) {
		this.basicUrl = basicUrl;
	}


	/**
	 * getter
	 * 
	 * @return
	 */
	public String getDomain() {
		return domain;
	}
	
	/**
	 * setter
	 * 
	 * @param domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}


	/**
	 * getter
	 * 
	 * @return
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * setter
	 * 
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}


	/**
	 * getter
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * setter
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}


	/**
	 * getter
	 * 
	 * @return
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * setter
	 * 
	 * @param userAgent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
