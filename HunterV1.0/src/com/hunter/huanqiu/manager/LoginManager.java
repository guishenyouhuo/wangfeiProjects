package com.hunter.huanqiu.manager;
import java.util.List;

import com.hunter.huanqiu.service.inter.*;
import com.hunter.huanqiu.entity.Company;
import com.hunter.huanqiu.entity.Record;
import com.hunter.huanqiu.entity.User;
import com.hunter.huanqiu.form.UserForm;
public class LoginManager {

	private UserServiceInter userService;
	private CompanyServiceInter companyService;
	private RecordServiceInter recordService;

	public void setRecordService(RecordServiceInter recordService) {
		this.recordService = recordService;
	}
	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}
	public void setCompanyService(CompanyServiceInter companyService) {
		this.companyService = companyService;
	}
	public String  checkInDatabase(UserForm userForm){
		String hql="from User as n where n.username=? and usertype=? and zt=1";
		String[] para={userForm.getUsername(),userForm.getUsertype()+""};
		@SuppressWarnings("unchecked")
		List<User> list=userService.search(hql, para);
		User user=null;
		if(list.size()>0){
			user=list.get(0);
		}
		if(user==null){
			return "找不到该用户！！";
		}
		else
		{
			if(user.getPassword().equals(userForm.getPassword())){
				return "success";
			}
			else{
				return "密码错误！";
			}
		}
	}
	public User getUserObj(String username){
		String hql="from User where username ='"+username+"' and zt=1";
		return (User)userService.search(hql, null).get(0);
	}
	public List<Company> getAllCompanyInClue(){
		String hql="from Company where CZt=1 or CZt=7";//1为线索库中的数据，7为用户从共享库中获得的线索数据
		List<Company> list=companyService.search(hql, null);
		return list;
	}
	
	public List<Company> getAllCompanyInOther(){
		String hql="from Company where CZt=2 or CZt=3 or CZt=4";//1为线索库中的数据，7为用户从共享库中获得的线索数据
		List<Company> list=companyService.search(hql, null);
		return list;
	}
	public void updateCompany(Company company){
		companyService.update(company);
	}
	public Record getLastRecordByCompanyId(String c_id){
		String hql="from Record as r where r.company.CId='"+c_id+"' and r.RZt=1 order by r.RTime asc";
		List<Record> records=recordService.search(hql, null);
		if(records.size()!=0){
			Record lastRecord=records.get(records.size()-1);
			return lastRecord;
		}
		else return null;
		
	}
}
