/**
 * ----------------------------
 * JobModel.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

/**
 * 
 * Contains Job Details
 *
 */
public class JobModel {
	String name;
	String time;
	String baseUrl;
	String jobType;
	int threshold;
	int crawlLevel;
	String availability;

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getAvailability() {
		return availability;
	}

	/**
	 * setter
	 * 
	 * @param availability
	 */
	public void setAvailability(String availability) {
		this.availability = availability;
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

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * setter
	 * 
	 * @param jobType
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
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
	 * @param threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public int getCrawlLevel() {
		return crawlLevel;
	}

	/**
	 * setter
	 * 
	 * @param crawlLevel
	 */
	public void setCrawlLevel(int crawlLevel) {
		this.crawlLevel = crawlLevel;
	}

}
