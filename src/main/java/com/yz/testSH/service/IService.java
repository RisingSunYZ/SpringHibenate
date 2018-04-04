package com.yz.testSH.service;

import java.util.List;
import java.util.Map;

import com.yz.testSH.util.DataGrid;
import com.yz.testSH.util.PageInfo;

public interface IService<T> {

	void save(T entity);
	
	DataGrid<Map<String,Object>> search(PageInfo info);
}
