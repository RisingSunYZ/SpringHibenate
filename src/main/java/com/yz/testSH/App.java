package com.yz.testSH;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;

import com.yz.testSH.model.TStudent;
import com.yz.testSH.service.student.IStudentService;

/**
 * Hello world!
 *
 */
public class App 
{

	public static void main( String[] args )
    {
    	
    	ApplicationContext con = new ClassPathXmlApplicationContext("Application.xml");
    	DataSource source = (DataSource) con.getBean("dataSource");
    	IStudentService studentService = (IStudentService) con.getBean("studentServiceImpl");
//    	IStudentService studentService = new StudentServiceImpl();
    	TStudent stu = new TStudent();
    	Date date = new Date();
    	stu.setBirth(date);
    	stu.setName("yz");
    	studentService.save(stu);
    	
    	Jedis conn = new Jedis("localhost");
    	System.out.println(stu.getId());
//    	Map<String,String> map = new HashMap<String,String>();
//    	map.put("name", "yz");
//    	map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
//    	conn.hmset("student"+stu.getId(), map);
    	
    	byte[] stuStr = serializable(stu);
    	conn.lpush("students".getBytes(), stuStr);
//    	
////    	IStudentDao dao = new StudentDaoImpl();
////    	dao.save(stu);
//    	
    	try {
//			System.out.println(source.getConnection());
//			System.out.println( "Hello World!123" );
//    		ApplicationContext con = new ClassPathXmlApplicationContext("Application.xml");
//    		IStudentDao dao = (IStudentDao) con.getBean("studentDaoImpl");
//    		dao.save(stu);
    		
//    		IStudentService service = (IStudentService) con.getBean("studentServiceImpl");
//    		service.save(stu);
    		
			
    	} catch (Exception e) {
			e.printStackTrace();
		}
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
