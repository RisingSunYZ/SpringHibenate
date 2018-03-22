package com.yz.testSH.dao;

public interface IBaseDao<T> {

	void saveBySession(T entity);
	
	void saveByJdbcTemplate(T entity);
}
