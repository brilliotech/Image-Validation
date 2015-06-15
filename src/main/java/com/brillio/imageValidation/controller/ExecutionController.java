/**
 * ----------------------------
 * ExecutionController.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brillio.imageValidation.entity.Build;
import com.brillio.imageValidation.model.BaseLineBuilds;
import com.brillio.imageValidation.model.BasicURLInfo;
import com.brillio.imageValidation.model.BasicURLInfoList;
import com.brillio.imageValidation.service.ExecutionInterface;

/**
 * Deals with the execution management of a Job. Below shown operations are supported 
 * 1)Crawling a web site. 
 * 2)BaseLine screenshot capture on the selected crawled URL data. 
 * 3)Compare with BaseLines builds.
 */
@Controller
@Scope("request")
public class ExecutionController {
	/**
	 * Registering to the logger
	 */
	final static Logger logger = Logger.getLogger(ExecutionController.class);

	/**
	 * Service required to crawl/baseline/compare the jobs
	 */
	private ExecutionInterface executionInterface;

	/**
	 * Sets Service
	 * 
	 * @param executionInterface
	 */
	@Autowired
	public void setExecutionService(ExecutionInterface executionInterface) {
		this.executionInterface = executionInterface;
	}

	/**
	 * Returns all the Base lined builds for a particular job.
	 * 
	 * @param jobName
	 * @return List of BaseLineBuilds
	 */
	@RequestMapping(value = { "/allBaseLineBuildsByJobName" }, method = RequestMethod.GET)
	public @ResponseBody BaseLineBuilds getBaseLinedJobDetailsByJobName(
			@RequestParam("jobName") String jobName) {
		List<Build> builds = null;
		BaseLineBuilds baseLineBuilds = new BaseLineBuilds();

		if (jobName != null && jobName.length() > 0) {
			builds = executionInterface.getBaseLineBuildByJobName(jobName);
			if (builds != null && builds.size() > 0) {
				for (Build build : builds) {
					baseLineBuilds.addBuildNames(build.getBuild_Name());
				}
			}
		}
		return baseLineBuilds;
	}

	/**
	 * Crawls the Base URL for a Job and saves the master list of crawled URLs.
	 * 
	 * @param jobName
	 * @return true, successfully crawled and saved
	 *         false, unsuccessfully crawled and saved
	 */
	@RequestMapping(value = { "/crawlAndSave" }, method = RequestMethod.POST, produces = "plain/text")
	public @ResponseBody String crawlAndSave(@RequestParam("jobName") String jobName) {
		
		try {
			List<String> urlList = null;
			if(jobName != null && jobName.length() > 0){
				urlList = executionInterface.crawlURLS(jobName);
			}else
				return "Job Name is Invalid";
			if (urlList != null && urlList.size() > 0) {
				logger.debug("Saving the crawled urls for a Job: "+jobName );
				executionInterface.saveCrawlDataByJobName(jobName, urlList);
				return jobName;
			}else
				return "Couldn't crawl the site for Job : " + jobName;
		} catch (Exception e) {
			logger.error("Couldn't execute/save Crawled URLS for this : "
					+ jobName, e);
			return "fail";
		}
		
	}

	/**
	 * Create a baseline build with selected URLS
	 * 
	 * @param basicUrlInfoList
	 * @return if   user has not selected any URLS then it redirects to the crawldUrlPage 
	 *         else go to the Jobs page
	 */
	@RequestMapping(value = "/executeBaseLine", method = RequestMethod.POST)
	public String executeBaseLine(
			@ModelAttribute("basicUrlsfromDB") BasicURLInfoList basicUrlInfoList,
			Model model) {
		List<BasicURLInfo> filteredList = null;
		if (basicUrlInfoList != null && basicUrlInfoList.getBasicUrls() != null
				&& basicUrlInfoList.getBasicUrls().size() > 0) {
			filteredList = new ArrayList<BasicURLInfo>();
			for (BasicURLInfo basic : basicUrlInfoList.getBasicUrls()) {
				// filter only selected URLs
				if (basic.isCkeckbox())
					filteredList.add(basic);
			}
			if (filteredList != null && filteredList.size() > 0) {
				if (basicUrlInfoList.getJobName() != null) {
					executionInterface.takeScreenshots(filteredList,
							basicUrlInfoList.getJobName(), "baseline");
				} else {
					model.addAttribute("basicUrlsfromDB", basicUrlInfoList);
					model.addAttribute("error", "job is not associated");
					return "crawledUrlPage";
					// redirect to the same page with job is not associated
				}
			} else {
				model.addAttribute("basicUrlsfromDB", basicUrlInfoList);
				model.addAttribute("error", "no URLs are selected to baseline");
				return "crawledUrlPage";
				// redirect to the same page with no URLs are selected to
				// baseline
			}

		} else {
			model.addAttribute("basicUrlsfromDB", basicUrlInfoList);
			model.addAttribute("error", "no URLs found");
			return "crawledUrlPage";
			// redirect to the same page with no URLs found.
		}

		return "Jobs";

	}

