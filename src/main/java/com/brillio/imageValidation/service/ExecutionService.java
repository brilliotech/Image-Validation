/**
 * ----------------------------
 * ExecutionService.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.im4java.process.ProcessStarter;
import org.jdom.IllegalDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.brillio.imageValidation.dao.BuildDao;
import com.brillio.imageValidation.dao.JobDao;
import com.brillio.imageValidation.entity.Build;
import com.brillio.imageValidation.entity.CrawlData;
import com.brillio.imageValidation.entity.Image_Result;
import com.brillio.imageValidation.entity.Job;
import com.brillio.imageValidation.entity.URL_INFO;
import com.brillio.imageValidation.imageValidationException.ImageValidationException;
import com.brillio.imageValidation.model.BasicURLInfo;
import com.brillio.imageValidation.model.CrawlURL;
import com.brillio.imageValidation.model.ImageDetails;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * 
 * ExecutionService deals with 
 * 1)Crawl of urls
 * 2)Taking Screenshots of the urls
 * 3)Comapring the images
 *
 */
@Component
@Scope("session")
public class ExecutionService implements ExecutionInterface{
	final static Logger logger = Logger.getLogger(ExecutionService.class);

	public static int rand = 1;

	private JobDao jobdao;

	/**
	 * set JobDao
	 * 
	 * @param jobdao
	 */
	@Autowired
	public void setJobdao(JobDao jobdao) {
		this.jobdao = jobdao;
	}

	private BuildDao buildDao;

	/**
	 * set BuildDao
	 * 
	 * @param buildDao
	 */
	@Autowired
	public void setBuildDao(BuildDao buildDao) {
		this.buildDao = buildDao;
	}

	/**
	 * get baseline build by job name
	 * 
	 * @param jobName
	 * @return
	 */
	@Transactional
	public List<Build> getBaseLineBuildByJobName(String jobName) {
		try {
			return buildDao.getAllBaseLinedBuildByJob(jobName);
		} catch (ImageValidationException e) {
			logger.error(e.getmessage());
			List<Build> buildList = null;
			return buildList;
		}
	}

	/**
	 * List of crawl urls
	 * 
	 * @param jobname
	 * @return list of crawled URLS
	 */
	@Transactional
	public List<String> crawlURLS(String jobname) {
		List<String> urlList = null;
		try {
			List<CrawlData> crawledDataList = null;
			crawledDataList = jobdao.getCrawlDataByJobName(jobname);
			if (crawledDataList != null && crawledDataList.size() > 0) {
				logger.info("crawled data already present ");
				urlList = new ArrayList<String>();
				for (CrawlData crawlData : crawledDataList) {
					urlList.add(crawlData.getUrl());
					logger.info("crawled url name: " + crawlData.getUrl());
				}
				return urlList;
			} else {
				Job job = null;
				try {
					job = jobdao.getJobDetailsByName(jobname);
				} catch (ImageValidationException e) {
					logger.error(e.getmessage());
				}
				if (job.getBase_url() != null) {

					urlList = crawlSitesService(job.getBase_url(),
							job.getCrawl_level());
				}
				return urlList;
			}
		} catch (Exception e) {

		}
		return urlList;
	}

	/**
	 * save crawl data by job name
	 * 
	 * @param name
	 * @param urls
	 * @return
	 */
	@Transactional
	public Boolean saveCrawlDataByJobName(String name, List<String> urls) {
		try {
			return jobdao.saveCrawlDataByJobName(name, urls);
		} catch (ImageValidationException e) {
			logger.error(e.getmessage());
			return false;
		}
	}

	/**
	 * return url list based on base url and crawl level
	 * 
	 * @param baseurl
	 * @param crawlLevel
	 * @return
	 */
	private List<String> crawlSitesService(String baseurl, int crawlLevel) {
		List<String> UrlList = new ArrayList<String>();

		UrlList = crawlSites(baseurl, crawlLevel);
		return UrlList;
	}

	

