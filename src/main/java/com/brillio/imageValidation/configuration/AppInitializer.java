/**
 * ----------------------------
 * AppInitializer.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */

package com.brillio.imageValidation.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Deals with the Application Initialization management. Below shown operations
 * are executed 
 * 1)Manage the life cycle of the root application context
 * 2)Dispatcher Handling
 */

public class AppInitializer implements WebApplicationInitializer {

	/**
	 * On startup initializes Context, assign listener  and handles dispatcher
	 */
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);

		container.addListener(new ContextLoaderListener(rootContext));

		rootContext.setServletContext(container);
		
		ServletRegistration.Dynamic servlet = container.addServlet(
				"dispatcher", new DispatcherServlet(rootContext));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

}
