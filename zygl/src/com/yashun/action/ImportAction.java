package com.yashun.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.yashun.bean.MessageBean;
import com.yashun.bean.UserBean;
import com.yashun.form.FileForm;
import com.yashun.manager.AdminManager;
import com.yashun.manager.UserManager;
import com.yashun.service.UserService;
import com.yashun.util.ManProperties;
import com.yashun.util.ManXmls;
import com.yashun.util.ReadExcel;

public class ImportAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FileForm fileForm = (FileForm)form; 
		FormFile file = fileForm.getExcel();
		InputStream is = file.getInputStream();
		List<MessageBean> messages = new ArrayList<MessageBean>();
		AdminManager am = new AdminManager();
		List<UserBean> list2 = am.getAllUsers();
		List<UserBean> users = new ArrayList<UserBean>();
		String numFromUser = fileForm.getSelect();
		String fromtag = request.getParameter("fromtag");
		List<MessageBean> list=null;
//		if("23网".equals(fromtag)||"36网".equals(fromtag)||"89网".equals(fromtag))
//			 list= ReadExcel.readXls23(is);
//		else if("78网".equals(fromtag)||"782网".equals(fromtag))
//			 list= ReadExcel.readXls78(is);
//		else if("959网".equals(fromtag)||"独轮959网".equals(fromtag))
//			 list= ReadExcel.readXls959(is);
//		else if("2958网".equals(fromtag))
//			 list= ReadExcel.readXls2958(is);
//		else if("3158网".equals(fromtag))
//			 list= ReadExcel.readXls3158(is);
//		else if("hao315".equals(fromtag))
//			 list= ReadExcel.readXls315(is);
//		else if("23热线".equals(fromtag)||"78热线".equals(fromtag)||"2958热线".equals(fromtag))
//			 list= ReadExcel.readXlsRe1(is);
//		else if("959热线".equals(fromtag))
//			 list= ReadExcel.readXlsRe959(is);
//		else if("3158热线".equals(fromtag))
//			 list= ReadExcel.readXlsRe3158(is);
//		else if("3158网".equals(fromtag))
//			 list= ReadExcel.readXls3158(is);
		
		String realPath = this.getServlet().getServletContext().getRealPath("/template.xml");
		String template = ManProperties.getTemplate(fromtag);
		String[] params = ManXmls.getParams(realPath, template);
		list = ReadExcel.readXls(is,params);
		for (MessageBean mb : list) {
//			System.out.println(mb.getKh_name()+"  "+mb.getKh_ly()+"  "+mb.getKh_address()+"  "+mb.getKh_tel());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=new Date();
			String timeNow=df.format(date);
			mb.setIntime(timeNow);
			messages.add(mb);
		}
		for (UserBean userBean : list2)
			if(userBean.getTy_flag()==1)
					users.add(userBean);
		int messageCount = messages.size();
		int userCount = users.size();
		String usernum=numFromUser;
		for(int i =0;i<messageCount;i++)
		{
			MessageBean mb = messages.get(i);
			if(numFromUser.equals("autoly"))
				usernum = am.getAutolyNum();
			mb.setFp_user(usernum);
			mb.setTag(fromtag);
			if(!am.checkByTel(mb.getKh_tel()))
			{
				am.addMessage(mb);
				String nowNum = usernum;
				List<UserBean> al = am.getAllOpenUsers();
				for(int j =0 ;j<al.size();j++)
				{
					UserBean ub = al.get(j);
					if(usernum.equals(ub.getGs_num()))
					{
						if(j==al.size()-1)
							nowNum = al.get(0).getGs_num();
						else
							nowNum = al.get(j+1).getGs_num();
						break;
					}
					else
						if(j==al.size()-1)
							nowNum = al.get(0).getGs_num();
				}
				am.updateNowNum(nowNum);
			}
		}
		ActionForward forward = mapping.findForward("ok"); 
		ActionForward newForward=new ActionForward(forward);
		String newPath=forward.getPath()+"&flag=mine";
		newForward.setPath(newPath);
		return newForward;
	}

}
