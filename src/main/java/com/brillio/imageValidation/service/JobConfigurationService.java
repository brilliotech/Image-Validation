/**
 * ----------------------------
 *  ConfigurationService.java
 *   ---------------------------- 
 *  (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.brillio.imageValidation.dao.JobDao;
import com.brillio.imageValidation.entity.Job;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;

/**
 * 
 * Save a job in database and get job details from database 
 *
 */
@Component
@Scope("session")
public class JobConfigurationService implements JobConfigurationInterface{
	
	final static Logger logger = Logger.getLogger(JobConfigurationService.class);
	private JobDao jobdao;

	/**
	 * set JobDao
	 * @param jobdao
	 */
	@Autowired
	public void setJobdao(JobDao jobdao) {
		this.jobdao = jobdao;
	}

	/**
	 * get job details by job name
	 * 
	 * @param jobName
	 * @return
	 */
	@Transactional
	public Boolean getJobDetailsByJobName(String jobName) {
		Job job = null;
		try {
			job = jobdao.getJobDetailsByName(jobName);
			if (job != null) {
				logger.info(jobName + " is already present in database");
				return true;
			} else {
				return false;
			}
		} catch (ImageValidationException e) {
			logger.error(e.getmessage());
			return false;
		}
	}

	/**
	 *  Saves a job.
	 *  
	 * @param job
	 * @return boolean
	 */
	@Transactional
	public Boolean saveJob(Job job) {
		if(job != null){
		try {
		return	jobdao.saveJob(job);
		} catch (ImageValidationException e) {
			logger.error(e.getmessage());
			return false;
		}}else
		{
			logger.error("base url is null , not able to save the job");
			return false;
		}
	}
}
