package com.yz.testSH.dao.student.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yz.testSH.dao.student.IStudentDao;
import com.yz.testSH.model.TStudent;


@Repository
public class StudentDaoImpl implements IStudentDao{

	@Autowired
	private SessionFactory factory;
	
	private Session getSession(){
		return this.factory.getCurrentSession();
	}
	
	@Override
	public void save(TStudent entity) {
		System.err.println("dap is ready.....");
		this.getSession().save(entity);
	}

}
