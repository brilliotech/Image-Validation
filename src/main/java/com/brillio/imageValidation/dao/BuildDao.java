/**
 * ----------------------------
 * BuildDao.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.dao;

import java.util.List;

import com.brillio.imageValidation.entity.Build;
import com.brillio.imageValidation.entity.Image_Result;
import com.brillio.imageValidation.entity.URL_INFO;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;

/**
 * 
 * BuildDao Interface
 *
 */
public interface BuildDao {
	
	/**
	 * Gets all build details by job name
	 * 
	 * @param jobName
	 * @return
	 * @throws ImageValidationException
	 */
	List<Build> getAllBuildDetailsByJobName(String jobName)
			throws ImageValidationException;

	/**
	 * Get Compare mode build details by job name
	 * 
	 * @param jobName
	 * @return
	 * @throws ImageValidationException
	 */
	List<Build> getCompareModeBuildDetailsByJobName(String jobName)
			throws ImageValidationException;

	/**
	 * Saves Build details for job
	 * 
	 * @param build
	 * @param jobName
	 * @return
	 * @throws ImageValidationException
	 */
	Boolean saveBuildDetailsForJob(Build build, String jobName)
			throws ImageValidationException;

	/**
	 * Get build details by build name
	 * 
	 * @param buildname
	 * @return
	 * @throws ImageValidationException
	 */
	Build getBuildDetailsByBuildName(String buildname) throws ImageValidationException;

	/**
	 * Get Build details by build id
	 * 
	 * @param buildId
	 * @return
	 * @throws ImageValidationException
	 */
	Build getBuildDetailsByBuildId(int buildId) throws ImageValidationException;

	/**
	 * Get all baseline build by job
	 * 
	 * @param jobName
	 * @return
	 * @throws ImageValidationException
	 */
	List<Build> getAllBaseLinedBuildByJob(String jobName)
			throws ImageValidationException;

	/**
	 * Save Image results by build name
	 * 
	 * @param compareBuildName
	 * @param results
	 * @return
	 * @throws ImageValidationException
	 */
	Boolean saveImageResultsByBuildName(String compareBuildName,
			List<Image_Result> results) throws ImageValidationException;

	/**
	 * Get image results of compare mode by build name
	 * 
	 * @param name
	 * @return
	 * @throws ImageValidationException
	 */
	List<Image_Result> getImageResultsOfCompareModeByBuildName(String name)
			throws ImageValidationException;

	/**
	 * Get all urls by build name
	 * 
	 * @param buildName
	 * @return
	 * @throws ImageValidationException
	 */
	List<URL_INFO> getUrlsByBuildName(String buildName) throws ImageValidationException;

}
