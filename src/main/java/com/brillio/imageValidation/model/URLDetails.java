/**
 * ----------------------------
 * URLDetails.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * 
 * Contains individual url details
 *
 */
@Component
public class URLDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	int id;
	String ip;
	String domain_name;
	String url;
	String ExpectedImage;
	String tag;
	String status;
	int threshold;
	String ActualImage;
	String path;

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
	public String getIp() {
		return ip;
	}

	/**
	 * setter
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * setter
	 * 
	 * @param d
	 */
	public void setThreshold(int d) {
		this.threshold = d;
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
	public String getDomain_name() {
		return domain_name;
	}

	/**
	 * setter
	 * 
	 * @param domain_name
	 */
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * setter
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getExpectedImage() {
		return ExpectedImage;
	}

	/**
	 * setter
	 * 
	 * @param expectedImage
	 */
	public void setExpectedImage(String expectedImage) {
		ExpectedImage = expectedImage;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getActualImage() {
		return ActualImage;
	}

	/**
	 * setter
	 * 
	 * @param actualImage
	 */
	public void setActualImage(String actualImage) {
		ActualImage = actualImage;
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

}
