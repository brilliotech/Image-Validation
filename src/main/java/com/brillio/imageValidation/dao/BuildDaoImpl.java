/**
 * ----------------------------
 * BuildDaoImpl.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */

package com.brillio.imageValidation.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import com.brillio.imageValidation.entity.Build;
import com.brillio.imageValidation.entity.Image_Result;
import com.brillio.imageValidation.entity.Job;
import com.brillio.imageValidation.entity.URL_INFO;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;

/**
 * Manages all Build Data and function like saving and fetching 
 *
 */
@Repository("BuildDaoImpl")
public class BuildDaoImpl extends AbstractDao implements BuildDao {

	final static Logger logger = Logger.getLogger(BuildDaoImpl.class);

	/**
	 * return a list of builds detail for a particular job.
	 * 
	 * @param jobName
	 * @return list of builds.
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public List<Build> getAllBuildDetailsByJobName(String jobName)
			throws ImageValidationException {
		if (jobName != null) {
			List<Build> buildList = new ArrayList<Build>();
			try {
				Criteria criteria1 = getSession().createCriteria(Job.class);
				criteria1.add(Restrictions.eq("job_Name", jobName));
				List<Job> jobList = criteria1.list();
				if (jobList != null && jobList.size() > 0)
					buildList = (List<Build>) jobList.get(0)
							.getJob_Build_Collection();

			} catch (SessionException e) {
				logger.error("session not found exception");
			} catch (HibernateException e) {
				logger.error("hibernate issue " + e);
			}
			if (buildList != null)
				return buildList;
			else {
				throw new ImageValidationException("no builds present");
			}
		} else {
			throw new ImageValidationException("job name is null");
		}
	}

	/**
	 * Save build for a particular job.
	 * 
	 * @param build
	 * @param jobName
	 * @return Boolean
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public Boolean saveBuildDetailsForJob(Build build, String jobName)
			throws ImageValidationException {
		if (jobName != null) {
			try {
				// fire named query
				Query query = getSession().getNamedQuery("findJobByName");
				query.setString("name", jobName);
				List<Job> jobList = query.list();
				if (jobList != null && jobList.size() > 0) {
					if (build != null) {
						Job job = jobList.get(0);
						job.getJob_Build_Collection().add(build);
						update(job);
						logger.info("succesfully save a new build for a job "
								+ jobName);
						return true;
					} else {
						throw new ImageValidationException("build is null");
					}
				} else {
					throw new ImageValidationException("no job found foe job name "
							+ jobName);
				}

			} catch (SessionException e) {
				logger.error("session not found exception");
			} catch (HibernateException e) {
				logger.error("hibernate issue " + e);
			}
		} else {
			throw new ImageValidationException("job name is null");
		}
		return false;
	}

	/**
	 * Return build detail for a particular build
	 * 
	 * @param buildname
	 * @return Build
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public Build getBuildDetailsByBuildName(String buildname)
			throws ImageValidationException {
		if (buildname != null) {
			List<Build> buildList = new ArrayList<Build>();
			try {
				Criteria criteria1 = getSession().createCriteria(Build.class);
				criteria1.add(Restrictions.eq("build_Name", buildname));
				buildList = criteria1.list();
			} catch (SessionException e) {
				logger.error("session not found Exception" + e);
				throw new ImageValidationException("session not found exception");

			} catch (HibernateException e) {
				logger.error("hibernate issue" + e);
				throw new ImageValidationException("hibernate issue");
			}
			if (buildList != null && buildList.size() > 0) {
				return buildList.get(0);
			} else {
				throw new ImageValidationException("no build present with build name: "
						+ buildname);
			}
		} else {
			throw new ImageValidationException("build name is null");
		}
	}
	
	
	/**
	 * Get Build Details for a particular job using build ID.
	 * 
	 * @param buildId
	 * @return Build
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public Build getBuildDetailsByBuildId(int buildId) throws ImageValidationException {
		if (buildId != 0) {
			List<Build> buildList = new ArrayList<Build>();
			try {
				Criteria criteria1 = getSession().createCriteria(Build.class);
				criteria1.add(Restrictions.eq("build_Id", buildId));
				buildList = criteria1.list();
				if (buildList.size() > 0 && buildList != null) {
					return buildList.get(0);
				} else {
					throw new ImageValidationException("no build present with build id:"
							+ buildId);
				}
			} catch (SessionException e) {
				logger.error("session not found Exception" + e);
				throw new ImageValidationException("session not found exception");

			} catch (HibernateException e) {
				logger.error("hibernate issue" + e);
				throw new ImageValidationException("hibernate issue");
			}
		} else {
			throw new ImageValidationException("build ID is null");
		}

	}

	/**
	 * Return all baseline builds for a particular job.
	 * 
	 * @param jobName
	 * @return list of builds.
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public List<Build> getAllBaseLinedBuildByJob(String jobName)
			throws ImageValidationException {
		if (jobName != null) {
			List<Build> buildList = new ArrayList<Build>();
			try {
				DetachedCriteria crit = DetachedCriteria
						.forClass(Job.class)
						.add(Restrictions.eq("job_Name", jobName))
						.createAlias("job_Build_Collection", "tagsAlias")
						.setProjection(
								Projections.property("tagsAlias.build_Id"));
				Criteria criteria2 = getSession().createCriteria(Build.class)
						.add(Restrictions.eq("mode", "baseline"))
						.add(Subqueries.propertyIn("build_Id", crit));
				buildList = criteria2.list();
				if (buildList.size() > 0 && buildList != null) {
					return buildList;
				} else {
					throw new ImageValidationException("no build found");
				}
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
	}

/**
 * Get Urls by build name 
 * 
 * @param buildName
 * @return list of urls
 * @throws ImageValidationException
 */
	@SuppressWarnings("unchecked")
	public List<URL_INFO> getUrlsByBuildName(String buildName) throws ImageValidationException {
		List<URL_INFO> urlList = null;
		if(buildName != null)
		{
		try {
			DetachedCriteria crit = DetachedCriteria.forClass(Build.class)
					.add(Restrictions.eq("build_Name", buildName))
					.createAlias("URL_INFO_Tb_Collection", "tagsAlias")
					.setProjection(Projections.property("tagsAlias.url_id"));
			Criteria criteria2 = getSession().createCriteria(URL_INFO.class)
					.add(Subqueries.propertyIn("url_id", crit));
			urlList = criteria2.list();
			if(urlList != null && urlList.size() > 0)
			{
				return urlList;
			}else
			{
				throw new ImageValidationException("");
			}
		}  catch (SessionException e) {
			logger.error("session not found Exception" + e);
			throw new ImageValidationException("session not found exception");

		} catch (HibernateException e) {
			logger.error("hibernate issue" + e);
			throw new ImageValidationException("hibernate issue");
		}
		}else
		{
			throw new ImageValidationException("build name is null");
		}
	}

