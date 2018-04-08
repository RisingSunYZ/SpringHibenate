package com.yz.testSH.service.student.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

import com.yz.testSH.dao.student.IStudentDao;
import com.yz.testSH.model.TStudent;
import com.yz.testSH.service.student.IStudentService;
import com.yz.testSH.util.AjaxMsg;
import com.yz.testSH.util.DataGrid;
import com.yz.testSH.util.PageInfo;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService{

	private Jedis getConn (){
		Jedis conn = new Jedis("localhost");
		return conn;
	}
	
	
	@Autowired
	private IStudentDao studentDao;
	
	
	@Override
	public AjaxMsg save(TStudent entity) {
		studentDao.saveBySession(entity);
		
		Jedis conn = getConn();
    	byte[] stuStr = serializable(entity);
    	conn.lpush("students".getBytes(), stuStr);
    	
		return new AjaxMsg(true,"添加成功");
	}


	@Override
	public  DataGrid<Map<String,Object>> search(PageInfo info) {
		Jedis conn = getConn();
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
		if(conn.exists("students".getBytes())){
			long total = conn.llen("students".getBytes());
			List<byte[]> l = conn.lrange("students".getBytes(), Integer.parseInt(page)*Integer.parseInt(rows),(Integer.parseInt(page)+1)*Integer.parseInt(rows)-1 );
			List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
			for(byte[] student :l){
				Object obj = unserializable(student);
				if(obj instanceof TStudent){
					Map<String,Object> temp = new HashMap<String,Object>();
					temp.put("id", ((TStudent) obj).getId());
					temp.put("name",((TStudent) obj).getName());
					temp.put("birth", ((TStudent) obj).getBirth());
					map.add(temp);
				}
			}
			DataGrid<Map<String,Object>> data = new DataGrid<Map<String,Object>>(total, map);	
			System.out.println("走的缓存");
			return data;
		}else{
			StringBuffer sb = new StringBuffer(" select * from t_student limit "+page+","+rows);
			StringBuffer countSql = new StringBuffer("select count(*) as counts from t_student ");
			List<Map<String, Object>> map = this.studentDao.searchForMap(sb.toString());
			long total = this.studentDao.count(countSql.toString());
			DataGrid<Map<String,Object>> data = new DataGrid<Map<String,Object>>(total, map);	
			System.out.println("走的数据库");
			return data;
		}
		
	}


	private Object unserializable(byte[] student) {
		ObjectInputStream ois = null;
		ByteArrayInputStream bais = null;
		
		try {
			bais = new ByteArrayInputStream(student);
			ois = new ObjectInputStream(bais);
			Object obj = ois.readObject();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static byte[] serializable(TStudent stu) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(stu);
			byte[] temp = baos.toByteArray();
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
}
