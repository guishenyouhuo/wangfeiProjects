package com.hunter.huanqiu.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hunter.huanqiu.entity.Bill;
import com.hunter.huanqiu.entity.Company;
import com.hunter.huanqiu.entity.Contract;
import com.hunter.huanqiu.entity.Record;
import com.hunter.huanqiu.entity.User;
import com.hunter.huanqiu.form.BillForm;
import com.hunter.huanqiu.form.ContractForm;
import com.hunter.huanqiu.manager.CompanyMananger;
import com.hunter.huanqiu.utils.MyTools;

public class BillUploadAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		BillForm billForm=(BillForm)form;
		String fileName="";
		long fileSize=0;
		FormFile formFile = billForm.getBillfile();;
		if(formFile!=null){
			fileName=formFile.getFileName();//得到文件名
			fileSize=formFile.getFileSize();//得到文件大小
		}
		//禁止上传大于5M的文件
		if(fileSize>5*1024*1024){
			System.out.println("文件过大.");
			return mapping.findForward("deployerr");
		}
		InputStream is=null;
		OutputStream os=null;
		String newFileName="";
		String realPath="";
		try {
			if(!fileName.equals("")){
				//开始上传文件
				is=formFile.getInputStream();
				//得到绝对路径
//				realPath=this.getServlet().getServletContext().getRealPath("/contracts");
				realPath="D://bills";
				//新的文件名
					newFileName=MyTools.getNewFileName(fileName);
				os=new FileOutputStream(realPath+"\\"+newFileName);
				
				int len=0;
				byte[] bytes=new byte[1024];
				while((len=is.read(bytes))>0){
					//读一点，写一点
					os.write(bytes, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			try {
				if(os!=null)
					os.close();
				if(is!=null)
					is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String conid=billForm.getBelongcontract();
		float shouldPay=billForm.getShouldpay();
		float payNow=billForm.getPaynow();
		String billAbstract=billForm.getBillabstract();
		Contract contract=cm.getContractById(conid);
		Company company=contract.getCompany();
		String abstractInfo="";
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		String timeNow=df.format(ts);
		Record record=new Record();
		record.setRId(UUID.randomUUID().toString());
		record.setRTime(ts);
		record.setRZt(1);
		record.setRTheme("付款");
		record.setRUsername(user.getUsername());
		if(payNow<shouldPay){
			abstractInfo=company.getCName()+"于"+timeNow+"由"+user.getUsername()+"上传付款清单，付款"+payNow+"元，但款项未付清";
		}
		else{
			abstractInfo=company.getCName()+"于"+timeNow+"由"+user.getUsername()+"上传付款清单，付款"+payNow+"元，款项全部付清";
			company.setCZt(5);
			cm.updateCompany(company);
		}
		record.setRAbstract(abstractInfo);
		record.setCompany(company);
		cm.addRecord(record);
		Bill bill=new Bill();
		bill.setBillId(UUID.randomUUID().toString());
		bill.setBillMoney(payNow);
		bill.setBillName(fileName);
		bill.setBillTime(ts);
		bill.setBillZt(1);
		bill.setContract(contract);
		bill.setUser(user);
		bill.setBillNewname(newFileName);
		bill.setBillAbstract(billAbstract);
		cm.addBill(bill);
		return null;
	}

}
