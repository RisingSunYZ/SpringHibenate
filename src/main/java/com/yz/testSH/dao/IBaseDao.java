package com.yz.testSH.dao;

import java.util.List;
import java.util.Map;

import com.yz.testSH.model.TStudent;

public interface IBaseDao<T> {

	void saveBySession(T entity);
	
	void saveByJdbcTemplate(T entity);
	
	List<Map<String, Object>> searchForMap(String sql);
	 
	long count(String string);
	
	void updateBySession(T entity);
	
	void deleteBySession(T entity);
	
	void deleteById(String sql, String id);
	
	T find(int i,Class<T> clazz);

}
