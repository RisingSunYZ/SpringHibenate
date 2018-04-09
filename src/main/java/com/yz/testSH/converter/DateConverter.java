package com.yz.testSH.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * @description 日期转换
 * @author yz
 * @data 2018年4月9日
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	     dateFormat.setLenient(false);    
	     try {    
	        return dateFormat.parse(source);    
	     } catch (Exception e) {    
	        e.printStackTrace();    
	     }           
	     return null;    
	}

}
