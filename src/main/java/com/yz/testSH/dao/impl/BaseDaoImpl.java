package com.yz.testSH.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yz.testSH.dao.IBaseDao;
import com.yz.testSH.model.TStudent;

public class BaseDaoImpl<T> implements IBaseDao<T>{

	@Autowired
	private SessionFactory factory;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Session getSession(){
		return this.factory.getCurrentSession();
	}
	
	@Override
	public void saveBySession(T entity) {
		this.getSession().save(entity);
	}

	@Override
	public void saveByJdbcTemplate(T entity) {
	}

	@Override
	public List<Map<String, Object>> searchForMap(String sql) {
		return this.jdbcTemplate.queryForList(sql);
	}

	@Override
	public long count(String sql) {
		Map<String,Object> map = this.jdbcTemplate.queryForMap(sql);
		if(null!=map&&map.size()>0){
			return (long)map.get("counts");
		}
		return 0;
	}

	@Override
	public void updateBySession(T entity) {
		this.getSession().update(entity);
	}

	@Override
	public void deleteBySession(T entity) {
		this.getSession().delete(entity);
	}

	@Override
	public void deleteById(String sql,String id) {
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, Integer.parseInt(id));
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(int id,Class<T> clazz) {
		Object obj= this.getSession().get(clazz, id);
		return (T) obj;
	}
	
	

}
