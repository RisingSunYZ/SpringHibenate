package com.yz.testSH.service;

import java.util.Map;

import com.yz.testSH.util.AjaxMsg;
import com.yz.testSH.util.DataGrid;
import com.yz.testSH.util.PageInfo;

public interface IService<T> {

	AjaxMsg save(T entity);
	
	DataGrid<Map<String,Object>> search(PageInfo info);
	
	AjaxMsg del(String ids);
}
