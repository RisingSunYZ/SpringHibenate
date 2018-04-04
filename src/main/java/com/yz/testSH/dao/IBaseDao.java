package com.yz.testSH.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {

	void saveBySession(T entity);
	
	void saveByJdbcTemplate(T entity);
	
	 List<Map<String, Object>> searchForMap(String sql);
	 
		long count(String string);

}
