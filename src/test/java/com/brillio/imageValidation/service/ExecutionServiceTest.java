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
public class ExecutionServiceTest extends
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
	 * {@link com.brillio.imageValidation.service.ExecutionService#getBaseLineBuildByJobName(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetBaseLineBuildByJobName() throws ImageValidationException {
		if (buildDao.getAllBaseLinedBuildByJob("Job2").size() >= 1) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.service.ExecutionService#crawlURLS(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testCrawlURLS() throws ImageValidationException {
		if (jobdao.getCrawlDataByJobName("Job2").size() != 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.service.ExecutionService#getBaseLineUrslByBuildName(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetBaseLineUrslByBuildName()
			throws ImageValidationException {
		if (buildDao
				.getUrlsByBuildName("Build-Fri_05.29.2015_at_04:07:23.336PM") != null) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.service.ExecutionService#getCrawlDataByJobName(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetCrawlDataByJobName() throws ImageValidationException {
		if (jobdao.getCrawlDataByJobName("Job2") != null) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

}
