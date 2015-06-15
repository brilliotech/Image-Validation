/**
 * ----------------------------
 * JobDao.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.dao;

import java.util.List;

import com.brillio.imageValidation.entity.CrawlData;
import com.brillio.imageValidation.entity.Job;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;

/**
 * 
 * JobDao Interface
 *
 */
public interface JobDao {
	/**
	 * Saves Job
	 * 
	 * @param job
	 * @return
	 * @throws ImageValidationException
	 */
	Boolean saveJob(Job job) throws ImageValidationException;

	/**
	 * Get all job details
	 * 
	 * @return
	 * @throws ImageValidationException
	 */
	List<Job> getAllJobDetails() throws ImageValidationException;

	/**
	 * Get Job details by name
	 * 
	 * @param name
	 * @return
	 * @throws ImageValidationException
	 */
	Job getJobDetailsByName(String name) throws ImageValidationException;

	/**
	 * Get Job details by id
	 * 
	 * @param id
	 * @return
	 * @throws ImageValidationException
	 */
	Job getJobDetailsById(int id) throws ImageValidationException;

	/**
	 * Saves Crawled data by job name
	 * 
	 * @param name
	 * @param urls
	 * @return
	 * @throws ImageValidationException
	 */
	Boolean saveCrawlDataByJobName(String name, List<String> urls) throws ImageValidationException;

	/**
	 * Gets crawled data by job name
	 * 
	 * @param name
	 * @return
	 * @throws ImageValidationException
	 */
	List<CrawlData> getCrawlDataByJobName(String name) throws ImageValidationException;

}
