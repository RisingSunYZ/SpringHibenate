package com.yz.testSH.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yz.testSH.dao.IBaseDao;

public class BaseDaoImpl<T> implements IBaseDao<T>{

	@Autowired
	private SessionFactory factory;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Session getSession(){
		return this.factory.getCurrentSession();
	}
	
	@Override
	public void saveBySession(T entity) {
		this.getSession().save(entity);
	}

	@Override
	public void saveByJdbcTemplate(T entity) {
	}

	

}
