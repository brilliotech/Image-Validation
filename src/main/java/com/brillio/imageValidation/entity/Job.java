/**
 * ----------------------------
 * Job.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * 
 * Contains Job Details
 *
 */
@NamedQueries({ @NamedQuery(name = "findJobByName", query = "from JOB_TB r where r.job_Name = :name")})
@Entity(name = "JOB_TB")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "JOB_ID")
	private int job_Id;
	@Column(name = "job_Name", unique = true)
	private String job_Name;

	private int crawl_level;

	private String job_type;

	private String base_url;

	private Timestamp job_Timestamp;

	private String userAgent;

	private int threshold;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "JOB_BUILD_TB", joinColumns = @JoinColumn(name = "JOB_ID"), inverseJoinColumns = @JoinColumn(name = "BUILD_ID"))
	private Collection<Build> job_Build_Collection = new ArrayList<Build>();

	@OneToOne
	@JoinColumn(name = "JOB_MC_ID", unique = true)
	private Machine_Info info;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "JOB_CRAWL_DATA", joinColumns = @JoinColumn(name = "JOB_ID"), inverseJoinColumns = @JoinColumn(name = "URL_ID"))
	private Collection<CrawlData> job_crawl_data = new ArrayList<CrawlData>();

	/**
	 * getter
	 * 
	 * @return crawl_level
	 */
	public int getCrawl_level() {
		return crawl_level;
	}

	/**
	 * getter
	 * 
	 * @return job_Name
	 */
	public String getJob_Name() {
		return job_Name;
	}

	/**
	 * setter
	 * 
	 * @param job_Name
	 */
	public void setJob_Name(String job_Name) {
		this.job_Name = job_Name;
	}

	/**
	 * setter
	 * 
	 * @param crawl_level
	 */
	public void setCrawl_level(int crawl_level) {
		this.crawl_level = crawl_level;
	}

	/**
	 * getter
	 * 
	 * @return job_Build_Collection
	 */
	public Collection<Build> getJob_Build_Collection() {
		return job_Build_Collection;
	}

	/**
	 * setter
	 * 
	 * @param job_Build_Collection
	 */
	public void setJob_Build_Collection(Collection<Build> job_Build_Collection) {
		this.job_Build_Collection = job_Build_Collection;
	}

	/**
	 * getter
	 * 
	 * @return info
	 */
	public Machine_Info getInfo() {
		return info;
	}

	/**
	 * setter
	 * 
	 * @param info
	 */
	public void setInfo(Machine_Info info) {
		this.info = info;
	}

	/**
	 * getter
	 * 
	 * @return job_type
	 */
	public String getJob_type() {
		return job_type;
	}

	/**
	 * setter
	 * 
	 * @param job_type
	 */
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}

	/**
	 * getter
	 * 
	 * @return base_url
	 */
	public String getBase_url() {
		return base_url;
	}

	/**
	 * setter
	 * 
	 * @param base_url
	 */
	public void setBase_url(String base_url) {
		this.base_url = base_url;
	}

	/**
	 * getter
	 * 
	 * @return job_Timestamp
	 */
	public Timestamp getJob_Timestamp() {
		return job_Timestamp;
	}

	/**
	 * setter
	 * 
	 * @param job_Timestamp
	 */
	public void setJob_Timestamp(Timestamp job_Timestamp) {
		this.job_Timestamp = job_Timestamp;
	}

	/**
	 * getter
	 * 
	 * @return job_Id
	 */
	public int getJob_Id() {
		return job_Id;
	}

	/**
	 * getter
	 * 
	 * @return threshold
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * setter
	 * 
	 * @param threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * getter
	 * 
	 * @return job_crawl_data
	 */
	public Collection<CrawlData> getJob_crawl_data() {
		return job_crawl_data;
	}

	/**
	 * setter
	 * 
	 * @param job_crawl_data
	 */
	public void setJob_crawl_data(Collection<CrawlData> job_crawl_data) {
		this.job_crawl_data = job_crawl_data;
	}

	/**
	 * getter
	 * 
	 * @return userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * setter
	 * 
	 * @param userAgent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
