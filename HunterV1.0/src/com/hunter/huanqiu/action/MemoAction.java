package com.hunter.huanqiu.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hunter.huanqiu.entity.Company;
import com.hunter.huanqiu.entity.Memo;
import com.hunter.huanqiu.entity.Record;
import com.hunter.huanqiu.entity.User;
import com.hunter.huanqiu.form.MemoForm;
import com.hunter.huanqiu.manager.MemoManager;

public class MemoAction extends DispatchAction {

	public ActionForward getMemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String timeNow=df1.format(date);
		int total=0;
		List<Object> rows;
		MemoManager mm=(MemoManager) act.getBean("memoManager");
		User user=(User)request.getSession().getAttribute("user");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));// 接受参数page和rows
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String type=request.getParameter("type");
		rows = new ArrayList<Object>();
		List<Memo> list=null;
		if(type.equals("today")){
			if(user.getUsertype().equals("user")){
				list=mm.getTodayMemo(user.getUsername(),page,row,order,sort,timeNow);
				total=mm.getTodayMemoCount(user.getUsername(),timeNow);
			}
			else {
				list=mm.getTodayMemo(null,page,row,order,sort,timeNow);
				total=mm.getTodayMemoCount(null,timeNow);
			}
		}
		else{
			if(user.getUsertype().equals("user")){
				list=mm.getOthersMemo(user.getUsername(), page, row, order, sort, timeNow);
				total=mm.getOthersMemoCount(user.getUsername(), timeNow);
			}
			else{
				list=mm.getOthersMemo(null, page, row, order, sort, timeNow);
				total=mm.getOthersMemoCount(null, timeNow);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			Memo memo = list.get(i); 
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("meId", memo.getMeId()); 
			map.put("username", memo.getUser().getUsername()); // 以键值对的形式保存到map中
			map.put("memoTheme", memo.getMemoTheme());
			map.put("memoTime",df.format(memo.getMemoTime()));
			map.put("memoAbstract", memo.getMemoAbstract());
			map.put("memoZt", memo.getMemoZt());
			rows.add(map); // 循环保存map到list对象this.rows中
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonObj.toString());
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	public ActionForward addMemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		MemoManager mm=(MemoManager) act.getBean("memoManager");
		User user=(User)request.getSession().getAttribute("user");
		MemoForm memoForm=(MemoForm)form;
		Memo memo=new Memo();
		memo.setMeId(UUID.randomUUID().toString());
		memo.setMemoTheme(memoForm.getMemoTheme());
		memo.setMemoAbstract(memoForm.getMemoAbstract());
		memo.setMemoTime(memoForm.getMemoTime());
		memo.setUser(user);
		memo.setMemoZt(1);
		mm.addMemo(memo);
		return null;
	}
	public ActionForward editMemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		MemoManager mm=(MemoManager) act.getBean("memoManager");
		User user=(User)request.getSession().getAttribute("user");
		MemoForm memoForm=(MemoForm)form;
		Memo memo=mm.getMemoById(memoForm.getMeId());
		memo.setMemoTheme(memoForm.getMemoTheme());
		memo.setMemoAbstract(memoForm.getMemoAbstract());
		memo.setMemoTime(memoForm.getMemoTime());
		memo.setUser(user);
		memo.setMemoZt(1);
		mm.editMemo(memo);
		return null;
	}
	public ActionForward deleteMemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		MemoManager mm=(MemoManager) act.getBean("memoManager");
		String meId=request.getParameter("meId");
		String[] meIds=meId.split(",");
		for(int i=0;i<meIds.length;i++){
			Memo memo=mm.getMemoById(meIds[i]);
			mm.deleteMemo(memo);
		}
		return null;
	}
}
