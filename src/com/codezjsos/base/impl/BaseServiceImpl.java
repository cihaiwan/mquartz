package com.codezjsos.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.codezjsos.base.IBaseDao;
import com.codezjsos.base.IBaseService;
import com.codezjsos.base.ICommonService;
import com.codezjsos.base.bean.PageBean;


@Transactional
public class BaseServiceImpl implements IBaseService {
	protected JdbcTemplate jdbcTemplate;

	
	
	
	
	private IBaseDao baseDao;

	public <T> void save(final T t) throws Exception {
		baseDao.save(t);
		
	}

	public <T> void update(T t) throws Exception {
		baseDao.update(t);
	}

	public <T> void delete(T t) throws Exception {
		baseDao.delete(t);
	}

	public <T> T findOne(Class<T> clz, Serializable unid) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findOne(clz, unid);
	}

	public <T> T load(Class<T> clz, Serializable unid) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.load(clz, unid);
	}

	public <T> T findOne(String hql, Object... obj) throws Exception {
		
		return baseDao.findOne(hql, obj);
	}

	public <T> T findOne(String hql, Map<String, Object> obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findOne(hql, obj);
	}

	public <T> List<T> findAll(String hql, Object... obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAll(hql, obj);
	}

	public <T> List<T> findAll(String hql, Map<String, Object> obj)
			throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAll(hql, obj);
	}


	
	public <T> List<T> findPage(String hql, PageBean bean, Object... obj)
			throws Exception {
		Long count=count(hql, obj);
		bean.setAllCount(count.intValue());
		List<T> data=baseDao.findPage(hql, bean.start(),bean.getPageSize(),obj);
		bean.setData(data);
		return data;
	}

	public <T> List<T> findPage(String hql, PageBean bean,
			Map<String, Object> obj) throws Exception {
		Long count=count(hql, obj);
		bean.setAllCount(count.intValue());
		List<T> data=baseDao.findPage(hql, bean.start(),bean.getPageSize(),obj);
		bean.setData(data);
		return data;
	}

	public <T> T findSql(Class<T> clz, String sql, Object... obj)
			throws Exception {
		
		return baseDao.findSql(clz, sql, obj);
	}

	public <T> T findSql(Class<T> clz, String sql, Map<String, Object> obj)
			throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findSql(clz, sql, obj);
	}

	public <T> List<T> findSqlAll(Class<T> clz, String sql, Object... obj)
			throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAllSql(clz, sql, obj);
	}

	public <T> List<T> findSqlAll(Class<T> clz, String sql,
			Map<String, Object> obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAllSql(clz, sql, obj);
	}

	public <T> List<T> findSqlPage(Class<T> clz, String sql, PageBean bean,
			Object... obj) throws Exception {
		Number count=baseDao.findSqlNum("select count(*) from "+ StringUtils.substringAfter(sql , "from"), obj);
		bean.setAllCount(count.intValue());
		List<T> data=baseDao.findSqlPage(clz,sql, bean.start(),bean.getPageSize(),obj);
		bean.setData(data);
		return data;
	}

	public <T> List<T> findSqlPage(Class<T> clz, String sql, PageBean bean,
			Map<String, Object> obj) throws Exception {
		Number count=baseDao.findSqlNum("select count(*) from "+ StringUtils.substringAfter(sql , "from"), obj);
		bean.setAllCount(count.intValue());
		List<T> data=baseDao.findSqlPage(clz,sql, bean.start(),bean.getPageSize(),obj);
		bean.setData(data);
		return data;
	}

	public Long count(String hql, Object... obj) throws Exception {
		String hql2="";
		if(hql.indexOf("select")!=-1){
			hql2=" from "+StringUtils.substringAfter(hql, "from") ;
		}else {
			hql2=hql;
		}
		return findOne("select count(*) " +hql2, obj);
//		if(hql.matches("select\\s+.*count\\([^)]+\\).*from .*")){
//			return findOne(hql, obj);
//		}else{
//			return findOne("select count(*) " +hql2, obj);
//		}
	}

	public Long count(String hql, Map<String, Object> obj) throws Exception {
		String hql2="";
		if(hql.indexOf("select")!=-1){
			hql2=" from "+StringUtils.substringAfter(hql, "from");
		}else {
			hql2=hql;
		}
		return findOne("select count(*) " +hql2, obj);
//		if(hql.matches("select\\s+.*count\\([^)]+\\).*from .*")){
//			return findOne(hql, obj);
//		}else{
//			return findOne("select count(*) " +hql, obj);
//		}
	}
	

	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void deleteListSql(String sql,Object... obj) throws Exception {
		//(sql,obj)jdbcTemplate.execute(sql);
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public <T> List<T> listByHQL(String hql, PageBean bean, Object... obj)
			throws Exception {
		if(bean==null||bean.getRows()<0||bean.getPage()<0){
			return findAll(hql, obj);
		}else{
			return findPage(hql, bean, obj);
		}
	}

	@Override
	public <T> List<T> listByHQL(String hql, PageBean bean,
			Map<String, Object> obj) throws Exception {
		if(bean==null||bean.getRows()<0||bean.getPage()<0){
			return findAll(hql, obj);
		}else{
			return findPage(hql, bean, obj);
		}
	}

	@Override
	public <T> List<T> listBySQL(Class<T> clz, String sql, PageBean bean,
			Object... obj) throws Exception {
		if(bean==null||bean.getRows()<0||bean.getPage()<0){
			return findSqlAll(clz, sql, obj);
		}else{
			return findSqlPage(clz, sql, bean, obj);
		}
	}

	@Override
	public <T> List<T> listBySQL(Class<T> clz, String sql, PageBean bean,
			Map<String, Object> obj) throws Exception {
		if(bean==null||bean.getRows()<0||bean.getPage()<0){
			return findSqlAll(clz, sql, obj);
		}else{
			return findSqlPage(clz, sql, bean, obj);
		}
	}
	
	
	public int executeUpdate(String sql) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.executeUpdate(sql);
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	@Override
	public List<Map<String,Object>> findSqlPageMap( String sql, PageBean bean, Object... obj) throws Exception {
		Number count=0;
		String sql2=sql;
		if(sql.toLowerCase().matches(".*group\\s+by.*")){
			sql2="select count(*) from ( "+ sql +" ) b";
		}else{
			sql2="select count(*) from "+ StringUtils.substringAfter(sql,"from");
		}
		count=baseDao.findSqlNum(sql2 , obj);
		bean.setAllCount(count.intValue());
		List<Map<String,Object>> data=(List<Map<String,Object>>) baseDao.findSqlPageResultTransformer(sql, bean.start(),bean.getPageSize(),obj);
		bean.setData(data);
		return data;
	}
	
	public List<Map<String,Object>> findSqlAllMap( String sql,Object...  obj) throws Exception {
		
		List<Map<String,Object>> data=(List<Map<String,Object>>) baseDao.findSqlPageResultTransformer(sql,obj);
		
		return data;
	}
	public Map<String,Object> findSqlMap( String sql,Object...  obj) throws Exception {
		
		Map<String,Object> data=(Map<String,Object>) baseDao.findSqlPageResultTransformer(sql,obj);
		
		return data;
	}

	@Override
	public List<Map<String, Object>> findSqlPageMap(String sql, PageBean bean,
			Map<String, Object> obj) throws Exception {
		Number count=0;
		String sql2=sql;
		if(sql.toLowerCase().matches(".*group\\s+by.*")){
			sql2="select count(*) from ( "+ sql +" ) b";
		}else{
			sql2="select count(*) from "+ StringUtils.substringAfter(sql,"from");
		}
		count=baseDao.findSqlNum(sql2 , obj);
		bean.setAllCount(count.intValue());
		List<Map<String,Object>> data=(List<Map<String,Object>>) baseDao.findSqlPageResultTransformer(sql, bean.start(), bean.getPageSize(), obj);
		bean.setData(data);
		return data;
	}

	@Override
	public List<Map<String, Object>> findSqlAllMap(String sql,
			Map<String, Object> obj) throws Exception {
		List<Map<String,Object>> data=(List<Map<String,Object>>) baseDao.findSqlPageResultTransformer(sql,obj);
		
		return data;
	}

	@Override
	public Map<String, Object> findSqlMap(String sql, Map<String, Object> obj)
			throws Exception {
		Map<String,Object> data=(Map<String,Object>) baseDao.findSqlPageResultTransformer(sql,obj);
		
		return data;
	}
}
