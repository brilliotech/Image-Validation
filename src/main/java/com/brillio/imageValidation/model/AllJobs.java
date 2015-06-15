/**
 * ----------------------------
 * AllJobs.java
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
 * Contain all jobs
 *
 */
public class AllJobs {

	List<JobModel> jobDetails;

	/**
	 * getter
	 * 
	 * @return
	 */
	public List<JobModel> getJobDetails() {
		return jobDetails;
	}

	/**
	 * setter
	 * 
	 * @param jobDetails
	 */
	public void setJobDetails(List<JobModel> jobDetails) {
		this.jobDetails = jobDetails;
	}

	
}
