/**
 * ----------------------------
 * Build_Compare.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * Contains status of comparison
 *
 */
@Entity(name = "BUILD_COMPARE")
public class Build_Compare {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "BUILD_COMPARE_ID")
	private int build_comapre_Id;
	private Timestamp time;
	private int Baseline_build;
	private int Current_build;
	private String Status;

	/**
	 * getter
	 * 
	 * @return time
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * setter
	 * 
	 * @param time
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

	/**
	 * getter
	 * 
	 * @return Baseline_build
	 */
	public int getBaseline_build() {
		return Baseline_build;
	}

	/**
	 * setter
	 * 
	 * @param baseline_build
	 */
	public void setBaseline_build(int baseline_build) {
		Baseline_build = baseline_build;
	}

	/**
	 * getter
	 * 
	 * @return Current_build
	 */
	public int getCurrent_build() {
		return Current_build;
	}

	/**
	 * setter
	 * 
	 * @param current_build
	 */
	public void setCurrent_build(int current_build) {
		Current_build = current_build;
	}

	/**
	 * getter
	 * 
	 * @return Status
	 */
	public String getStatus() {
		return Status;
	}

	/**
	 * setter
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		Status = status;
	}

}
