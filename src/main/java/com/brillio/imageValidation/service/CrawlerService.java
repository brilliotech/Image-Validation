/**
 * ----------------------------
 *  CrawlerService.java
 *   ---------------------------- 
 *  (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.brillio.imageValidation.model.CrawlURL;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 
 * Crawls and gets all urls for a base url
 *
 */
@SuppressWarnings(value = { "unused" })
@Component
@Scope("request")
public class CrawlerService extends WebCrawler {

	int count = 0;

	String main_url = null;

	CrawlURL url_main;

	/**
	 * Constructor
	 */
	public CrawlerService() {
		url_main = new CrawlURL();
	}

	/**
	   * This function is called just before starting the crawl by this crawler
	   * instance. It can be used for setting up the data structures or
	   * initializations needed by this crawler instance.
	   */
	@Override
	public void onStart() {
		count = 0;
	}

	/**
	 * Patterns for not Visit Policy
	 */
	Pattern BINARY_FILES_EXTENSIONS = Pattern
			.compile(".*\\.(bmp|gif|jpe?g|png|tiff?|pdf|ico|xaml|pict|rif|pptx?|ps"
					+ "|mid|mp2|mp3|mp4|wav|wma|au|aiff|flac|ogg|3gp|aac|amr|au|vox"
					+ "|avi|mov|mpe?g|ra?m|m4v|smil|wm?v|swf|aaf|asf|flv|mkv"
					+ "|zip|rar|gz|7z|aac|ace|alz|apk|arc|arj|dmg|jar|lzip|lha|css)"
					+ "(\\?.*)?$");

	String startString;

	/**
	  * Classes that extends WebCrawler can overwrite this function to tell the
	  * crawler whether the given url should be crawled or not. The following
	  * implementation indicates that all urls should be included in the crawl.
	  *
	  * @param url
	  *            the url which we are interested to know whether it should be
	  *            included in the crawl or not.
	  * @param page
	  *           Page context from which this URL was scraped
	  * @return if the url should be included in the crawl it returns true,
	  *         otherwise false is returned.
	  */
	@Override
	public boolean shouldVisit(Page page, WebURL url) {
		String href = url.getURL().toLowerCase();

		if (count == 0) {
			main_url = page.getWebURL().getURL();
			count++;
		}
		return !BINARY_FILES_EXTENSIONS.matcher(href).matches()
				&& href.startsWith(main_url);
		// && !href.startsWith("http://google.com")
	}

	List<String> lis = new ArrayList<String>();

	 /**
	   * Classes that extends WebCrawler can overwrite this function to process
	   * the content of the fetched and parsed page.
	   *
	   * @param page
	   *            the page object that is just fetched and parsed.
	   */
	@Override
	public void visit(Page page) {
		int docid = page.getWebURL().getDocid();
		String url = page.getWebURL().getURL();
		String domain = page.getWebURL().getDomain();
		String path = page.getWebURL().getPath();
		String subDomain = page.getWebURL().getSubDomain();
		String parentUrl = page.getWebURL().getParentUrl();
		String anchor = page.getWebURL().getAnchor();

		//logger.debug("Docid: {}", docid);
		logger.info("URL: {}", url);
		//logger.debug("Domain: '{}'", domain);
		//logger.debug("Sub-domain: '{}'", subDomain);
		//logger.debug("Path: '{}'", path);
		//logger.debug("Parent page: {}", parentUrl);
		//logger.debug("Anchor text: {}", anchor);

		lis.add(url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			Set<WebURL> links = htmlParseData.getOutgoingUrls();

			//logger.debug("Text length: {}", text.length());
			//logger.debug("Html length: {}", html.length());
			//logger.debug("Number of outgoing links: {}", links.size());
		}

		Header[] responseHeaders = page.getFetchResponseHeaders();
		if (responseHeaders != null) {
			logger.debug("Response headers:");
			for (Header header : responseHeaders) {
				logger.debug("\t{}: {}", header.getName(), header.getValue());
			}
		}

		//logger.debug("=============");
	}

	/**
	   * The CrawlController instance that has created this crawler instance will
	   * call this function just before terminating this crawler thread. Classes
	   * that extend WebCrawler can override this function to pass their local
	   * data to their controller. The controller then puts these local data in a
	   * List that can then be used for processing the local data of crawlers (if needed).
	   *
	   * @return url_main
	   */
	@Override
	public Object getMyLocalData() {
		url_main.setUrl(lis);
		return url_main;
	}

}