package com.yz.testSH;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yz.testSH.model.TStudent;
import com.yz.testSH.service.student.IStudentService;
import com.yz.testSH.service.student.impl.StudentServiceImpl;

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
    	stu.setBirth(new Date());
    	stu.setName("yz");
    	studentService.save(stu);
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
}
