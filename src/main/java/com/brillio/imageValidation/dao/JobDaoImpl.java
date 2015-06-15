/**
 * ----------------------------
 * JobDaoImpl.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */

package com.brillio.imageValidation.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brillio.imageValidation.entity.CrawlData;
import com.brillio.imageValidation.entity.Job;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;

/**
 * 
 * Manages all Job data and functions
 *
 */
@Repository("JobDao")
public class JobDaoImpl extends AbstractDao implements JobDao {
	final static Logger logger = Logger.getLogger(JobDaoImpl.class);

	/**
	 * Save a job.
	 * 
	 * @param job
	 * @return Success or error message.
	 * @throws ImageValidationException
	 */
	public Boolean saveJob(Job job) throws ImageValidationException {
		try {
			if (job != null) {
				if (!job.getJob_Name().equals("")
						|| job.getJob_Name().length() > 0) {
					Date date = new Date();
					job.setJob_Timestamp(new Timestamp(date.getTime()));
					persist(job);
					System.out
							.println("save job successfully*****************");
					return true;
				} else {
					throw new ImageValidationException("please insert job details again");
				}
			}
		} catch (SessionException sessionnotfound) {
			logger.error("session not found exception" + sessionnotfound);
			throw new ImageValidationException(
					"session not found exception, job not saved please check log file");
		} catch (HibernateException e) {
			logger.error("hibernate exception " + e);
			throw new ImageValidationException(
					"hibernate exception, job not saved please check log file");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	/**
	 * return job details for a particular job.
	 * 
	 * @param jobname
	 *            .
	 * @return job.
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public Job getJobDetailsByName(String jobName) throws ImageValidationException {
		if (jobName != null) {
			Criteria criteria = getSession().createCriteria(Job.class);
			criteria.add(Restrictions.eq("job_Name", jobName));
			List<Job> jobList = criteria.list();
			if (jobList.isEmpty()) {
				logger.info("no job for job name :" + jobName);
				throw new ImageValidationException("no job available");
			} else {
				logger.info("job details found  for job name :" + jobName);
				try {
					return jobList.get(0);
				} catch (ArrayIndexOutOfBoundsException e) {
					logger.error("no job found " + e);
					throw new ImageValidationException("no job found");
				}
			}
		} else {
			logger.error("custom exception occur ");
			throw new ImageValidationException("job name not found");
		}
	}

	/**
	 * return job details for a particular job.
	 * 
	 * @param job
	 *            Id.
	 * @return job.
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public Job getJobDetailsById(int jobID) throws ImageValidationException {
		try {
			if (jobID != 0) {
				Criteria criteria = getSession().createCriteria(Job.class);
				criteria.add(Restrictions.eq("job_Id", jobID));
				List<Job> jobList = criteria.list();
				if (jobList.isEmpty()) {
					throw new ImageValidationException("no job present with job id "
							+ jobID);
				} else {
					try {
						return jobList.get(0);
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new ImageValidationException("no job present with job id "
								+ jobID);
					}

				}
			} else {
				throw new ImageValidationException("job id not found");
			}
		} catch (SessionException sessionnotfound) {

			logger.error("session not found exception" + sessionnotfound);
			throw new ImageValidationException("session not found exception");

		} catch (HibernateException e) {
			logger.error("hibernate exception " + e);
			throw new ImageValidationException("hibernate exception");
		}
	}

	/**
	 * return all the job details which are present in database.
	 * 
	 * @return list of job.
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public List<Job> getAllJobDetails() throws ImageValidationException {
		List<Job> jobDetails = new ArrayList<Job>();
		try {
			Criteria criteria = getSession().createCriteria(Job.class);
			jobDetails = criteria.list();
			if (jobDetails != null) {
				logger.info("size of the list of all jobs " + jobDetails.size());
				return jobDetails;
			} else {
				throw new ImageValidationException("no job available");
			}
		} catch (SessionException e) {
			logger.error("session not found or sesszion not created Exception: "
					+ e);
			throw new ImageValidationException("session not found exception");
		} catch (HibernateException e) {
			logger.error("hibernate exception :" + e);
			throw new ImageValidationException("hibernate issue");
		}

	}

	/**
	 * Save crawled data for a particular job.
	 * 
	 * @param jobName
	 * @return if true, successfully saved crawled URLS false, unsuccessful in
	 *         false, saving crawled URLS
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public Boolean saveCrawlDataByJobName(String jobName, List<String> urls)
			throws ImageValidationException {
		if (jobName != null) {
			List<CrawlData> crawledUrlList = null;
			if (urls != null && urls.size() > 0) {
				crawledUrlList = new ArrayList<CrawlData>();
				for (String url : urls) {
					CrawlData crawledUrl = new CrawlData();
					crawledUrl.setUrl(url);
					crawledUrlList.add(crawledUrl);
				}
			}
			try {
				Criteria criteria = getSession().createCriteria(Job.class);
				criteria.add(Restrictions.eq("job_Name", jobName));
				List<Job> jobList = criteria.list();
				if (jobList != null && jobList.size() > 0) {
					Job job = jobList.get(0);
					if (crawledUrlList != null && crawledUrlList.size() > 0) {
						job.setJob_crawl_data(crawledUrlList);
						getSession().saveOrUpdate(job);
						logger.info("successfully saved crawled data");
						return true;
					} else {
						throw new ImageValidationException("no crawled urls found");
					}
				}
			} catch (SessionException e) {
				logger.error("session not found Exception , unsuccefull in updating job");
				throw new ImageValidationException("session not found exception");
			} catch (HibernateException e) {
				logger.error("hibernate exception :" + e);
				throw new ImageValidationException("hibernate issue");
			}
		} else {
			throw new ImageValidationException("job name is null");
		}
		return false;
	}

	/**
	 * Return a list of crawled URLS for a particular job.
	 * 
	 * @param jobName
	 * @return list of crawled URLS
	 * @throws ImageValidationException
	 */
	@SuppressWarnings({ "unchecked" })
	public List<CrawlData> getCrawlDataByJobName(String jobname)
			throws ImageValidationException {
		List<Job> jobList = null;
		if (jobname != null) {
			jobList = new ArrayList<Job>();
			try {
				Criteria criteria = getSession().createCriteria(Job.class);
				criteria.add(Restrictions.eq("job_Name", jobname));
				jobList = criteria.list();
			} catch (SessionException e) {
				logger.error("session not found Exception" + e);
				throw new ImageValidationException("session not found exception");

			} catch (HibernateException e) {
				logger.error("hibernate issue" + e);
				throw new ImageValidationException("hibernate issue");
			}
		} else {
			throw new ImageValidationException("job name is null");
		}
		if (jobList == null) {
			logger.info("no crawled urls are present for job :" + jobname);
			throw new ImageValidationException("no job found ");
		} else {
			return (List<CrawlData>) jobList.get(0).getJob_crawl_data();
		}

	}

}
