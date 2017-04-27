package com.codezjsos.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.codezjsos.base.IBaseDao;


public class BaseDaoImpl extends HibernateDaoSupport implements IBaseDao {
	
	private static final Logger logger=LoggerFactory.getLogger(BaseDaoImpl.class);
	
	
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }  
	
	public <T> void save(T t) throws Exception {
		getHibernateTemplate().save(t);
	}

	public <T> void update(T t) throws Exception {
		getHibernateTemplate().update(t);
		
	}

	public <T> void delete(T t) throws Exception {
		getHibernateTemplate().delete(t);
	}
	
	
	public <T> T findOne(Class<T> clz, Serializable unid) {
		
		return getHibernateTemplate().get(clz, unid);
	}

	public <T> T load(Class<T> clz, Serializable unid) throws Exception{
		// TODO Auto-generated method stub
		return  getHibernateTemplate().load(clz, unid);
	}
	private void setParamets(Query query,Object[] obj,int index){
		for(Object o:(Object[])obj){
			if(o instanceof Object[]){
				setParamets(query,(Object[]) o, index);
			}else{
				query.setParameter(index,o);
			}
			index++;
		}
	}
	private void setParamets(Object obj,Query query){
		if(obj instanceof Map){
			query.setProperties((Map<String,Object>)obj);
		}else if(obj instanceof Object[]){
			setParamets(query, (Object[]) obj,0);
		}
	}
	
	public <T> T findOne(final String hql, final Object obj) throws Exception{
		
		return getHibernateTemplate().execute(new HibernateCallback<T>() {

			public T doInHibernate(Session arg0) throws HibernateException {
				Query query=arg0.createQuery(hql);
				setParamets(obj, query);
				return (T) query.uniqueResult();
			}
		});
	}

	public <T> List<T> findAll(final String hql,final Object obj) throws Exception{
		// TODO Auto-generated method stub
		return  getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			public List<T> doInHibernate(Session arg0) throws HibernateException {
				Query query=arg0.createQuery(hql);
				setParamets(obj, query);
				return (List<T>) query.list();
			}
		});
	}

	public <T> List<T> findPage(final String hql,final  int first,final int max,final Object obj)throws Exception{
		// TODO Auto-generated method stub
		return  getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			public List<T> doInHibernate(Session arg0) throws HibernateException {
				Query query=arg0.createQuery(hql);
				query.setFirstResult(first);
				query.setMaxResults(max);
				setParamets(obj, query);
				return (List<T>) query.list();
			}
		});
	}

	public <T> T findSql(final Class<T> clz,final String sql,final Object obj) throws Exception{
		
		return getHibernateTemplate().execute(new HibernateCallback<T>() {

			public T doInHibernate(Session arg0) throws HibernateException {
				SQLQuery query=arg0.createSQLQuery(sql);
				setParamets(obj, query);
				return (T) query.addEntity(clz).uniqueResult();
			}
		});
	}

	public <T> List<T> findAllSql(final Class<T> clz,final String sql,final Object obj) throws Exception{
		// TODO Auto-generated method stub
		return  getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			public List<T> doInHibernate(Session arg0) throws HibernateException {
				SQLQuery query=arg0.createSQLQuery(sql);
				setParamets(obj, query);
				return (List<T>) query.addEntity(clz).list();
			}
		});
	}

	public <T> List<T> findSqlPage(final Class<T> clz,final String sql, final int first,final  int max,final Object obj) throws Exception{
		// TODO Auto-generated method stub
		return  getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			public List<T> doInHibernate(Session arg0) throws HibernateException {
				SQLQuery query=arg0.createSQLQuery(sql);
				setParamets(obj, query);
				query.setFirstResult(first);
				query.setMaxResults(max);
				return (List<T>) query.addEntity(clz).list();
			}
		});
	}



	public void setSessionFactory2(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}

	public <T> T findSqlNo(final Class<T> clz,final String sql, final Object... obj)
			throws Exception {
		return  getHibernateTemplate().execute(new HibernateCallback<T>() {

			public T doInHibernate(Session arg0) throws HibernateException {
				SQLQuery query=arg0.createSQLQuery(sql);
				setParamets(obj, query);
				query.setResultTransformer(Transformers.aliasToBean(clz));
				return  (T) query.uniqueResult();
			}
		});
	}
	public  Number findSqlNum(final String sql, final Object... obj)
			throws Exception {
		return  getHibernateTemplate().execute(new HibernateCallback<Number>() {
			
			public Number doInHibernate(Session arg0) throws HibernateException {
				SQLQuery query=arg0.createSQLQuery(sql);
				setParamets(obj, query);
				
				return  (Number) query.uniqueResult();
			}
		});
	}
	public  Number findSqlNum(final String sql, final Map<String,Object> obj)
			throws Exception {
		return  getHibernateTemplate().execute(new HibernateCallback<Number>() {
			
			public Number doInHibernate(Session arg0) throws HibernateException {
				SQLQuery query=arg0.createSQLQuery(sql);
				setParamets(obj, query);
				
				return  (Number) query.uniqueResult();
			}
		});
	}

	@Override
	public int executeUpdate(String sql) throws Exception {
		
		getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
//		List<ZMenu> os = findAllSql(ZMenu.class, "select p.* from z_menu p where EXISTS ( select 1 from z_menu_tmp where id = p.id )", null);
		return 1;
	}

	@Override
	public  List<Map<String,Object>> findSqlPageResultTransformer(final String sql,
			final int first, final int max,final Object obj) throws Exception {
		return  getHibernateTemplate().execute(new HibernateCallback<List<Map<String,Object>>>() {

			public List<Map<String,Object>> doInHibernate(Session arg0) throws HibernateException {
				SQLQuery query=(SQLQuery) arg0.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				setParamets(obj, query);
				query.setFirstResult(first);
				query.setMaxResults(max);
				return (List<Map<String,Object>>) query.list();
			}
		});
	}
	@Override
	public  List<Map<String,Object>> findSqlPageResultTransformer(final String sql,final Object obj) throws Exception {
		return  getHibernateTemplate().execute(new HibernateCallback<List<Map<String,Object>>>() {
			
			public List<Map<String,Object>> doInHibernate(Session arg0) throws HibernateException {
				SQLQuery query=(SQLQuery) arg0.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				setParamets(obj, query);
				return (List<Map<String,Object>>) query.list();
			}
		});
	}

	@Override
	public Map<String,Object> findSqlOnePageResultTransformer(
			final String sql,final Object obj) throws Exception {
		return  getHibernateTemplate().execute(new HibernateCallback<Map<String,Object>>() {
			
			public Map<String,Object> doInHibernate(Session arg0) throws HibernateException {
				SQLQuery query=(SQLQuery) arg0.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				setParamets(obj, query);
				return (Map<String,Object>) query.uniqueResult();
			}
		});
	}
	
	
}
