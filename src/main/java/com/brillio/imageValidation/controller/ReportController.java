/**
 * ----------------------------
 * ReportController.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */

package com.brillio.imageValidation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brillio.imageValidation.model.AllBuild;
import com.brillio.imageValidation.model.AllJobs;
import com.brillio.imageValidation.model.ImageDetails;
import com.brillio.imageValidation.service.ReportInterface;

/**
 * Deals with the Report management of a Job. Below shown operations are supported 
 * 1)Get All Job Details.
 * 2)Get All Builds of a Jobs. 
 * 3)Get Compared Build of a Job. 
 * 4)Get List of Image Results of a Build.
 *
 */

@Controller
@Scope("request")
public class ReportController {
	private ReportInterface reportInterface;

	/**
	 * Sets Service 
	 * 
	 * @param reportInterface
	 */
	@Autowired
	public void setReportService(ReportInterface reportInterface) {
		this.reportInterface = reportInterface;
	}

	/**
	 * Give all jobs details
	 * */
	@RequestMapping(value = { "/allJobs" }, method = RequestMethod.GET)
	public @ResponseBody AllJobs getAllJobDetails() {
		AllJobs jobDetails = reportInterface.getAllJobs();
		return jobDetails;
	}

	/**
	 * return all the builds for a job
	 * */
	@RequestMapping(value = { "/allBuilds" }, method = RequestMethod.GET)
	public @ResponseBody AllBuild getAllBuildDetailsByJobName(
			@RequestParam("jobname") String jobName) {
		AllBuild allBuils = reportInterface.getAllBuildDetailsByJobname(jobName);
		return allBuils;
	}

	/**
	 * return only compared build for a job
	 * */
	@RequestMapping(value = { "/allCompareBuilds" }, method = RequestMethod.GET)
	public @ResponseBody AllBuild getCompareModeBuildDetailsByJobName(
			@RequestParam("jobname") String jobName) {
		AllBuild allBuils = reportInterface
				.getCompareModeBuildDetailsByJobname(jobName);
		return allBuils;
	}

	/**
	 * return a list of image results for a build
	 * */
	@RequestMapping(value = { "/allImageResults" }, method = RequestMethod.GET)
	public @ResponseBody List<ImageDetails> getResultbybuildname(
			@RequestParam("buildName") String buildName,
			@RequestParam("jobName") String jobName) {
		List<ImageDetails> imageList = reportInterface
				.getImageResultDetailsOfCompareModeByBuildName(buildName,
						jobName);
		return imageList;
	}

}
