package com.brillio.imageValidation;

import static org.junit.Assert.assertEquals;

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
import com.brillio.imageValidation.imageValidationException.ImageValidationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class,
		AppConfig.class, AppInitializer.class })
@WebAppConfiguration
public class BuildTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private BuildDao builddao;

	@Test
	public void testDaoLayer() throws ImageValidationException {
		assertEquals(16, builddao.getAllBaseLinedBuildByJob("Job2").size());
	}

	@Test
	public void testDao() throws ImageValidationException {
		assertEquals("Build-Fri_05.29.2015_at_04:07:23.336PM",
				builddao.getBuildDetailsByBuildId(1).getBuild_Name());

	}

	@Test
	public void testDaoBuild() throws ImageValidationException {
		assertEquals("Build-Fri_05.29.2015_at_04:07:23.336PM",
				builddao.getBuildDetailsByBuildId(2).getBuild_Name());

	}
}
