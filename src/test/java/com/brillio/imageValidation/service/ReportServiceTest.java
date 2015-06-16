/**
 * 
 */
package com.brillio.imageValidation.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.brillio.imageValidation.configuration.AppConfig;
import com.brillio.imageValidation.configuration.AppInitializer;
import com.brillio.imageValidation.configuration.HibernateConfiguration;
import com.brillio.imageValidation.dao.BuildDao;
import com.brillio.imageValidation.dao.JobDao;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;

/**
 * @author himanshu.gawari
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, AppInitializer.class,
		HibernateConfiguration.class })
@WebAppConfiguration
public class ReportServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private JobDao jobdao;

	@Autowired
	private BuildDao buildDao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.service.ReportService#getAllJobs()}.
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetAllJobs() throws ImageValidationException {
		if (jobdao.getAllJobDetails() != null) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.service.ReportService#getAllBuildDetailsByJobname(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetAllBuildDetailsByJobname()
			throws ImageValidationException {
		if (buildDao.getAllBuildDetailsByJobName("Job2").size() > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.service.ReportService#getCompareModeBuildDetailsByJobname(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetCompareModeBuildDetailsByJobname()
			throws ImageValidationException {
		if (buildDao.getCompareModeBuildDetailsByJobName("Job2") != null) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.service.ReportService#getImageResultDetailsOfCompareModeByBuildName(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetImageResultDetailsOfCompareModeByBuildName()
			throws ImageValidationException {
		if (buildDao
				.getImageResultsOfCompareModeByBuildName("Build-Fri_05.29.2015_at_04:09:21.761PM") != null) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		;
	}

}
