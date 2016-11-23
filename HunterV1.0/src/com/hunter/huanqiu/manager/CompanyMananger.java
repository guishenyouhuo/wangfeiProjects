package com.hunter.huanqiu.manager;

import java.util.List;
import com.hunter.huanqiu.entity.*;
import com.hunter.huanqiu.service.inter.*;
public class CompanyMananger {
	private CompanyServiceInter companyService;
	private RecordServiceInter recordService;
	private UserServiceInter userService;
	private ContractServiceInter contractService;
	private BillServiceInter billService;

	public void setBillService(BillServiceInter billService) {
		this.billService = billService;
	}

	public void setContractService(ContractServiceInter contractService) {
		this.contractService = contractService;
	}

	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}

	public void setRecordService(RecordServiceInter recordService) {
		this.recordService = recordService;
	}
	public void setCompanyService(CompanyServiceInter companyService) {
		this.companyService = companyService;
	}

	public List<Company> getCompanys(int page,int row,String order,String sort,String key,String address_search,String username,int zt){
		
		String hql="from Company as n where CZt="+zt;
		if(key!=null){
			hql=hql+" and n.CName like'%"+key+"%'";
		}
		if(username!=null){
			hql=hql+" and n.user.username='"+username+"'";
		}
		if(address_search!=null){
			hql=hql+" and n.CAddress like'%"+address_search+"%'";
		}
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		@SuppressWarnings("unchecked")
		List<Company> list=companyService.search(hql, null,page,row);
		return list;
	}
	public int getCompanyCount(String key,String address_search,String username,int zt){
		String hql="select count(*) from Company u where  u.CZt="+zt;
		if(key!=null){
			hql=hql+" and u.CName like'%"+key+"%'";
		}
		if(username!=null){
			hql=hql+" and u.user.username='"+username+"'";
		}
		if(address_search!=null){
			hql=hql+" and u.CAddress like'%"+address_search+"%'";
		}
		return companyService.getCount(hql);
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
	public List<Record> getRecordByCid(int page,int row,String order,String sort,String cid){
		String hql="from Record as r where r.company.CId=? and r.RZt=1";
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		String[] parameter={cid};
		List<Record> list=recordService.search(hql, parameter,page,row);
		return list;
	}
	public int getRecordCountByCid(int page,int row,String order,String sort,String cid){
		String hql="select count(*) from Record as r where  r.company.CId='"+cid+"' and r.RZt=1";
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		int count=recordService.getCount(hql);
		return count;		
	}
	public Company getCompanyById(String cid){
		String hql="from Company where CId='"+cid+"'";
		List<Company> list=companyService.search(hql, null);
		Company company=list.get(0);
		return company;
	}
	public boolean checkCompanyInDb(String companyname){
		boolean b=false;
		String hql="from Company where CName='"+companyname+"' and CZt!=0";
		List<Company> list=companyService.search(hql, null);
		if(list.size()>0){
			b=true;
		}
		return b;
	}
	public void updateCompany(Company company){
		companyService.update(company);
	}
	public void deleteCompany(Company company){
		company.setCZt(0);
		companyService.update(company);
	}
	public void addCompany(Company company){
		companyService.save(company);
	}
	public User getUserObj(String username){
		String hql="from User where username ='"+username+"' and zt=1";
		return (User)userService.search(hql, null).get(0);
	}
	public void addRecord(Record record){
		recordService.save(record);
	}
	public int getCountByZtAndUser(String zt,String username){
		String hql="select count(*) from Company u where  u.CZt='"+zt+"' and u.user.username='"+username+"'";
		int count=companyService.getCount(hql);
		return count;
	}
	public void addContract(Contract contract){
		contractService.save(contract);
	}
	public Contract getContractByCid(String cid){
		String hql="from Contract as c where c.company.CId=? and c.conZt=1 order by conTime desc";
		String[] parameters={cid};
		List<Contract> list = contractService.search(hql, parameters);
		Contract contract=null;
		if(list.size()>0){
			contract=list.get(0);
		}
		return contract;
	}
	public Contract getContractById(String conid){
		String hql="from Contract as c where c.conId=? and c.conZt=1";
		String[] parameters={conid};
		List<Contract> list = contractService.search(hql, parameters);
		Contract contract=null;
		if(list.size()>0){
			contract=list.get(0);
		}
		return contract;
	}
	public void addBill(Bill bill){
		billService.save(bill);
	}
	public Bill getBillByConid( String conid){
		String hql="from Bill as b where b.contract.conId=? and b.billZt=1 order by billTime desc";
		String[] parameters={conid};
		List<Bill> list = contractService.search(hql, parameters);
		Bill bill=null;
		if(list.size()>0){
			bill=list.get(0);
		}
		return bill;
	}
	public List<Bill> getBillsByConid(String conid){
		String hql="from Bill as b where b.contract.conId=? and b.billZt=1 order by billTime desc";
		String[] parameters={conid};
		List<Bill> list = contractService.search(hql, parameters);
		return list;
	}
	public Float getpaidMoneyByConid(String conid){
		float paidMoney=0;
		String hql="from Bill as b where b.contract.conId=? and b.billZt=1";
		String[] parameters={conid};
		List<Bill> list = contractService.search(hql, parameters);
		for(int i=0;i<list.size();i++){
			Bill bill=list.get(i);
			paidMoney+=bill.getBillMoney();
		}
		return paidMoney;
	}
	
	//资源调度
public List<Company> getAllResources(int page,int row,String order,String sort,String key,String tran_ser_address){
		
		String hql="from Company as n where CZt!=0 and CZt!=6";
		if(key!=null){
			hql=hql+" and n.CName like'%"+key+"%'";
		}
		if(tran_ser_address!=null){
			hql=hql+" and n.CAddress like'%"+tran_ser_address+"%'";
		}
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		@SuppressWarnings("unchecked")
		List<Company> list=companyService.search(hql, null,page,row);
		return list;
	}
	public int getResourcesCount(String key,String tran_ser_address){
		String hql="select count(*) from Company u where  u.CZt!=0 and u.CZt!=6";
		if(key!=null){
			hql=hql+" and u.CName like'%"+key+"%'";
		}
		if(tran_ser_address!=null){
			hql=hql+" and u.CAddress like'%"+tran_ser_address+"%'";
		}
		return companyService.getCount(hql);
	}
}
