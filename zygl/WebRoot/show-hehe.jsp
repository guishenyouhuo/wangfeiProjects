<%@ page language="java" import="java.util.*,com.yashun.db.*,com.yashun.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  
  <body>
    <%
  
  String tel = request.getParameter("tel");
		String sql="select * from khmessage where kh_tel like '%"+tel+"'";
		SqlHelper sqlHelper=new SqlHelper();
		ArrayList al=sqlHelper.excuteQuery(sql, null);
		ArrayList al2=new ArrayList();
		if(al.size()>0){
			for(int i=0;i<al.size();i++){
				Object []objs=(Object[]) al.get(i);
				MessageBean message=new MessageBean();
				message.setId(objs[0].toString());
				if(null!=objs[1])
					message.setKh_name(objs[1].toString());
				else
					message.setKh_name("");
				if(null!=objs[2])
					message.setKh_tel(objs[2].toString());
				else
					message.setKh_tel("空");
				if(null!=objs[3])
					message.setKh_address(objs[3].toString());
				else
					message.setKh_address("");
				if(null!=objs[4])
					message.setKh_ly(objs[4].toString());
				else
					message.setKh_ly("");
				message.setFp_user(objs[5].toString());
				if(null==objs[6])
					message.setFp_hf("");
				else
					message.setFp_hf(objs[6].toString());
				message.setIntime(objs[7].toString());
				if(null==objs[8])
					message.setLasthf("");
				else
					message.setLasthf(objs[8].toString());
				message.setType(objs[9].toString());
				if(null==objs[10])
					message.setTag("");
				else
					message.setTag(objs[10].toString());
				if(null==objs[11])
					message.setLast_user("");
				else
					message.setLast_user(objs[11].toString());
				al2.add(message);
			}
		}
		request.setAttribute("messages", al2);
		//request.getRequestDispatcher("show.jsp").forward(request, response);
  
   %>
		<div align="center" style="margin-top:50px;">
	<c:if test="${!empty messages}">
	<c:forEach items="${messages}" var="message">
     <table width="900" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FF9900">
      <tr>
        <td width="40" bgcolor="#FFFF99"><div align="center">编号</div></td>
        <td width="53" height="25" bgcolor="#FFFF99"><div align="center">客户姓名</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.kh_name}</div></td>
        <td width="140" bgcolor="#FFFFFF"><div align="center">${message.intime}</div></td>
        <td width="61" bgcolor="#FFFF99"><div align="center">分配人员</div></td>
        <td width="100" bgcolor="#FFFF99"><div align="center">来源</div></td>
        <td width="239" bgcolor="#FFFF99"><div align="center">回访情况</div></td>
        <td width="165" rowspan="3" bgcolor="#FFFFFF">下次回访时间: ${message.lasthf}</td>
      </tr>
      <tr>
        <td rowspan="2" bgcolor="#FFFFFF" width="40"><div align="center">${message.id}</div></td>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户电话</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.kh_tel}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.kh_ly}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">  </div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.tag}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>${message.fp_hf}</td>
  </tr>
  <tr>
    <td>
    
    </td>
  </tr>
</table>
</td>
      </tr>
      <tr>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户地址</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.kh_address}</div></td>
      </tr>
    </table>
      <br />
  <br />
  </c:forEach>
  </c:if>
 <c:if test="${empty messages}">
<span class="STYLE1">
没有找到相关信息
</span>
</c:if>
</div>
  </body>
</html>
