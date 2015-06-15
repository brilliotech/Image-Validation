/**
 * ----------------------------
 * BaseLineBuilds.java
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
 * Contains all baseline builds
 *
 */
public class BaseLineBuilds {

	List<String> buildNames;

	/**
	 * getter
	 * 
	 * @return
	 */
	public List<String> getBuildNames() {
		return buildNames;
	}

	/**
	 * Add Build Names
	 * 
	 * @param buildName
	 */
	public void addBuildNames(String buildName) {
		
		if(buildNames != null)
			buildNames.add(buildName);
		else{
			buildNames  = new ArrayList<String>();
			buildNames.add(buildName);
		}
		
	}
	
}
