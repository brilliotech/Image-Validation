/**
 * 
 */
package com.brillio.imageValidation.controller;

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
import com.brillio.imageValidation.service.ExecutionInterface;

/**
 * @author himanshu.gawari
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, AppInitializer.class,
		HibernateConfiguration.class })
@WebAppConfiguration
public class ExecutionControllerTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ExecutionInterface executionInterface;

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
	 * {@link com.brillio.imageValidation.controller.ExecutionController#getBaseLinedJobDetailsByJobName(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetBaseLinedJobDetailsByJobName() {
		if (executionInterface.getBaseLineBuildByJobName("Job2").size() > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.controller.ExecutionController#crawlAndSave(java.lang.String)}
	 * .
	 */
	@Test
	public void testCrawlAndSave() {
		if (executionInterface.crawlURLS("Job2").size() > 0) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.controller.ExecutionController#compare(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testCompare() {
		if (executionInterface.compare("Job2",
				"Build-Fri_05.29.2015_at_04:07:23.336PM")) {
			Assert.assertTrue(false);
		} else {
			Assert.assertTrue(true);
		}
	}
}
