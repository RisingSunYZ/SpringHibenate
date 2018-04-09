package com.yz.testSH.service.student.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
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

/**
 * 
 * @description 学生service层
 * @author yz
 * @data 2018年4月9日
 */
@Service
@Transactional
public class StudentServiceImpl implements IStudentService{

	/**
	 * 获取redis连接(待优化)
	 * @return
	 */
	private Jedis getConn (){
		Jedis conn = new Jedis("localhost");
		return conn;
	}
	
	
	@Autowired
	private IStudentDao studentDao;
	
	
	/**
	 * 保存
	 */
	@Override
	public AjaxMsg save(TStudent entity) {
		
		studentDao.saveBySession(entity);//存入数据库
		
		Jedis conn = getConn();
    	byte[] stuStr = serializable(entity);//将学生信息序列化
    	
    	conn.lpush("students".getBytes(), stuStr);//存入缓存
    	
		return new AjaxMsg(true,"添加成功");
	}


	/**
	 * 查询学生信息
	 */
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
		
		int begin = Integer.parseInt(page)*Integer.parseInt(rows);
		int end = (Integer.parseInt(page)+1)*Integer.parseInt(rows)-1 ;
		if(conn.exists("students".getBytes())){//如果缓存中存在 则从缓存读数据
			long total = conn.llen("students".getBytes());
			List<byte[]> l = conn.lrange("students".getBytes(),begin ,end);
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
		}else{//否则从数据库读取数据
			StringBuffer sb = new StringBuffer(" select * from t_student limit "+begin+","+end);
			StringBuffer countSql = new StringBuffer("select count(*) as counts from t_student ");
			List<Map<String, Object>> map = this.studentDao.searchForMap(sb.toString());
			long total = this.studentDao.count(countSql.toString());
			DataGrid<Map<String,Object>> data = new DataGrid<Map<String,Object>>(total, map);	
			System.out.println("走的数据库");
			return data;
		}
		
	}


	/**
	 * 反序列化
	 * @param student
	 * @return
	 */
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
	
	/**
	 * 序列化
	 * @param stu
	 * @return
	 */
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


	/**
	 * 删除
	 */
	@Override
	public AjaxMsg del(String ids) {
		try {
			String [] idArr = ids.split(",");
			for(String id:idArr){
				if(StringUtils.isNotBlank(id)){
					TStudent stu = this.studentDao.find(Integer.parseInt(id), TStudent.class);
					Jedis conn = getConn();
					//===============处理日期begin
					/**
					 * 因为save的时候日期格式(Wed Apr 25 00:00:00 CST 2018)与search(2018-04-25 00:00:00.0)的时候不一致
					 * 导致序列化对象结果不相同 所以这里处理一下
					 */
					Date date  = stu.getBirth();
					long dateLong = date.getTime();
					date = new Date(dateLong);
					stu.setBirth(date);
					//===============处理日期end
					byte [] temp = serializable(stu);
					long res = conn.lrem("students".getBytes(), 1, temp);
					if(res == 0){
						return new AjaxMsg(false, "缓存数据删除失败");
					}
				}
				
				this.studentDao.deleteById(" delete TStudent where id = ? ",id);	
			}
			
			return new AjaxMsg(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
