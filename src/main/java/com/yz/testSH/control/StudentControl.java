package com.yz.testSH.control;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yz.testSH.model.TStudent;
import com.yz.testSH.service.student.IStudentService;
import com.yz.testSH.util.AjaxMsg;
import com.yz.testSH.util.DataGrid;
import com.yz.testSH.util.PageInfo;

/**
 * 
 * @description 学生control
 * @author yz
 * @data 2018年4月9日
 */
@Controller
@Scope("prototype")
@RequestMapping("/student")
public class StudentControl {
	
	@Autowired
	private IStudentService studentService;
	
	private static final String basePath = "student/";
	
	/**
	 * 获取学生数据
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public String getStudentList(PageInfo info){
		DataGrid<Map<String,Object>> students = studentService.search(info);
		String res = JSON.toJSONString(students);
		return res;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping("/fdAdd.do")
	public String fdAdd(){
		return basePath+"add";
	}
	
	/**
	 * 添加学生信息
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add.do")
	public String add(TStudent student){
		AjaxMsg msg =  this.studentService.save(student);
		String res = JSON.toJSONString(msg);
		return res;
	}
	
	/**
	 * 删除学生
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("del/{ids}.do")
	public String del(@PathVariable("ids")String ids) throws Exception{
		if(StringUtils.isBlank(ids)){
			throw new Exception("获取学生信息失败");
		}
		AjaxMsg msg =  this.studentService.del(ids);
		String res = JSON.toJSONString(msg);
		return res;
	}
}
