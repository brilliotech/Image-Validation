/**
 * ----------------------------
 * Build.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.entity;

import java.sql.Timestamp;
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

/**
 * 
 * Contains individual buid details
 *
 */
@Entity(name = "BUILD_TB")
public class Build {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUILD_ID")
	private int build_Id;
	@Column(name = "build_Name", unique = true, nullable = false)
	private String build_Name;

	private Timestamp build_TimeStamp;
	private String mode;

	private String build_Status;

	// changes
	private String comparable_build_name;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "BUILD_URL_INFO_Tb", joinColumns = @JoinColumn(name = "BUILD_ID"), inverseJoinColumns = @JoinColumn(name = "url_id"))
	private Collection<URL_INFO> URL_INFO_Tb_Collection;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "BUILD_IMAGE_RESULTS", joinColumns = @JoinColumn(name = "BUILD_ID"), inverseJoinColumns = @JoinColumn(name = "IMG_RES_ID"))
	private Collection<Image_Result> imageResults;

	/**
	 * getter
	 * 
	 * @return imageResults
	 */
	public Collection<Image_Result> getImageResults() {
		return imageResults;
	}

	/**
	 * setter 
	 * 
	 * @param imageResults
	 */
	public void setImageResults(Collection<Image_Result> imageResults) {
		this.imageResults = imageResults;
	}

	/**
	 * getter
	 * 
	 * @return build_Name
	 */
	public String getBuild_Name() {
		return build_Name;
	}

	/**
	 * setter
	 * 
	 * @param build_Name
	 */
	public void setBuild_Name(String build_Name) {
		this.build_Name = build_Name;
	}

	/**
	 * getter
	 * 
	 * @return build_TimeStamp
	 */
	public Timestamp getBuild_TimeStamp() {
		return build_TimeStamp;
	}

	/**
	 * setter
	 * 
	 * @param build_TimeStamp
	 */
	public void setBuild_TimeStamp(Timestamp build_TimeStamp) {
		this.build_TimeStamp = build_TimeStamp;
	}

	/**
	 * getter
	 * 
	 * @return build_Status
	 */
	public String getBuild_Status() {
		return build_Status;
	}

	/**
	 * setter
	 * 
	 * @param build_Status
	 */
	public void setBuild_Status(String build_Status) {
		this.build_Status = build_Status;
	}

	/**
	 * getter
	 * 
	 * @return URL_INFO_Tb_Collection
	 */
	public Collection<URL_INFO> getURL_INFO_Tb_Collection() {
		return URL_INFO_Tb_Collection;
	}

	/**
	 * setter
	 * 
	 * @param uRL_INFO_Tb_Collection
	 */
	public void setURL_INFO_Tb_Collection(
			Collection<URL_INFO> uRL_INFO_Tb_Collection) {
		URL_INFO_Tb_Collection = uRL_INFO_Tb_Collection;
	}

	/**
	 * getter
	 * 
	 * @return mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * setter
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * getter
	 * 
	 * @return comparable_build_name
	 */
	public String getComparable_build_name() {
		return comparable_build_name;
	}

	/**
	 * setter
	 * 
	 * @param comparable_build_name
	 */
	public void setComparable_build_name(String comparable_build_name) {
		this.comparable_build_name = comparable_build_name;
	}

	/**
	 * getter
	 * 
	 * @return build_Id
	 */
	public int getBuild_Id() {
		return build_Id;
	}

}
