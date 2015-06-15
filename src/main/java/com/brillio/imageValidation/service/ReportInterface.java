/**
 * ----------------------------
 *  ReportInterface.java
 *   ---------------------------- 
 *  (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.service;

import java.util.List;

import com.brillio.imageValidation.model.AllBuild;
import com.brillio.imageValidation.model.AllJobs;
import com.brillio.imageValidation.model.ImageDetails;

/**
 * 
 * Interface for Report Service
 *
 */
public interface ReportInterface {
	/**
	 * get all jobs
	 * 
	 * @return
	 */
	public AllJobs getAllJobs();

	/**
	 * get all build details by job name
	 * 
	 * @param jobName
	 * @return
	 */
	public AllBuild getAllBuildDetailsByJobname(String jobName);

	/**
	 * get compare mode build details by job name
	 * 
	 * @param jobName
	 * @return
	 */
	public AllBuild getCompareModeBuildDetailsByJobname(String jobName);

	/**
	 * get image result details of compare mode by build name
	 * 
	 * @param buildName
	 * @param jobname
	 * @return
	 */
	public List<ImageDetails> getImageResultDetailsOfCompareModeByBuildName(
			String buildName, String jobname);
}
