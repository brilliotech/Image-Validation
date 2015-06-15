/**
 * ----------------------------
 * ImageValidationException.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */

package com.brillio.imageValidation.imageValidationException;

/**
 * 
 * Image Validation Custom Exception
 *
 */
public class ImageValidationException extends Exception {

	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 1L;
	String message = null;

	/**
	 * Image validation exception message
	 * 
	 * @param message
	 */
	public ImageValidationException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * super refers immediate parent
	 */
	public ImageValidationException() {
		super();
	}

	/**
	 * gets message
	 * 
	 * @return message
	 */
	public String getmessage() {
		return message;
	}
}
