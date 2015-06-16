/**
 * 
 */
package com.brillio.imageValidation.dao;

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
import com.brillio.imageValidation.imageValidationException.ImageValidationException;

/**
 * @author himanshu.gawari
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, AppInitializer.class,
		HibernateConfiguration.class })
@WebAppConfiguration
public class JobDaoImplTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private JobDao jobdao;

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
	 * {@link com.brillio.imageValidation.dao.JobDaoImpl#getJobDetailsByName(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetJobDetailsByName() throws ImageValidationException {
		if (jobdao.getJobDetailsByName("Job2") != null) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.dao.JobDaoImpl#getJobDetailsById(int)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetJobDetailsById() throws ImageValidationException {

		Assert.assertEquals("Job3", jobdao.getJobDetailsById(2).getJob_Name()
				.toString());

	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.dao.JobDaoImpl#getAllJobDetails()}.
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetAllJobDetails() throws ImageValidationException {
		if (jobdao.getAllJobDetails() != null) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.dao.JobDaoImpl#getCrawlDataByJobName(java.lang.String)}
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetCrawlDataByJobName() throws ImageValidationException {
		if (jobdao.getCrawlDataByJobName("Job2").size() >= 1) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

}
