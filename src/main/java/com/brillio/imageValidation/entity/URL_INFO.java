/**
 * ----------------------------
 * URL_INFO.java
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
 * Contains url information
 *
 */
@Entity(name = "URL_INFO")
public class URL_INFO  { 

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "url_id")
	private int url_id;
	private String Url_name;
	private String tag;
	private String url_domain;
	private String imagePath;
	

	/**
	 * getter
	 * 
	 * @return imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	
	/**
	 * setter
	 * 
	 * @param imagePath
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * getter
	 * 
	 * @return Url_name
	 */
	public String getUrl_name() {
		return Url_name;
	}
	
	/**
	 * setter
	 * 
	 * @param url_name
	 */
	public void setUrl_name(String url_name) {
		Url_name = url_name;
	}

	/**
	 * getter
	 * 
	 * @return tag
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
	 * @return url_domain
	 */
	public String getUrl_domain() {
		return url_domain;
	}
	
	/**
	 * setter
	 * 
	 * @param url_domain
	 */
	public void setUrl_domain(String url_domain) {
		this.url_domain = url_domain;
	}

	/**
	 * getter
	 * 
	 * @return url_id
	 */
	public int getUrl_id() {
		return url_id;
	}	
}
