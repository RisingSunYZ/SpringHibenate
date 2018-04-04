package com.yz.testSH.control;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yz.testSH.model.TStudent;
import com.yz.testSH.service.student.IStudentService;
import com.yz.testSH.util.DataGrid;
import com.yz.testSH.util.PageInfo;

@Controller
@Scope("prototype")
@RequestMapping("/student")
public class StudentControl {
	
	@Autowired
	private IStudentService studentService;
	
	private static final String basePath = "student/";
	
	@ResponseBody
	@RequestMapping("/list.do")
	public String getStudentList(PageInfo info){
		DataGrid<Map<String,Object>> students = studentService.search(info);
		String res = JSON.toJSONString(students);
		return res;
	}
	
	
	@RequestMapping("/fdAdd.do")
	public String fdAdd(){
		return basePath+"add";
	}
	
	@RequestMapping("add.do")
	public String add(TStudent student){
//		this.studentService.save();
		return "";
	}
}
