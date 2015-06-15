/**
 * ----------------------------
 * ReportJob.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Contains Job Report Details
 *
 */
public class ReportJob {
	String jobName;
	String jobTime;
	int threshold;
	List<ReportBuild> buildList = new ArrayList<ReportBuild>();
	
	/**
	 * getter
	 * 
	 * @return
	 */
	public List<ReportBuild> getBuilList() {
		return buildList;
	}

	/**
	 * setter
	 * 
	 * @param buildList
	 */
	public void setBuilList(List<ReportBuild> buildList) {
		this.buildList = buildList;
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
	
	/**
	 * getter
	 * 
	 * @return
	 */
	public String getJobTime() {
		return jobTime;
	}

	/**
	 * setter 
	 * 
	 * @param jobTime
	 */
	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}

}
