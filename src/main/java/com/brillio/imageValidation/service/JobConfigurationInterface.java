/**
 * ----------------------------
 *  JobConfigurationInterface.java
 *   ---------------------------- 
 *  (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.service;

import com.brillio.imageValidation.entity.Job;

/**
 * 
 * Interface for Job Configuration service
 *
 */
public interface JobConfigurationInterface {
	/**
	 * get job details by job name
	 * 
	 * @param jobName
	 * @return
	 */
	public Boolean getJobDetailsByJobName(String jobName);

	/**
	 * saves job
	 * 
	 * @param job
	 * @return
	 */
	public Boolean saveJob(Job job);
}
