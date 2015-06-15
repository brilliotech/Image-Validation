/**
 * ----------------------------
 * AllBuild.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.model;

import java.util.List;

/**
 * 
 * Contain all builds
 *
 */
public class AllBuild {
	
	List<BuildModel> buildDetails;

	/**
	 * getter
	 * 
	 * @return
	 */
	public List<BuildModel> getBuildDetails() {
		return buildDetails;
	}

	/**
	 * setter
	 * 
	 * @param buildDetails
	 */
	public void setBuildDetails(List<BuildModel> buildDetails) {
		this.buildDetails = buildDetails;
	}

}
