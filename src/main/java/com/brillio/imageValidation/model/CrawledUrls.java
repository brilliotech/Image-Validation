/**
 * ----------------------------
 * CrawledUrls.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

import java.util.List;

/***
 * 
 * Contains Crawled urls
 *
 */
public class CrawledUrls {
	List<String> urls;

	/**
	 * getter
	 * 
	 * @return
	 */
	public List<String> getUrls() {
		return urls;
	}

	/**
	 * setter
	 * 
	 * @param urls
	 */
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
