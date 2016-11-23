<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jquery-1.3.2.js"></script> 
<script type="text/javascript" src="jquery-calendar.js"></script> 
<link rel="stylesheet" type="text/css" href="jquery-calendar.css" /> 
</head>

<body>
<form id="form1" name="form1" method="post" action="marketaction.do?method=doModify">
  <p>&nbsp;</p>
  <table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
  <caption> 修改回访 </caption>
    <tr>
      <td width="168" height="30">客户姓名：</td>
      <td width="332">${message.kh_name}</td>
    </tr>
    <tr>
      <td height="30">客户电话：</td>
      <td>${message.kh_tel}</td>
    </tr>
    <tr>
      <td height="30">客户地址：</td>
      <td>${message.kh_address}</td>
    </tr>
    <tr>
      <td height="30">客户留言：</td>
      <td>${message.kh_ly}</td>
    </tr>
    <tr>
      <td height="30">回访情况：</td>
      <td><label>
        <textarea name="textarea" cols="45" rows="10">${message.fp_hf}</textarea>
      </label></td>
    </tr>
    <tr>
	  <td height="30">下次回访日期：</td>
	  <td><label>
	    <input name="lasthg" type="text" id="lasthg" maxlength="10"  value="${message.lasthf}" onfocus="$(this).calendar()" readonly="readonly"/>
	  （日期格式：2012-8-1）</label></td>
	</tr>

    <tr>
      <td height="30" colspan="2"><label>
        <div align="center">
          <input type="submit" name="Submit" value="提交" />
        </div>
      </label></td>
    </tr>
  </table>
  <input type="hidden" name="mid" value="${message.id}"/>
  <input type="hidden" name="pageNow" value="${pageNow}"/>
</form>
</body>
</html>