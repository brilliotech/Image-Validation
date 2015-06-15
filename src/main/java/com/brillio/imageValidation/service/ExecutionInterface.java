/**
 * ----------------------------
 *  ExecutionInterface.java
 *   ---------------------------- 
 *  (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.service;

import java.util.List;

import com.brillio.imageValidation.entity.Build;
import com.brillio.imageValidation.entity.Image_Result;
import com.brillio.imageValidation.entity.URL_INFO;
import com.brillio.imageValidation.model.BasicURLInfo;
import com.brillio.imageValidation.model.ImageDetails;

/**
 * 
 * Interface for Execution Service
 *
 */
public interface ExecutionInterface {
	/**
	 * get Baseline Build by job name
	 * 
	 * @param jobName
	 * @return
	 */
	public List<Build> getBaseLineBuildByJobName(String jobName);

	/**
	 * List of crawl urls
	 * 
	 * @param jobname
	 * @return
	 */
	public List<String> crawlURLS(String jobname);

	/**
	 * save crawl data by job name
	 * 
	 * @param name
	 * @param urls
	 * @return
	 */
	public Boolean saveCrawlDataByJobName(String name, List<String> urls);

	/**
	 * Checks Images compare results
	 * 
	 * @param jobName
	 * @param baseBuildName
	 * @return
	 */
	public Boolean compare(String jobName, String baseBuildName);

	/**
	 * Get BaseLine urls by build name
	 * 
	 * @param buildName
	 * @return
	 */
	public List<URL_INFO> getBaseLineUrslByBuildName(String buildName);

	/**
	 * Takes Screen shots
	 * 
	 * @param urlDetailsList
	 * @param jobName
	 * @param mode
	 * @return
	 */
	public String takeScreenshots(List<BasicURLInfo> urlDetailsList,
			String jobName, String mode);

	/**
	 * save image results
	 * 
	 * @param compareBuildName
	 * @param results
	 * @return
	 */
	public Boolean saveImageResults(String compareBuildName,
			List<Image_Result> results);

	/**
	 * gets crawl data by job name
	 * 
	 * @param jobName
	 * @return
	 */
	public List<BasicURLInfo> getCrawlDataByJobName(String jobName);

	/**
	 * gets url difference baseline
	 * 
	 * @param jobname
	 * @param mode
	 * @param urlDetailsList
	 */
	public void urlDiffBaseline(String jobname, String mode,
			List<BasicURLInfo> urlDetailsList);

	/**
	 * gets crawl sites
	 * 
	 * @param urlString
	 * @param crawlLevel
	 * @return
	 */
	public List<String> crawlSites(String urlString, int crawlLevel);

	/**
	 * initialize crawl
	 * 
	 * @param url
	 * @param crawlLevel
	 * @return
	 */
	public List<String> initiateCrawl(String url, int crawlLevel);

	/**
	 * Compare Images
	 * 
	 * @param imageDetails
	 * @return
	 * @throws Exception
	 */
	public ImageDetails compareImages(ImageDetails imageDetails)
			throws Exception;
}