	/**
	 * Save Image results by build name
	 * 
	 * @param compareBuildName
	 * @param list
	 *            of image result
	 * @return true, if false
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public Boolean saveImageResultsByBuildName(String compareBuildName,
			List<Image_Result> results) throws ImageValidationException {
		if (compareBuildName != null) {
			try {
				Criteria crit = getSession().createCriteria(Build.class).add(
						Restrictions.eq("build_Name", compareBuildName));
				List<Build> compareBuild = crit.list();
				if (compareBuild.size() > 0 && compareBuild != null) {
					Build tempBuild = compareBuild.get(0);
					if (results.size() > 0 && results != null) {
						tempBuild.setImageResults(results);
						update(tempBuild);
						return true;
					} else {
						throw new ImageValidationException(
								"no compare images detail found");
					}
				} else {
					throw new ImageValidationException("no build found with name "
							+ compareBuildName);
				}
			} catch (SessionException e) {
				logger.error("session not found Exception" + e);
				throw new ImageValidationException("session not found exception");

			} catch (HibernateException e) {
				logger.error("hibernate issue" + e);
				throw new ImageValidationException("hibernate issue");
			}
		}
		return false;
	}

	/**
	 * Return a list of image details for a particular build.
	 * 
	 * @param buildName
	 * @return list of image Result
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public List<Image_Result> getImageResultsOfCompareModeByBuildName(
			String buildName) throws ImageValidationException {
		if (buildName != null) {
			List<Build> builds = new ArrayList<Build>();
			try {
				Criteria criteria = getSession().createCriteria(Build.class)
						.add(Restrictions.eq("build_Name", buildName))
						.add(Restrictions.eq("mode", "compare"));
				builds = criteria.list();
				if (builds != null && builds.size() > 0) {
					List<Image_Result> imageList = (List<Image_Result>) builds
							.get(0).getImageResults();
					if (imageList != null && imageList.size() > 0) {
						return imageList;
					} else {
						throw new ImageValidationException(
								"no image result found for a build "
										+ buildName);
					}
				} else {
					throw new ImageValidationException("no build found ");
				}
			} catch (SessionException e) {
				logger.error("session not found Exception" + e);
				throw new ImageValidationException("session not found exception");

			} catch (HibernateException e) {
				logger.error("hibernate issue" + e);
				throw new ImageValidationException("hibernate issue");
			}
		} else {
			throw new ImageValidationException("build name is null");
		}

	}

	/**
	 * Return a list of all compare mode build for a particular job.
	 * 
	 * @param jobName
	 * @return list of compare builds
	 * @throws ImageValidationException
	 */
	@SuppressWarnings("unchecked")
	public List<Build> getCompareModeBuildDetailsByJobName(String jobName)
			throws ImageValidationException {
		if (jobName != null) {
			List<Build> builds = new ArrayList<Build>();
			try {
				DetachedCriteria crit = DetachedCriteria
						.forClass(Job.class)
						.add(Restrictions.eq("job_Name", jobName))
						.createAlias("job_Build_Collection", "tagsAlias")
						.setProjection(
								Projections.property("tagsAlias.build_Id"));
				Criteria criteria2 = getSession().createCriteria(Build.class)
						.add(Restrictions.eq("mode", "compare"))
						.add(Subqueries.propertyIn("build_Id", crit));
				builds = criteria2.list();
				if (builds != null && builds.size() > 0) {
					return builds;
				} else {
					throw new ImageValidationException("no compare build found");
				}
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
	}

}
