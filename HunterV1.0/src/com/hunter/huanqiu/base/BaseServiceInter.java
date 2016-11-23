package com.hunter.huanqiu.base;

import java.io.Serializable;
import java.util.List;

public interface BaseServiceInter {

	public List search(String hql,Object[] parameters);
	public List search(String hql,Object[] parameters,int page,int size);
	public void save(Object obj);
	public void delete(Object obj);
	public void update(Object obj);
	public Object getObjectById(Class clazz,Serializable id);
	public int getCount(String hql);
	
}
