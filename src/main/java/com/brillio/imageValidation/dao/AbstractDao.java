/**
 * ----------------------------
 * AbstractDao.java
 * ----------------------------
 * (C) Copyright 2015 by Brillio Technologies Pvt. Ltd.
 * 
 * @author Automation COE
 * 
 */
package com.brillio.imageValidation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract class
 *
 */
public abstract class AbstractDao {

	/**
	 * SessionFactory object
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Gets session
	 * 
	 * @return session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Saves entity
	 * 
	 * @param entity
	 */
	public void persist(Object entity) {
		getSession().persist(entity);
	}
	
	/**
	 * Deletes entity
	 * 
	 * @param entity
	 */
	public void delete(Object entity) {
		getSession().delete(entity);
	}

	/**
	 * Updates entity
	 * 
	 * @param entity
	 */
	public void update(Object entity) {
		getSession().update(entity);
	}

}
