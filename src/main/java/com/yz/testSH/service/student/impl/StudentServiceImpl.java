package com.yz.testSH.service.student.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yz.testSH.dao.student.IStudentDao;
import com.yz.testSH.model.TStudent;
import com.yz.testSH.service.student.IStudentService;
import com.yz.testSH.util.DataGrid;
import com.yz.testSH.util.PageInfo;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private IStudentDao studentDao;
	
	
	@Override
	public void save(TStudent entity) {
		System.out.println("searvice is ready..........");
		studentDao.saveBySession(entity);
	}


	@Override
	public  DataGrid<Map<String,Object>> search(PageInfo info) {
		String page = info.getPage();
		String rows = info.getRows();
		if(StringUtils.isBlank(page)){
			page = "0";
		}else{
			page = Integer.parseInt(page)-1+"";
		}
		if(StringUtils.isBlank(rows)){
			rows = "50";
		}
		StringBuffer sb = new StringBuffer(" select * from t_student limit "+page+","+rows);
		StringBuffer countSql = new StringBuffer("select count(*) as counts from t_student ");
		List<Map<String, Object>> map = this.studentDao.searchForMap(sb.toString());
		long total = this.studentDao.count(countSql.toString());
		DataGrid<Map<String,Object>> data = new DataGrid<Map<String,Object>>(total, map);
		return data;
	}

	
}