	/**
	 * get results of Images compare
	 * 
	 * @param jobName
	 * @param baseBuildName
	 * @return true, if successfully compare the builds
	 *         false,if any error occurs
	 */
	@Transactional
	public Boolean compare(String jobName, String baseBuildName) {
		logger.info("comparing build with baseline build name " + baseBuildName);
		// Get the urls for the baseline build
		Boolean result = false;
		List<URL_INFO> baseUrlsList = null;
		List<Image_Result> imageResults = new ArrayList<Image_Result>();
		baseUrlsList = getBaseLineUrslByBuildName(baseBuildName);
		List<BasicURLInfo> comparedUrlsList = new ArrayList<BasicURLInfo>();
		if (baseUrlsList != null && baseUrlsList.size() > 0) {
			for (URL_INFO url_infoList : baseUrlsList) {
				BasicURLInfo basicURLInfo = new BasicURLInfo();
				basicURLInfo.setBasicUrl(url_infoList.getUrl_name());
				basicURLInfo.setDomain(url_infoList.getUrl_domain());
				basicURLInfo.setTag(url_infoList.getTag());
				comparedUrlsList.add(basicURLInfo);
				logger.info("baseline build url : "
						+ basicURLInfo.getBasicUrl());
			}
			// Take the screen shot again for each url, grouping under a new
			// build with mode as compare
			String buildName = takeScreenshots(comparedUrlsList, jobName,
					"compare");
			Job job = getJobDetailsByJobName(jobName);

			// Now get the compare build urls
			String tempBuildName = buildName.replace(":", ".");
			String tempPath = "/Image-Validation/" + jobName + "/"
					+ tempBuildName + "/compared_image/";
			try {
				File file = new File("C:" + tempPath);
				file.mkdirs();
			} catch (Exception e) {
				logger.info("failed to create a compared image folder inside a compare build");
			}
			List<URL_INFO> compareUrlsList = getBaseLineUrslByBuildName(buildName);

			// Compare the urls and store the results.
			List<ImageDetails> imageResultList = new ArrayList<ImageDetails>();
			if (compareUrlsList != null && compareUrlsList.size() > 0)
				if (baseUrlsList != null && baseUrlsList.size() > 0) {
					for (int i = 0; i < baseUrlsList.size(); i++) {
						ImageDetails imageDetails = new ImageDetails();
						imageDetails.setActualImage(compareUrlsList.get(i)
								.getImagePath());
						imageDetails.setExpectedImage(baseUrlsList.get(i)
								.getImagePath());
						imageDetails.setActualImageId(compareUrlsList.get(i)
								.getUrl_id());
						imageDetails.setExpectedImageId(baseUrlsList.get(i)
								.getUrl_id());
						// Have to get the threshold for a job
						imageDetails.setThreshold(job.getThreshold());
						imageDetails.setTempDiffImagePath(tempPath + i);
						imageDetails.setUrl_name(baseUrlsList.get(i)
								.getUrl_name());
						try {
							imageResultList.add(compareImages(imageDetails));
						} catch (Exception e) {
							e.printStackTrace();
						}
						logger.info("for comparing url: "
								+ imageDetails.getUrl_name());
					}
				}
			try {
				if (imageResultList != null && imageResultList.size() > 0) {
					for (ImageDetails imageDetail : imageResultList) {
						Image_Result imageResult = new Image_Result();
						imageResult.setActual_Image_id(imageDetail
								.getActualImageId());
						imageResult.setExpected_Image_id(imageDetail
								.getExpectedImageId());
						imageResult.setActualImagePath(imageDetail
								.getActualImage());
						imageResult.setExpectedImagePath(imageDetail
								.getExpectedImage());
						imageResult.setDiffernce_ImagePath(imageDetail
								.getDifference());
						imageResult.setStatus(imageDetail.getStatus());
						imageResult.setUrl_name(imageDetail.getUrl_name());
						imageResult.setPercentage(imageDetail.getDiffPercent());
						if (imageResult != null)
							imageResults.add(imageResult);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (imageResults.size() > 0 && imageResults != null) {
					// Save the Image Results for the build
					result = saveImageResults(buildName, imageResults);
				}
			}
			return result;
		}
		return false;
	}

	/**
	 * Return a list of URLS for a particular build
	 * 
	 * @param buildName
	 * @return list of URLS
	 */
	@Transactional
	public List<URL_INFO> getBaseLineUrslByBuildName(String buildName) {

		try {
			return buildDao.getUrlsByBuildName(buildName);
		} catch (ImageValidationException e) {
			logger.error(e.getmessage());
			List<URL_INFO> list = null;
			return list;
		}

	}

	/**
	 * Takes screenshots
	 * 
	 * @param urlDetailsList
	 * @param jobName
	 * @param mode
	 * @return build name
	 */
	@Transactional
	public String takeScreenshots(List<BasicURLInfo> urlDetailsList,
			String jobName, String mode) {
		String buildName = null;
		String path = null;
		try {
			if (jobName != null && urlDetailsList.size() > 0
					&& urlDetailsList != null) {
				Map<String, String> browsers = new HashMap<String, String>();
				browsers.put(
						"Safari",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/600.3.10 (KHTML, like Gecko) Version/8.0.3 Safari/600.3.10");
				browsers.put(
						"Chrome",
						"Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.111 Safari/537.36");
				browsers.put("Firefox",
						"Mozilla/5.0 (Windows NT 6.2; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0");
				browsers.put(
						"ie",
						"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.2; WOW64; Trident/6.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729)");
				Job job = null;
				try {
					job = jobdao.getJobDetailsByName(jobName);
				} catch (ImageValidationException e1) {
					logger.error(e1.getmessage());
				}
				if (job != null) {
					String userAgent = job.getUserAgent();
					logger.info("selected user agent for job "
							+ job.getJob_Name() + " is " + job.getUserAgent());

					Date date = new Date();
					SimpleDateFormat ft = new SimpleDateFormat(
							"E_MM.dd.yyyy_'at'_hh:mm:ss.SSSa");
					buildName = "Build-"
							+ ft.format(new Timestamp(date.getTime()));
					String tempBuildName = buildName.replace(":", ".");
					if (mode.equalsIgnoreCase("baseline")) {
						path = "/Image-Validation/" + jobName + "/"
								+ tempBuildName + "/Expected_image/image";
					} else if (mode.equalsIgnoreCase("compare")) {
						path = "/Image-Validation/" + jobName + "/"
								+ tempBuildName + "/Actual-image/image";
					}
					int i = 1;
					List<BasicURLInfo> basicUrlList = new ArrayList<BasicURLInfo>();
					for (BasicURLInfo basicURLInfo : urlDetailsList) {
						basicURLInfo.setPath(path + i);
						basicURLInfo.setUserAgent(browsers.get(userAgent));
						i++;
						basicUrlList.add(basicURLInfo);
					}
					List<Future<BasicURLInfo>> ScreenshotList = null;
					List<Executor> mycallableList = new ArrayList<Executor>();
					for (BasicURLInfo urlDetails : basicUrlList) {
						Executor mycallable = new Executor(urlDetails);
						mycallableList.add(mycallable);
					}
					ExecutorService parallelExecutor = null;

					parallelExecutor = Executors.newFixedThreadPool(5);
					ScreenshotList = parallelExecutor.invokeAll(mycallableList);
					// Convert to BasicURLInfo
					if (ScreenshotList != null && ScreenshotList.size() > 0) {
						List<URL_INFO> urls = new ArrayList<URL_INFO>();
						for (Future<BasicURLInfo> future : ScreenshotList) {
							URL_INFO url = new URL_INFO();
							url.setUrl_name(future.get().getBasicUrl());
							url.setTag(future.get().getTag());
							url.setUrl_domain(future.get().getDomain());
							url.setImagePath(future.get().getPath());
							urls.add(url);
						}

						// Set the build
						Build buildDetails = new Build();
						buildDetails.setBuild_TimeStamp(new Timestamp(date.getTime()));
						buildDetails.setBuild_Name(buildName);
						buildDetails.setMode(mode);
						buildDetails.setURL_INFO_Tb_Collection(urls);
						buildDao.saveBuildDetailsForJob(buildDetails, jobName);
					}

				}
			}
		} catch (ImageValidationException e) {
			logger.error(e.getMessage());
		} catch (IllegalDataException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} catch (ExecutionException e) {
			logger.error(e.getMessage());
		}
		return buildName;
	}

	/**
	 * Saves image compare results
	 * 
	 * @param compareBuildName
	 * @param results
	 * @return
	 */
	@Transactional
	public Boolean saveImageResults(String compareBuildName,
			List<Image_Result> results) {
		if (results != null) {
			try {
				return buildDao.saveImageResultsByBuildName(compareBuildName,
						results);
			} catch (ImageValidationException e) {
				logger.error(e.getmessage());
				return false;
			}
		} else {
			logger.info("image result is null");
			return false;
		}
	}

	/**
	 * get crawl data by job name
	 * 
	 * @param jobName
	 * @return
	 */
	@Transactional
	public List<BasicURLInfo> getCrawlDataByJobName(String jobName) {

		List<BasicURLInfo> basicUrls = new ArrayList<BasicURLInfo>();

		List<CrawlData> crawledUrls = null;
		try {
			crawledUrls = jobdao.getCrawlDataByJobName(jobName);
		} catch (ImageValidationException e) {
			List<BasicURLInfo> basicUrlList = null;
			return basicUrlList;
		}
		if (crawledUrls != null && crawledUrls.size() > 0) {
			for (CrawlData crawlData : crawledUrls) {
				BasicURLInfo url = new BasicURLInfo();
				url.setBasicUrl(crawlData.getUrl());
				basicUrls.add(url);
			}
		}
		return basicUrls;
	}

	/**
	 * get url differnce baseline
	 * 
	 * @param jobname
	 * @param mode
	 * @param urlDetailsList
	 */
	public void urlDiffBaseline(String jobname, String mode,
			List<BasicURLInfo> urlDetailsList) {
		takeScreenshots(urlDetailsList, jobname, mode);
	}

	/**
	 * wraps the data in to actual URL details list
	 */
	public List<String> crawlSites(String urlString, int crawlLevel) {
		List<String> urlsAddressList = new ArrayList<String>();
		// List<URLDetails> locURLDetailList = new ArrayList<URLDetails>();
		try {
			urlsAddressList = initiateCrawl(urlString, crawlLevel);
		} catch (Exception e) {
		}
		return urlsAddressList;
	}

	/**
	 * Crawler Configuration settings
	 */
	public List<String> initiateCrawl(String url, int crawlLevel) {
		List<Object> tempCrawledList = new ArrayList<Object>();
		String crawlStorageFolder = "\\crawldata\\";
		int numberOfCrawlers = 15;
		CrawlConfig crawlConfig = new CrawlConfig();
		crawlConfig.setCrawlStorageFolder(crawlStorageFolder);
		crawlConfig.setPolitenessDelay(500);
		crawlConfig.setMaxDepthOfCrawling(crawlLevel);
		crawlConfig.setMaxPagesToFetch(-1);
		crawlConfig.setIncludeBinaryContentInCrawling(false);
		System.out.println(crawlConfig.toString());

		PageFetcher pageFetcher = new PageFetcher(crawlConfig);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = null;
		try {
			controller = new CrawlController(crawlConfig, pageFetcher,
					robotstxtServer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> finalCrawledList = new ArrayList<String>();
		if (!url.isEmpty()) {
			controller.addSeed(url);
			controller.start(CrawlerService.class, numberOfCrawlers);
			controller.waitUntilFinish();
			tempCrawledList = controller.getCrawlersLocalData();
		}
		for (Object locList : tempCrawledList) {
			CrawlURL individualCrawlList = (CrawlURL) locList;
			if (individualCrawlList != null) {
				for (String urla : individualCrawlList.getUrl()) {
					finalCrawledList.add(urla);
				}
			}
		}
		if (controller != null) {
			controller.shutdown();
		}
	

		return finalCrawledList;
	}

	/**
	 * Compares Images
	 */
	@SuppressWarnings(value = "unused")
	public ImageDetails compareImages(ImageDetails imageDetails)
			throws Exception {
		// int rand = 1;
		// int rand = ThreadLocalRandom.current().nextInt(1, 10000);
		String imgname = "image" + Integer.toString(rand).concat(".gif");
		String path = imageDetails.getTempDiffImagePath() + imgname;
		String temp_actual_path = imageDetails.getTempDiffImagePath()
				+ "image_resized" + Integer.toString(rand).concat(".png");
		rand++;
		String path1 = null;
		path1 = "C:".concat(imageDetails.getExpectedImage());
		System.out.println();
		path1 = path1.replace("/", "\\");
		String path2 = null;
		path2 = "C:".concat(imageDetails.getActualImage());
		path2 = path2.replace("/", "\\");

		System.out.println(path1 + "   ***   " + path2);
		String path3 = "C:".concat(path);
		double similarity = 0;
		String imMetricLocal = "RMSE";
		String imFuzzLocal = "1";
		String SPACE = " ";
		int VALUE = 100;
		logger.info("imMetric = " + imMetricLocal);
		logger.info("imFuzz = " + imFuzzLocal);

		int height1 = 0;
		int width1 = 0;
		int height2 = 0;
		int width2 = 0;

		Info imageInfo = new Info(path1, true);
		height1 = imageInfo.getImageHeight();
		width1 = imageInfo.getImageWidth();

		Info imageInfo1 = new Info(path2, true);
		height2 = imageInfo1.getImageHeight();
		width2 = imageInfo1.getImageWidth();
		String image1 = null;
		String image2 = null;

		if (height1 <= height2) {
			image1 = path1;
			image2 = path2;
		} else {
			image1 = path2;
			image2 = path1;
		}

		try {
			Runtime runtime1 = Runtime.getRuntime();
			StringBuffer convertCommand = new StringBuffer(120);
			convertCommand.append("cmd /c");
			convertCommand.append(SPACE);
			convertCommand.append("convert");
			convertCommand.append(SPACE);
			convertCommand.append("-set");
			convertCommand.append(SPACE);
			convertCommand.append("delay");
			convertCommand.append(SPACE);
			convertCommand.append("50");
			convertCommand.append(SPACE);
			convertCommand.append(image1);
			convertCommand.append(SPACE);
			convertCommand.append(image2);
			convertCommand.append(SPACE);
			convertCommand.append("-loop");
			convertCommand.append(SPACE);
			convertCommand.append("0");
			convertCommand.append(SPACE);
			convertCommand.append(path3);
			String convertString = convertCommand.toString();
			logger.info("Convert command = " + convertString);
			Process proc1 = null;
			String cmdOutput = "";
			InputStreamReader inputStreamReader = null;
			BufferedReader sysErr = null;

			proc1 = runtime1.exec(convertString, null, new File(
					"C:\\Program Files\\ImageMagick-6.9.0-Q16"));

			imageDetails.setDifference(path);
			try {
				inputStreamReader = new InputStreamReader(
						proc1.getErrorStream());
				sysErr = new BufferedReader(inputStreamReader);
				cmdOutput = sysErr.readLine();
				logger.info("cmdOutput = " + cmdOutput);
			} catch (IOException ioe) {
				logger.info("Exception while reading the buffer : "
						+ ioe.getMessage());
			} finally {
				if (null != sysErr) {
					sysErr.close();
				}
				if (null != inputStreamReader) {
					inputStreamReader.close();
				}
			}

		} catch (Exception e) {
			logger.info("Exception while executing the Convert command : "
					+ e.getMessage());
			throw new Exception("Unable to Convert Images  to GIF", e);
		}

		String temp_Path = "C:".concat(temp_actual_path);

		if (height1 != height2) {
			convert(path2, temp_Path, width1, height1);
			path2 = temp_Path;
		}

		try {
			Runtime runtime = Runtime.getRuntime();
			StringBuffer compareCommand = new StringBuffer(120);
			compareCommand.append("cmd /c");
			compareCommand.append(SPACE);
			compareCommand.append("compare");
			compareCommand.append(SPACE);
			compareCommand.append("-dissimilarity-threshold");
			compareCommand.append(SPACE);
			compareCommand.append("100%");
			compareCommand.append(SPACE);
			compareCommand.append("-metric");
			compareCommand.append(SPACE);
			compareCommand.append(imMetricLocal);
			compareCommand.append(SPACE);
			compareCommand.append("-fuzz");
			compareCommand.append(SPACE);
			compareCommand.append(imFuzzLocal);
			compareCommand.append('%');
			compareCommand.append(SPACE);
			compareCommand.append(path1);
			compareCommand.append(SPACE);
			compareCommand.append(path2);
			compareCommand.append(SPACE);
			compareCommand.append("null");
			String compareString = compareCommand.toString();
			logger.info("Compare command = " + compareString);
			Process proc = null;
			String cmdOutput = "";
			InputStreamReader inputStreamReader = null;
			BufferedReader sysErr = null;

			proc = runtime.exec(compareString, null, new File(
					"C:\\Program Files\\ImageMagick-6.9.0-Q16"));

			try {
				inputStreamReader = new InputStreamReader(proc.getErrorStream());
				sysErr = new BufferedReader(inputStreamReader);
				cmdOutput = sysErr.readLine();
				logger.info("cmdOutput = " + cmdOutput);
			} catch (IOException ioe) {
				logger.info("Exception while reading the buffer : "
						+ ioe.getMessage());
			} finally {
				if (null != sysErr) {
					sysErr.close();
				}
				if (null != inputStreamReader) {
					inputStreamReader.close();
				}
			}

			logger.info("cmdOutput = " + cmdOutput);
			int openingBraces = cmdOutput.indexOf('(');
			int closingBraces = cmdOutput.indexOf(')');
			if (openingBraces != -1 && closingBraces != -1) {
				String strDisimilarity = cmdOutput.substring(openingBraces + 1,
						closingBraces);
				logger.info("strDisimilarity = " + strDisimilarity);
				double disimilarity = 0;
				try {
					disimilarity = Double.parseDouble(strDisimilarity);
				} catch (NumberFormatException nfe) {
					logger.info("Exception while conversion from string to double : "
							+ nfe.getMessage());
				}
				logger.info("disimilarity = " + disimilarity);
				similarity = (1 - disimilarity) * VALUE;
				DecimalFormat df = new DecimalFormat("###.##");
				similarity = Double.parseDouble(df.format(similarity));
				logger.info("Similarity = " + similarity);
				imageDetails.setDiffPercent(similarity);
				if ((int) similarity >= imageDetails.getThreshold()) {
					imageDetails.setStatus("PASS");
				} else {
					imageDetails.setStatus("FAIL");
				}
			}
		} catch (Exception e) {
			logger.info("Exception while executing the compare command : "
					+ e.getMessage());
			throw new Exception("Unable to Compare Images ", e);
		}

		return imageDetails;

	}

	/**
	 * get job details by job name
	 * 
	 * @param jobname
	 * @return
	 */
	@Transactional
	private Job getJobDetailsByJobName(String jobname) {
		try {
			return jobdao.getJobDetailsByName(jobname);
		} catch (ImageValidationException e) {
			logger.error(e.getmessage());
			return new Job();
		}
	}
	
	/**
	 * Check Imagemagick Availability
	 */
	private static void isImageMagickAvailable() {
		try {
			ConvertCmd cmd = new ConvertCmd();
			IMOperation versionOp = new IMOperation();
			versionOp.version();
			cmd.run(versionOp);
			logger.info("ImageMagick version " + versionOp
					+ " detected and found to be working");
		} catch (Exception e) {
			logger.info("Setting Imagemagick");
			setImagemagickPath();
		}
	}

	/**
	 * Set Imagemagick Path for Execution
	 */
	private static void setImagemagickPath() {
		String ImagemagickPath = null;
		Properties property = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(System.getProperty("user.dir"
					+ "\\src\\main\\resources\\configuration.properties"));
			property.load(input);
			System.out
					.println("*************************Imagemagick Properties***************************************");
			System.out.println(property.getProperty("imagemagick.path"));
			ImagemagickPath = property.getProperty("imagemagick.path");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// String ImagemagickPath = "C:\\Program Files\\ImageMagick-6.9.0-Q16";
		ProcessStarter.setGlobalSearchPath(ImagemagickPath);
		isImageMagickAvailable();
	}

	/**
	 * convert to png image
	 * 
	 * @param acutul_path
	 * @param expected_path
	 * @param width
	 * @param height
	 */
	private static void convert(String acutul_path, String expected_path,
			int width, int height) {
		try {
			BufferedImage originalImage = ImageIO.read(new File(acutul_path));
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: originalImage.getType();
			BufferedImage resizeImagePng = resizeImage(originalImage, type,
					width, height);
			ImageIO.write(resizeImagePng, "png", new File(expected_path));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * resizes images
	 * 
	 * @param originalImage
	 * @param type
	 * @param width
	 * @param height
	 * @return
	 */
	private static BufferedImage resizeImage(BufferedImage originalImage,
			int type, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}
}
