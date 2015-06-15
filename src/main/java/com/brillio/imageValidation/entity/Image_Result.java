/**
 * ----------------------------
 * Image_Result.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * Contains Image Details
 *
 */
@Entity(name = "IMAGE_RESULT_TB")
public class Image_Result {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "IMG_RES_ID")
	private int img_res_Id;

	private String url_name;

	private int actual_Image_id;

	private String actualImagePath;

	private String expectedImagePath;

	private int expected_Image_id;

	private String differnce_ImagePath;

	private String status;

	private double percentage;

	/**
	 * getter
	 * 
	 * @return img_res_Id
	 */
	public int getImg_res_Id() {
		return img_res_Id;
	}

	/**
	 * setter
	 * 
	 * @param img_res_Id
	 */
	public void setImg_res_Id(int img_res_Id) {
		this.img_res_Id = img_res_Id;
	}

	/**
	 * getter
	 * 
	 * @return url_name
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
	 * @return actual_Image_id
	 */
	public int getActual_Image_id() {
		return actual_Image_id;
	}

	/**
	 * setter
	 * 
	 * @param actual_Image_id
	 */
	public void setActual_Image_id(int actual_Image_id) {
		this.actual_Image_id = actual_Image_id;
	}

	/**
	 * getter
	 * 
	 * @return actualImagePath
	 */
	public String getActualImagePath() {
		return actualImagePath;
	}

	/**
	 * setter
	 * 
	 * @param actualImagePath
	 */
	public void setActualImagePath(String actualImagePath) {
		this.actualImagePath = actualImagePath;
	}

	/**
	 * getter
	 * 
	 * @return expectedImagePath
	 */
	public String getExpectedImagePath() {
		return expectedImagePath;
	}

	/**
	 * setter
	 * 
	 * @param expectedImagePath
	 */
	public void setExpectedImagePath(String expectedImagePath) {
		this.expectedImagePath = expectedImagePath;
	}

	/**
	 * getter
	 * 
	 * @return expected_Image_id
	 */
	public int getExpected_Image_id() {
		return expected_Image_id;
	}

	/**
	 * setter
	 * 
	 * @param expected_Image_id
	 */
	public void setExpected_Image_id(int expected_Image_id) {
		this.expected_Image_id = expected_Image_id;
	}

	/**
	 * getter
	 * 
	 * @return differnce_ImagePath
	 */
	public String getDiffernce_ImagePath() {
		return differnce_ImagePath;
	}

	/**
	 * setter
	 * 
	 * @param differnce_ImagePath
	 */
	public void setDiffernce_ImagePath(String differnce_ImagePath) {
		this.differnce_ImagePath = differnce_ImagePath;
	}

	/**
	 * getter
	 * 
	 * @return status
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
	 * @return percentage
	 */
	public double getPercentage() {
		return percentage;
	}

	/**
	 * setter
	 * 
	 * @param percentage
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	

}
