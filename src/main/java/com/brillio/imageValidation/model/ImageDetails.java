/**
 * ----------------------------
 * ImageDetails.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

/**
 * 
 * Contains Image Details
 *
 */
public class ImageDetails {
	int actualImageId;
	int expectedImageId;
	String actualImage;
	String expectedImage;
	String difference;
	String status;
	String url_name;
	int threshold;
	String tempDiffImagePath;
	double diffPercent;

	/**
	 * getter
	 * 
	 * @return
	 */
	public int getActualImageId() {
		return actualImageId;
	}

	/**
	 * setter
	 * 
	 * @param actualImageId
	 */
	public void setActualImageId(int actualImageId) {
		this.actualImageId = actualImageId;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public int getExpectedImageId() {
		return expectedImageId;
	}

	/**
	 * setter
	 * 
	 * @param expectedImageId
	 */
	public void setExpectedImageId(int expectedImageId) {
		this.expectedImageId = expectedImageId;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getActualImage() {
		return actualImage;
	}

	/**
	 * setter
	 * 
	 * @param actualImage
	 */
	public void setActualImage(String actualImage) {
		this.actualImage = actualImage;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getExpectedImage() {
		return expectedImage;
	}

	/**
	 * setter
	 * 
	 * @param expectedImage
	 */
	public void setExpectedImage(String expectedImage) {
		this.expectedImage = expectedImage;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getDifference() {
		return difference;
	}

	/**
	 * setter
	 * 
	 * @param difference
	 */
	public void setDifference(String difference) {
		this.difference = difference;
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
	public String getUrl_name() {
		return url_name;
	}

	/**
	 * setter
	 * 
	 * @param url_name
	 */
	public void setUrl_name(String url_name) {
		this.url_name = url_name;
	}

	/**
	 * getter
	 * 
	 * @return
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
	 * @return
	 */
	public String getTempDiffImagePath() {
		return tempDiffImagePath;
	}

	/**
	 * setter
	 * 
	 * @param tempDiffImagePath
	 */
	public void setTempDiffImagePath(String tempDiffImagePath) {
		this.tempDiffImagePath = tempDiffImagePath;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public double getDiffPercent() {
		return diffPercent;
	}

	/**
	 * setter
	 * 
	 * @param diffPercent
	 */
	public void setDiffPercent(double diffPercent) {
		this.diffPercent = diffPercent;
	}

	
}
