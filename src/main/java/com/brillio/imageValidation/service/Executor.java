/**
 * ----------------------------
 *  Executor.java
 *   ---------------------------- 
 *  (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.brillio.imageValidation.model.BasicURLInfo;

/**
 * Takes screenshot using multithreading and saving the images
 */
public class Executor implements Callable<BasicURLInfo> {

	BasicURLInfo urlDetailsObject;

	public static int rand = 1;

	/**
	 * 
	 * Deals with setting capabality, taking screenshot and saving images
	 * 
	 * */
	public BasicURLInfo call() throws Exception {
		WebDriver driver = null;

		try {
			System.out.println("user agent browser****************"
					+ urlDetailsObject.getUserAgent());
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setJavascriptEnabled(true);
			caps.setCapability("takesScreenshot", true);
			// setting phantomjs path
			caps.setCapability(
					PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					System.getProperty("catalina.base")+"\\webapps\\Image-Validation\\resources\\lib\\phantomjs.exe");
			// setting user agent
			caps.setCapability("phantomjs.page.settings.userAgent",
					urlDetailsObject.getUserAgent());
			caps.setCapability("phantomjs.page.customHeaders.Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			caps.setCapability("phantomjs.page.customHeaders.Accept-Language",
					"en-GB,en-US;q=0.8,en;q=0.6");
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
					new String[] { "--web-security=false",
							"--ssl-protocol=any", "--ignore-ssl-errors=true" });

			driver = new PhantomJSDriver(caps);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

			driver.get(urlDetailsObject.getBasicUrl());

		
			String imgname = Integer.toString(rand).concat(".png");
			rand++;
			String path = urlDetailsObject.getPath() + imgname;
			System.out.println();
			String temp = "C:".concat(path);
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(temp));
				urlDetailsObject.setPath(path);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null) {			
				driver.quit();
			}
		}
		return urlDetailsObject;
	}

	/**
	 * Constructor
	 * 
	 * @param urlDetails
	 */
	public Executor(BasicURLInfo urlDetails) {
		urlDetailsObject = urlDetails;
	}

}
