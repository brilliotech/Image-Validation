/**
 * ----------------------------
 * CrawlData.java
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
 * Contains Crawled URLS Data
 *
 */
@Entity(name = "CRAWL_DATA")
public class CrawlData {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "URL_ID")
	private int url_id;

	private String url;

	/**
	 * getter
	 * 
	 * @return url_id
	 */
	public int getUrl_id() {
		return url_id;
	}

	/**
	 * setter
	 * 
	 * @param url_id
	 */
	public void setUrl_id(int url_id) {
		this.url_id = url_id;
	}

	/**
	 * getter
	 * 
	 * @return url
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

}
