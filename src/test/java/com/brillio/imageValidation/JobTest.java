package com.brillio.imageValidation;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.brillio.imageValidation.configuration.HibernateConfiguration;
import com.brillio.imageValidation.dao.JobDao;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;
import com.brillio.imageValidation.service.JobConfigurationInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={HibernateConfiguration.class})
@WebAppConfiguration
public class JobTest extends AbstractTransactionalJUnit4SpringContextTests
 { 

	
	private JobDao jobdao;
	
	@Autowired
	public void setJobdao(JobDao jobdao) {
		this.jobdao = jobdao;
	}
	
	private JobConfigurationInterface configurationServcie;

	@Autowired
	public void setConfigurationServcie(
			JobConfigurationInterface configurationServcie) {
		this.configurationServcie = configurationServcie;
	}
	
	@Test
	public void testDaoLayer() throws ImageValidationException {
		Assert.assertEquals(16, jobdao.getAllJobDetails().size());
	}
	
	@Test
	public void testServiceLayer() throws ImageValidationException {
		Assert.assertTrue(configurationServcie.getJobDetailsByJobName("test"));
		
	}
}
