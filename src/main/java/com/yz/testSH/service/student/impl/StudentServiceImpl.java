package com.yz.testSH.service.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yz.testSH.dao.student.IStudentDao;
import com.yz.testSH.model.TStudent;
import com.yz.testSH.service.student.IStudentService;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private IStudentDao studentDao;
	
	
	@Override
	public void save(TStudent entity) {
		System.out.println("searvice is ready..........");
		studentDao.save(entity);
	}

	
}
