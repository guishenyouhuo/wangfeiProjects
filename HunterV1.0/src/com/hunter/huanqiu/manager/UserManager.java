package com.hunter.huanqiu.manager;
import java.util.List;

import com.hunter.huanqiu.entity.Company;
import com.hunter.huanqiu.entity.User;
import com.hunter.huanqiu.service.inter.UserServiceInter;
public class UserManager {

	private UserServiceInter userService;

	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}
	public List<User> getUsers(int page,int row,String order,String sort,String key){
		if(key==null){
			key="";
		}
		String hql="from User as n where n.username like'%"+key+"%' and zt!=0";
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		@SuppressWarnings("unchecked")
		List<User> list=userService.search(hql, null,page,row);
		return list;
	}
	public int getUserCount(String key){
		if(key==null){
			key="";
		}
		String hql="select count(*) from User u where u.username like'%"+key+"%' and zt!=0";
		return userService.getCount(hql);
	}
	public User getUserObj(String username){
		String hql="from User where username ='"+username+"' and zt=1";
		return (User)userService.search(hql, null).get(0);
	}
	public boolean checkUserInDb(String username) {
		boolean b=false;
		String hql="from User where username='"+username+"'";
		List<User> list=userService.search(hql, null);
		if(list.size()>0){
			b=true;
		}
		return b;
	}
	public User getUserByUsername(String username){
		String hql="from User where username='"+username+"' and zt!=0";
		List<User> list=userService.search(hql, null);
		User user =null;
		if(list.size()>0){
			user=list.get(0);
		}
		return user;
	}
	public List<User> getUsers(String key,String order,String sort){
		String hql="from User as n where zt!=0";
		if(key!=null){
			hql=hql+" and n.username like'%"+key+"%'";
		}
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		@SuppressWarnings("unchecked")
		List<User> list=userService.search(hql, null);
		return list;
	}
	public void updateUser(User user){
		userService.update(user);
	}
	public void addUser(User user){
		userService.save(user);
	}
	public void deleteUser(User user){
		user.setZt(0);
		userService.update(user);
	}
}
