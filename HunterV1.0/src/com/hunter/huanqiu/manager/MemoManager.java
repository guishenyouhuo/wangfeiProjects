package com.hunter.huanqiu.manager;

import java.util.List;

import com.hunter.huanqiu.entity.Memo;
import com.hunter.huanqiu.service.inter.CompanyServiceInter;
import com.hunter.huanqiu.service.inter.EmployeeServiceInter;
import com.hunter.huanqiu.service.inter.MemoServiceInter;

public class MemoManager {
	private EmployeeServiceInter employeeService;
	private MemoServiceInter memoService;
	public void setEmployeeService(EmployeeServiceInter employeeService) {
		this.employeeService = employeeService;
	}
	public void setMemoService(MemoServiceInter memoService) {
		this.memoService = memoService;
	}
	public List<Memo> getTodayMemo(String username,int page,int row,String order,String sort,String timeNow){
		String hql="from Memo as n where  n.memoZt=1 and n.memoTime like'"+timeNow+"%'";
		if(username!=null){
			hql+="and "+"n.user.username='"+username+"'";
		}
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		List<Memo> list=memoService.search(hql, null,page,row);
		return list;
	}
	public int getTodayMemoCount(String username,String timeNow){
		String hql="select count(*) from Memo as n where  n.memoZt=1 and n.memoTime like'"+timeNow+"%'";
		if(username!=null){
			hql+="and "+"n.user.username='"+username+"'";
		}
		return memoService.getCount(hql);
	}
	public List<Memo> getOthersMemo(String username,int page,int row,String order,String sort,String timeNow){
		String hql="from Memo as n where n.memoZt=1 and n.memoTime not like'"+timeNow+"%'";
		if(username!=null){
			hql+="and "+"n.user.username='"+username+"'";
		}
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		List<Memo> list=memoService.search(hql, null,page,row);
		return list;
	}
	public int getOthersMemoCount(String username,String timeNow){
		String hql="select count(*) from Memo as n where  n.memoZt=1 and n.memoTime not like'"+timeNow+"%'";
		if(username!=null){
			hql+="and "+"n.user.username='"+username+"'";
		}
		return memoService.getCount(hql);
	}
	public Memo getMemoById(String id){
		String hql="from Memo as n where n.meId='"+id+"' and n.memoZt=1";
		Memo memo=(Memo) memoService.search(hql, null).get(0);
		return memo;
	}
	public void addMemo(Memo memo){
		memoService.save(memo);
	}
	public void editMemo(Memo memo){
		memoService.update(memo);
	}
	public void deleteMemo(Memo memo){
		memo.setMemoZt(0);
		memoService.update(memo);
	}
}
