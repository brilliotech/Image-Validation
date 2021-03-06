package com.brillio.imageValidation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import com.brillio.imageValidation.dao.JobDao;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;
import com.brillio.imageValidation.service.JobConfigurationInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class,
		AppConfig.class, AppInitializer.class })
@WebAppConfiguration
public class JobTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private JobDao jobdao;

	@Autowired
	private JobConfigurationInterface configurationServcie;

	@Test
	public void testDaoLayer() throws ImageValidationException {
		assertEquals(16, jobdao.getAllJobDetails().size());
	}

	@Test
	public void testServiceLayer() throws ImageValidationException {
		assertTrue(configurationServcie.getJobDetailsByJobName("Job2"));

	}
}
