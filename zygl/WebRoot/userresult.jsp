<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE1 {
	font-size: 18pt;
	color: #FF0000;
}
-->
</style>
</head>

<body>

<p align="center">查询结果</p>

<div align="center">
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
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center"><iframe src="fenpeiren.do?id=${message.fp_user}" width="50" height="14" frameborder="0" scrolling="no"></iframe></div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><div align="center">${message.tag}</div></td>
        <td rowspan="2" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>${message.fp_hf}</td>
  </tr>
  <tr>
    <td>
    <c:if test="${nowUser==message.fp_user}">
    <div align="right"><a href="userlyaction.do?method=modifyMessageView&id=${message.id}&type=task">修改回访</a></div>
    </c:if>
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
