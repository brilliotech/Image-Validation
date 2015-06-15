/**
 * ----------------------------
 * ReportBuild.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Contains Build Report Details
 *
 */
public class ReportBuild {
	String buildName;
	String status;
	String time;
	List<ImageDetails> ImageList = new ArrayList<ImageDetails>();

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getBuildName() {
		return buildName;
	}

	/**
	 * setter
	 * 
	 * @param buildName
	 */
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * setter
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * setter
	 * 
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public List<ImageDetails> getImageList() {
		return ImageList;
	}

	/**
	 * setter
	 * @param imageList
	 */
	public void setImageList(List<ImageDetails> imageList) {
		ImageList = imageList;
	}

}
