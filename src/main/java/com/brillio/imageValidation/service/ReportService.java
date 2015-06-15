/**
 * ----------------------------
 *  ReportService.java
 *   ---------------------------- 
 *  (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */

package com.brillio.imageValidation.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



import com.brillio.imageValidation.dao.BuildDao;
import com.brillio.imageValidation.dao.JobDao;
import com.brillio.imageValidation.entity.Build;
import com.brillio.imageValidation.entity.Image_Result;
import com.brillio.imageValidation.entity.Job;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;
import com.brillio.imageValidation.model.AllBuild;
import com.brillio.imageValidation.model.AllJobs;
import com.brillio.imageValidation.model.BuildModel;
import com.brillio.imageValidation.model.ImageDetails;
import com.brillio.imageValidation.model.JobModel;

/**
 * ReportService used to interact with database
 */
@Component
@Scope("session")
/**
 *  services related to report page
 * */
public class ReportService implements ReportInterface{
	final static Logger logger = Logger.getLogger(ReportService.class);

	private JobDao jobdao;

	/**
	 * set JobDao
	 * 
	 * @param jobdao
	 */
	@Autowired
	public void setJobdao(JobDao jobdao) {
		this.jobdao = jobdao;
	}

	private BuildDao buildDao;

	
	/**
	 * set BuildDao
	 * 
	 * @param buildDao
	 */
	@Autowired
	public void setBuildDao(BuildDao buildDao) {
		this.buildDao = buildDao;
	}

	/**
	 * Return a list of jobs which are present in database.
	 * 
	 * @return AllJobs
	 */
	@Transactional
	public AllJobs getAllJobs() {
		SimpleDateFormat ft = new SimpleDateFormat(
				"E MM.dd.yyyy 'at' hh:mm:ss a");
		List<Job> allJobDetails = null;
		try {
			allJobDetails = new ArrayList<Job>();
			allJobDetails = jobdao.getAllJobDetails();
		} catch (ImageValidationException e) {
			logger.error(e.getmessage());
		}
		AllJobs jobs = new AllJobs();
		List<JobModel> jobmodellist = new ArrayList<JobModel>();
		if (allJobDetails != null && allJobDetails.size() > 0) {
			for (Job job : allJobDetails) {
				JobModel model = new JobModel();
				model.setName(job.getJob_Name());
				model.setTime(ft.format(job.getJob_Timestamp()));
				model.setBaseUrl(job.getBase_url());
				model.setCrawlLevel(job.getCrawl_level());
				model.setJobType(job.getJob_type());
				model.setThreshold(job.getThreshold());
				jobmodellist.add(model);
			}
			jobs.setJobDetails(jobmodellist);
			return jobs;
		} else {
			logger.info("no job available in database");
		}
		return jobs;
	}

	/**
	 * Return a list of builds for a particular job
	 * 
	 * @param jobName
	 * @return list of builds
	 */
	@Transactional
	public AllBuild getAllBuildDetailsByJobname(String jobName) {
		List<Build> buildDetails = null;
		try {
			buildDetails = buildDao.getAllBuildDetailsByJobName(jobName);
		} catch (ImageValidationException e) {
			logger.error(e.getmessage());
		}
		AllBuild allBuild = new AllBuild();
		List<BuildModel> buildModellist = new ArrayList<BuildModel>();
		SimpleDateFormat ft = new SimpleDateFormat(
				"E MM.dd.yyyy 'at' hh:mm:ss a");
		if (buildDetails != null && buildDetails.size() > 0) {
			for (Build build : buildDetails) {
				BuildModel model = new BuildModel();
				model.setId(build.getBuild_Id());
				model.setName(build.getBuild_Name());
				model.setMode(build.getMode());
				model.setStatus(build.getBuild_Status());
				model.setTime(ft.format(build.getBuild_TimeStamp()));
				buildModellist.add(model);
			}
			allBuild.setBuildDetails(buildModellist);
			return allBuild;
		} else {
			logger.error("no build present");
		}
		return allBuild;
	}

	/**
	 * Return a list of compare build for a particular job
	 * 
	 * @param jobName
	 * @return list of compare builds
	 */
	@Transactional
	public AllBuild getCompareModeBuildDetailsByJobname(String jobName) {
		List<Build> buildDetails = null;
		try {
			buildDetails = buildDao
					.getCompareModeBuildDetailsByJobName(jobName);
		} catch (ImageValidationException e) {
			logger.error(e.getmessage());
		}
		AllBuild allBuild = new AllBuild();
		List<BuildModel> buildModellist = new ArrayList<BuildModel>();
		SimpleDateFormat ft = new SimpleDateFormat(
				"E MM/dd/yyyy 'at' hh:mm:ss a");
		if (buildDetails != null && buildDetails.size() > 0) {
			for (Build build : buildDetails) {
				BuildModel model = new BuildModel();
				model.setId(build.getBuild_Id());
				model.setName(build.getBuild_Name());
				model.setMode(build.getMode());
				model.setStatus(build.getBuild_Status());
				model.setTime(ft.format(build.getBuild_TimeStamp()));
				buildModellist.add(model);
			}
			allBuild.setBuildDetails(buildModellist);
			return allBuild;
		} else {
			logger.error("no compare build found");
		}
		return allBuild;
	}


	/**
	 * Returns a list of image result details for a particular build
	 * 
	 * @param buildName
	 * @param jobname
	 * @return
	 */
	@Transactional
	public List<ImageDetails> getImageResultDetailsOfCompareModeByBuildName(
			String buildName, String jobname) {
		List<ImageDetails> tempImageList = null;
		if (buildName != null && jobname != null) {
			tempImageList = new ArrayList<ImageDetails>();
			List<Image_Result> imageDetailsList = null;
			Job job = null;
			try {
				imageDetailsList = buildDao
						.getImageResultsOfCompareModeByBuildName(buildName);
				job = jobdao.getJobDetailsByName(jobname);
			} catch (ImageValidationException e) {
				logger.error(e.getmessage());
			}

			if (imageDetailsList != null && imageDetailsList.size() > 0) {
				for (Image_Result image_Result : imageDetailsList) {
					ImageDetails imgDetail = new ImageDetails();
					imgDetail.setActualImage(image_Result.getActualImagePath());
					imgDetail.setDifference(image_Result
							.getDiffernce_ImagePath());
					imgDetail.setExpectedImage(image_Result
							.getExpectedImagePath());
					imgDetail.setStatus(image_Result.getStatus());
					imgDetail.setUrl_name(image_Result.getUrl_name());
					imgDetail.setDiffPercent(image_Result.getPercentage());
					imgDetail.setThreshold(job.getThreshold());
					tempImageList.add(imgDetail);
				}
			}
		}
		return tempImageList;
	}

}
