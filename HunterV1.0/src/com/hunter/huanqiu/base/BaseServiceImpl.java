package com.hunter.huanqiu.base;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class BaseServiceImpl implements BaseServiceInter {
	@Resource
	protected SessionFactory sessionFactory;

	public List search(String hql, Object[] parameters) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(parameters!=null&&parameters.length>0){
			for(int i=0;i<parameters.length;i++){
				query.setParameter(i, parameters[i]);
			}
		}
		return query.list();
	}

	public void delete(Object obj) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(obj);

	}

	public void update(Object obj) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(obj);
	}

	public Object getObjectById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().load(clazz, id);
	}

	public void save(Object obj) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(obj);
	}

	public int getCount(String hql) {
		// TODO Auto-generated method stub
		int count=(Integer)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		return count;
	}

	public List search(String hql, Object[] parameters, int page, int size) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setFirstResult((page-1)*size).setMaxResults(size);
		if(parameters!=null&&parameters.length>0){
			for(int i=0;i<parameters.length;i++){
				query.setParameter(i, parameters[i]);
			}
		}
		return query.list();
	}
}
