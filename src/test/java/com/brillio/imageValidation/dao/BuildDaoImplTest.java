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
public class BuildDaoImplTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private BuildDao builddao;

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
	 * {@link com.brillio.imageValidation.dao.BuildDaoImpl#getAllBuildDetailsByJobName(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetAllBuildDetailsByJobName()
			throws ImageValidationException {
		Assert.assertEquals(10, builddao.getAllBuildDetailsByJobName("Job2")
				.size());
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.dao.BuildDaoImpl#getBuildDetailsByBuildName(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetBuildDetailsByBuildName()
			throws ImageValidationException {
		if (builddao
				.getBuildDetailsByBuildName("Build-Fri_05.29.2015_at_04:07:23.336PM") != null) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.dao.BuildDaoImpl#getBuildDetailsByBuildId(int)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetBuildDetailsByBuildId() throws ImageValidationException {
		Assert.assertEquals("Build-Fri_05.29.2015_at_04:07:23.336PM", builddao
				.getBuildDetailsByBuildId(1).getBuild_Name());
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.dao.BuildDaoImpl#getAllBaseLinedBuildByJob(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetAllBaseLinedBuildByJob() throws ImageValidationException {
		if (builddao.getAllBaseLinedBuildByJob("Job2").size() >= 1) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.dao.BuildDaoImpl#getUrlsByBuildName(java.lang.String)}
	 * .
	 * 
	 * @throws ImageValidationException
	 */
	@Test
	public void testGetUrlsByBuildName() throws ImageValidationException {
		if (builddao.getUrlsByBuildName(
				"Build-Fri_05.29.2015_at_04:07:23.336PM").size() >= 1) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		// fail("Not yet implemented");
	}

}
