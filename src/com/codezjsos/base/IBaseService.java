package com.codezjsos.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.codezjsos.base.bean.PageBean;

public interface IBaseService {
	public <T> void save(T t) throws Exception;
	public <T> void update(T t) throws Exception;
	public <T> void delete(T t) throws Exception;
	public <T> T findOne(Class<T> clz, Serializable unid)throws Exception;
	public <T> T load(Class<T> clz, Serializable unid)throws Exception;
	public <T> T findOne(String hql, Object... obj)throws Exception;
	public <T> T findOne(String hql, Map<String, Object> obj)throws Exception;
	public <T> List<T> findAll(String hql, Object... obj)throws Exception;
	public <T> List<T> findAll(String hql, Map<String, Object> obj)throws Exception;
	public <T> List<T> findPage(String hql, PageBean bean, Object... obj)throws Exception;
	public <T> List<T> findPage(String hql, PageBean bean, Map<String, Object> obj)throws Exception;
	

	public <T> List<T> listByHQL(String hql, PageBean bean, Object... obj)throws Exception;
	public <T> List<T> listByHQL(String hql, PageBean bean, Map<String, Object> obj)throws Exception;
	public <T> List<T> listBySQL(final Class<T> clz, String sql, PageBean bean, Object... obj)throws Exception;
	public <T> List<T> listBySQL(final Class<T> clz, String sql, PageBean bean, Map<String, Object> obj)throws Exception;

	public <T> T findSql(final Class<T> clz, String sql, Object... obj)throws Exception;
	public <T> T findSql(final Class<T> clz, String sql, Map<String, Object> obj)throws Exception;
	public <T> List<T> findSqlAll(final Class<T> clz, String sql, Object... obj)throws Exception;
	public <T> List<T> findSqlAll(final Class<T> clz, String sql, Map<String, Object> obj)throws Exception;
	public <T> List<T> findSqlPage(final Class<T> clz, String sql, PageBean bean, Object... obj)throws Exception;
	public <T> List<T> findSqlPage(final Class<T> clz, String sql, PageBean bean, Map<String, Object> obj)throws Exception;
	public Long count(String hql, Object... obj)throws Exception ;
	public Long count(String hql, Map<String, Object> obj)throws Exception ;
	
	public void deleteListSql(String sql, Object... obj) throws Exception;
	public int executeUpdate(String sql) throws Exception;

	public void setBaseDao(IBaseDao baseDao);
	public void setJdbcTemplate(DataSource dataSource);
	
	public JdbcTemplate getJdbcTemplate();
	
	public List<Map<String,Object>> findSqlPageMap( String sql, PageBean bean, Object... obj) throws Exception;
	public List<Map<String,Object>> findSqlAllMap( String sql,Object...  obj) throws Exception;
	public Map<String,Object> findSqlMap( String sql,Object... obj) throws Exception;
	public List<Map<String,Object>> findSqlPageMap( String sql, PageBean bean, Map<String, Object> obj ) throws Exception;
	public List<Map<String,Object>> findSqlAllMap( String sql,Map<String, Object> obj) throws Exception;
	public Map<String,Object> findSqlMap( String sql,Map<String, Object> obj) throws Exception;

}
