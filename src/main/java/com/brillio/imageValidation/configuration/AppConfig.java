/**
 * ----------------------------
 * AppConfig.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.configuration;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Deals with the Application configuration management of loading application.
 * Below shown operations are executed 
 * 1)View Resolver 
 * 2)Resource Handlers
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.brillio.imageValidation")
public class AppConfig extends WebMvcConfigurerAdapter {

	/**
	 * Returns a View Resolver
	 * 
	 * @return viewResolver
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * Return a Message source
	 * 
	 * @return messageSource
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	/**
	 * Handles Resources and set cache period
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**")
				.addResourceLocations("/resources/styles/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/img/**")
				.addResourceLocations("/resources/images/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/localImg/**")
				.addResourceLocations("file:///C:/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**")
				.addResourceLocations("/resources/scripts/")
				.setCachePeriod(31556926);
	}

	/**
	 * Configure Default Server Handler
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
