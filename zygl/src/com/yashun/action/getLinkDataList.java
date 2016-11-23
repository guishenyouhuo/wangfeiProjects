package com.yashun.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.chain.contexts.ServletActionContext;

import com.yashun.manager.AdminManager;

public class getLinkDataList extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String linkDataName=request.getParameter("linkDataName");
		AdminManager am = new AdminManager();
		String uid = (String)request.getSession().getAttribute("usernum");
        List<String> linkDataList = am.getLinkDataList(linkDataName,uid);
        if(linkDataList!=null&&linkDataList.size()>0){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            for(int i=0;i<linkDataList.size()-1;i++){
                stringBuffer.append("{\"linkDataName\":\"").append(linkDataList.get(i)).append("\"},");
               // stringBuffer.append("\"linkDataId\":\"").append(linkDataList.get(i).getLinkDataId()).append("\"},");
            }
            stringBuffer.append("{\"linkDataName\":\"").append(linkDataList.get(linkDataList.size()-1)).append("\"}]");
            //stringBuffer.append("\"linkDataId\":\"").append(linkDataList.get(linkDataList.size()-1).getLinkDataId()).append("\"}]");
            response.getWriter().write(stringBuffer.toString());
        }else{
        	response.getWriter().write("[]");
        }
		return null;
	}

}
