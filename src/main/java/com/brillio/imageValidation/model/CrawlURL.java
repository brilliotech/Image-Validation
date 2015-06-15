/**
 * ----------------------------
 * CrawlURl.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
 * Contains base url
 * 
 */
@Component
public class CrawlURL {

	private String baseUrl;


	/**
	 * getter
	 * 
	 * @return
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * setter
	 * 
	 * @param baseUrl
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	private List<String> url;


	/**
	 * getter
	 * 
	 * @return
	 */
	public List<String> getUrl() {
		return url;
	}

	/**
	 * setter
	 * 
	 * @param url
	 */
	public void setUrl(List<String> url) {
		this.url = url;
	}

}