	/**
	 * Compares the snapshots with a selected baseline build for a job
	 * 
	 * @param jobName
	 * @param baseBuildName
	 * @return true else error
	 */
	@RequestMapping(value = { "/executeCompare" }, method = RequestMethod.POST)
	public @ResponseBody String compare(
			@RequestParam("jobName") String jobName,
			@RequestParam("baselineBuildName") String baseBuildName) {
		if (jobName != null) {
			if (baseBuildName != null) {
				if (executionInterface.compare(jobName, baseBuildName)) {
					return "true";
				}else
				{
				return "not able to compare , check log";
				}
			} else {
				return "no baseline selected";
			}
		} else {
			return "no job associated";
		}
      
	}

	/**
	 * Return all crawled URLS for a particular job
	 * 
	 * @param jobName
	 * @param model
	 * @return Redirect to the crawledUrlPage with crawled URLS
	 */
	@RequestMapping(value = { "/crawledUrls" }, method = RequestMethod.GET)
	public String openCrawledUrlsPage(@RequestParam("jobName") String jobName,
			ModelMap model) {
		BasicURLInfoList basicUrllist = new BasicURLInfoList();
		try {
			basicUrllist.setBasicUrls(executionInterface
					.getCrawlDataByJobName(jobName));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (basicUrllist.getBasicUrls() != null
				&& basicUrllist.getBasicUrls().size() > 0) {
			model.addAttribute("error", "");
			basicUrllist.setJobName(jobName);
			model.addAttribute("basicUrlsfromDB", basicUrllist);
			return "crawledUrlPage";
		} else {
			model.addAttribute("error", "no urls found");
			basicUrllist.setJobName(jobName);
			model.addAttribute("basicUrlsfromDB", basicUrllist);
			return "crawledUrlPage";
		}
	}

	/**
	 * Gets urls for URL Differnce
	 * 
	 * @param jobName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/urlDiffExecute" }, method = RequestMethod.GET)
	public String addUrlsForUrlDiff(@RequestParam("jobName") String jobName,
			ModelMap model) {
		BasicURLInfoList basicUrllist = new BasicURLInfoList();
		BasicURLInfo basic = new BasicURLInfo();
		List<BasicURLInfo> basicList = new ArrayList<BasicURLInfo>();
		basicList.add(basic);
		basicUrllist.setBasicUrls(basicList);
		basicUrllist.setJobName(jobName);
		model.addAttribute("basicUrlsfromDB", basicUrllist);
		return "addUrlDiff";
	}

	/**
	 * getting a list of urls for creating a baseline and redirecting it to jobs
	 * page
	 * */
	@RequestMapping(value = "/executeUrlDiffBaseline", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String urlDiffScreenshot(
			@ModelAttribute("basicUrlsfromDB") BasicURLInfoList basicUrlInfoList) {
		List<BasicURLInfo> filteredList = new ArrayList<BasicURLInfo>();
		if (basicUrlInfoList != null
				&& basicUrlInfoList.getBasicUrls().size() > 0) {
			for (BasicURLInfo basic : basicUrlInfoList.getBasicUrls()) {
				if (!basic.getBasicUrl().isEmpty())
					filteredList.add(basic);
			}
		}
		
		if (filteredList != null && filteredList.size() > 0)
			if (basicUrlInfoList.getJobName() != null)
				executionInterface.takeScreenshots(filteredList,
						basicUrlInfoList.getJobName(), "baseline");
		return "Jobs";
	}
}
