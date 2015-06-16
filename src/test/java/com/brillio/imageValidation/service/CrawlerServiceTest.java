/**
 * 
 */
package com.brillio.imageValidation.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

/**
 * @author himanshu.gawari
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, AppInitializer.class,
		HibernateConfiguration.class })
@WebAppConfiguration
public class CrawlerServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	CrawlerService crawl;

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
	 * {@link com.brillio.imageValidation.service.CrawlerService#onStart()}.
	 */
	@Test
	public void testOnStart() {
		crawl.count = 1;
		crawl.onStart();
		assertEquals(1, crawl.count);
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.service.CrawlerService#visit(edu.uci.ics.crawler4j.crawler.Page)}
	 * .
	 */
	@Test
	public void testVisitPage() {
		Assert.assertEquals(1, crawl.getMyId());
	}

	/**
	 * Test method for
	 * {@link com.brillio.imageValidation.service.CrawlerService#getMyLocalData()}
	 * .
	 */
	@Test
	public void testGetMyLocalData() {

		if (crawl.getMyLocalData() != null) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

}
