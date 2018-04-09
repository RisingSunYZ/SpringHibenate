package com.yz.testSH.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yz.testSH.dao.IBaseDao;

public class BaseDaoImpl<T> implements IBaseDao<T>{

	@Autowired
	private SessionFactory factory;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 获取session
	 * @return
	 */
	private Session getSession(){
		return this.factory.getCurrentSession();
	}
	
	/**
	 * 保存
	 */
	@Override
	public void saveBySession(T entity) {
		this.getSession().save(entity);
	}

	@Override
	public void saveByJdbcTemplate(T entity) {
	}

	/**
	 * 返回List<Map<String, Object>> 数据
	 */
	@Override
	public List<Map<String, Object>> searchForMap(String sql) {
		return this.jdbcTemplate.queryForList(sql);
	}

	/**
	 * 统计数量(待优化)
	 */
	@Override
	public long count(String sql) {
		Map<String,Object> map = this.jdbcTemplate.queryForMap(sql);
		if(null!=map&&map.size()>0){
			return (long)map.get("counts");
		}
		return 0;
	}

	/**
	 * 更新
	 */
	@Override
	public void updateBySession(T entity) {
		this.getSession().update(entity);
	}

	/**
	 * 删除实体
	 */
	@Override
	public void deleteBySession(T entity) {
		this.getSession().delete(entity);
	}

	/**
	 * HQL删除
	 */
	@Override
	public void deleteById(String sql,String id) {
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, Integer.parseInt(id));
		query.executeUpdate();
		
	}

	/**
	 * 查找对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T find(int id,Class<T> clazz) {
		Object obj= this.getSession().get(clazz, id);
		return (T) obj;
	}
	
	

}
