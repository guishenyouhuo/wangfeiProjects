<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<%
	String userid = (String)request.getSession().getAttribute("userid");
 %>
<body>

<p align="center">查看所有留言 </p>
<c:if test="${empty messages}">
   无记录!
   </c:if>
   <c:if test="${!empty messages}">
   
	 <form id="form1" name="form1" method="post" action="showmessage.do?pageNow=${pageNow}&flag=0">
	   <p align="center">
	   <c:if test="${pageNow>1}">
	   <a href="showmessage.do?pageNow=1&flag=0">第一页</a> || 
	   <a href="showmessage.do?pageNow=${pageNow-1}&flag=0">上一页</a> ||
	   </c:if>
	   <c:if test="${pageNow<pageCount}">
	    <a href="showmessage.do?pageNow=${pageNow+1}&flag=0">下一页</a> 
	    || <a href="showmessage.do?pageNow=${pageCount}&flag=0">最后一页</a> 
	   </c:if>
		【共 ${messageCount} 条留言·当前在 ${pageNow} 页·共 ${pageCount} 页】 
	     <label>
	    &nbsp;&nbsp;跳转至
	          <input name="offset" type="text" id="offset" size="3" maxlength="3" />
	          页
	    </label>
	    <label>
	    <input type="submit" name="Submit" value="提交" />
	    </label></p></form>
	    
	<c:forEach items="${messages}" var="message">
    <table width="961" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#FF9900">
      <tr>
        <td width="40" bgcolor="#FFFF99"><div align="center">编号</div></td>
        <td width="53" height="25" bgcolor="#FFFF99"><div align="center">客户姓名</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.kh_name}</div></td>
        <td width="140" bgcolor="#FFFFFF"><div align="center">${message.intime}</div></td>
        <td width="61" bgcolor="#FFFF99"><div align="center">前所属人</div></td>
        <td width="61" bgcolor="#FFFF99"><div align="center">分配人员</div></td>
        <td width="100" bgcolor="#FFFF99"><div align="center">来源</div></td>
        <td width="239" bgcolor="#FFFF99"><div align="center">回访情况</div></td>
        <td width="165" rowspan="3" bgcolor="#FFFFFF"><iframe src="modifyren.do?num=${message.fp_user}&id=${message.id}&pageNow = ${pageNow}" width="165" height="80" frameborder="0" scrolling="no"></iframe></td>
      </tr>
      <tr>
        <td rowspan="2" bgcolor="#FFFFFF" width="40"><div align="center">${message.id}</div></td>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户电话</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.kh_tel}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.kh_ly}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center"><iframe src="fenpeiren.do?id=${message.last_user}" width="50" height="14" frameborder="0" scrolling="no"></iframe></div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center"><iframe src="fenpeiren.do?id=${message.fp_user}" width="50" height="14" frameborder="0" scrolling="no"></iframe></div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.tag}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.fp_hf}</div></td>
      </tr>
      <tr>
        <td height="25" bgcolor="#FFFF99"><div align="center">客户地址</div></td>
        <td width="80" bgcolor="#FFFFFF"><div align="center">${message.kh_address}</div></td>
      </tr>
    </table>
	<br/>
	</c:forEach>

 <form id="form1" name="form1" method="post" action="showmessage.do?pageNow=${pageNow}&flag=0">
	   <p align="center">
	   <c:if test="${pageNow>1}">
	   <a href="showmessage.do?pageNow=1&flag=0">第一页</a> || 
	   <a href="showmessage.do?pageNow=${pageNow-1}&flag=0">上一页</a> ||
	   </c:if>
	   <c:if test="${pageNow<pageCount}">
	    <a href="showmessage.do?pageNow=${pageNow+1}&flag=0">下一页</a> 
	    || <a href="showmessage.do?pageNow=${pageCount}&flag=0">最后一页</a> 
	   </c:if>
		【共 ${messageCount} 条留言·当前在 ${pageNow} 页·共 ${pageCount} 页】 
	     <label>
	    &nbsp;&nbsp;跳转至
	          <input name="offset" type="text" id="offset" size="3" maxlength="3" />
	          页
	    </label>
	    <label>
	    <input type="submit" name="Submit" value="提交" />
	    </label></p></form>
    </c:if>
<br />
</body>
</html>