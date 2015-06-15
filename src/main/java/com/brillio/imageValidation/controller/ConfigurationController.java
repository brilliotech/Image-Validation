/**
 * ----------------------------
 * ConfigurationController.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */

package com.brillio.imageValidation.controller;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brillio.imageValidation.entity.Job;
import com.brillio.imageValidation.service.JobConfigurationInterface;

/**
 * Deals with the configuration management of a Job and the initial loading of
 * application. Below shown operations are supported
 * 1)Creation of a Job.
 * 2)Loading the Jobs. 
 * 3)Loading the Reports.
 * 4)Help
 *
 */
@Controller
@Scope("request")
@RequestMapping(value = "/")
public class ConfigurationController {
	/**
	 * Registering to the logger
	 */
	final static Logger logger = Logger
			.getLogger(ConfigurationController.class);

	/**
	 * Service required to save/delete/retrieve the Job
	 */
	private JobConfigurationInterface configurationServcie;

	@Autowired
	public void setConfigurationServcie(
			JobConfigurationInterface configurationServcie) {
		this.configurationServcie = configurationServcie;
	}

	/**
	 * Loads the Job creation page
	 * 
	 * @param model
	 * @return configuration
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String configJob(ModelMap model) {
		model.addAttribute("jobDetails", new Job());
		return "configuration";
	}

	/**
	 * Saves the Job.
	 * 
	 * @param job
	 *            , contains job configuration details like Name,Crawl Level,
	 *            Type, Base URL, Browser type and Threshold.
	 * @return Jobs Detail Page
	 */
	@RequestMapping(value = { "/newJob" }, method = RequestMethod.POST)
	public String createNewJob(@Valid Job job, BindingResult result,
			ModelMap model) {
		String jobName = null;
		logger.info("In Configuration Controller, createNewJob method");
		if (job.getJob_Name() == null) {
			model.addAttribute("error", "job name should not be null");
			model.addAttribute("jobDetails", new Job());

			return "configuration";
		} else{
			jobName = job.getJob_Name();
			job.setJob_Name(jobName.replaceAll(" ", "_"));
		}
		if (configurationServcie.saveJob(job)) {
			model.addAttribute("error", "");
			return "Jobs";
		} else
			model.addAttribute("error",
					"Job was not save, Please look to logs for further information");
		model.addAttribute("jobDetails", new Job());
		return "configuration";
	}

	/**
	 * Checks whether a job is present with the same name or not and returns
	 * true or false.
	 * 
	 * @param jobName
	 * @return name availability
	 */
	@RequestMapping(value = { "/checkJob" }, method = RequestMethod.GET)
	public @ResponseBody Boolean checkJobDetails(
			@RequestParam("jobName") String jobName) {
		if (jobName != null && jobName.length() > 0)
			return configurationServcie.getJobDetailsByJobName(jobName);
		return false;
	}

	/**
	 * Helps in redirecting to creation of job page
	 * 
	 * @param model
	 * @return configuration
	 */
	@RequestMapping(value = { "/CreateJob" }, method = RequestMethod.GET)
	public String createNewJob(ModelMap model) {
		model.addAttribute("jobDetails", new Job());
		return "configuration";
	}

	/**
	 * Helps in redirecting to job detail page
	 * 
	 * @return Jobs
	 */
	@RequestMapping(value = { "/Jobs" }, method = RequestMethod.GET)
	public String openJobsPage() {
		return "Jobs";
	}

	/**
	 * Helps in redirecting to reports page
	 * 
	 * @return Report
	 */
	@RequestMapping(value = { "/Reports" }, method = RequestMethod.GET)
	public String openReportsPage() {
		return "Report";
	}

	/**
	 * Helps in redirecting to help page
	 * 
	 * @return Report
	 */
	@RequestMapping(value = { "/Help" }, method = RequestMethod.GET)
	public String openHelpPage() {
		return "Report";
	}

}
