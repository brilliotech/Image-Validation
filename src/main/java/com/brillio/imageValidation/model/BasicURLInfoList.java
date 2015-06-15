/**
 * ----------------------------
 * BasicURLInfoList.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

import java.util.List;

/**
 * 
 * Contains all basic url information
 *
 */
public class BasicURLInfoList {
	
	String jobName;
	List<BasicURLInfo> basicUrls;


	/**
	 * getter
	 * 
	 * @return
	 */
	public List<BasicURLInfo> getBasicUrls() {
		return basicUrls;
	}

	/***
	 * setter
	 * 
	 * @param basicUrls
	 */
	public void setBasicUrls(List<BasicURLInfo> basicUrls) {
		this.basicUrls = basicUrls;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * setter
	 * 
	 * @param jobName
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
}
