<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>用户登录</TITLE>
<LINK href="mycss/Default.css" type=text/css rel=stylesheet>
<LINK href="mycss/xtree.css" type=text/css rel=stylesheet>
<LINK href="mycss/User_Login.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.6000.16674" name=GENERATOR>
<script type="text/javascript" src="jquery-easyui/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#IbtnEnter").click(function(){
			$("#loginForm").submit();
		});
	});

</script>
</HEAD>
<BODY id=userlogin_body>

<DIV id=user_login>
<DL>
  <DD id=user_top>
  <UL>
    <LI class=user_top_l></LI>
    <LI class=user_top_c></LI>
    <LI class=user_top_r></LI></UL>
  <DD id=user_main>
  <UL>
    <LI class=user_main_l></LI>
    <LI class=user_main_c>
    <DIV class=user_main_box>
    <div style="font-size: 14px; color: red; margin-left: 60px;">
    <c:if test="${!empty error}">
    ${error}
    </c:if>
    </div>
    <form id="loginForm" action="login.do" method="post">
    <UL>
      <LI class=user_main_text>用户名： </LI>
      <LI class=user_main_input><INPUT class=TxtUserNameCssClass id=TxtUserName 
      maxLength=20 name=username> </LI></UL>
    <UL>
      <LI class=user_main_text>密 码： </LI>
      <LI class=user_main_input><INPUT class=TxtPasswordCssClass id=TxtPassword 
      type=password  name=password> </LI></UL>
    <UL>
      <LI class=user_main_text>用户类型： </LI>
      <LI class=user_main_input>
      <SELECT id=DropExpiration name=usertype> 
      <OPTION value="user">普通用户</OPTION> 
        <OPTION value="manager">管理员</OPTION>     
      </SELECT> 
      </LI>
    </UL>
    </form>
    </DIV></LI>
    <LI class=user_main_r><INPUT class=IbtnEnterCssClass id=IbtnEnter 
    style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
    type=image src="images/pic/user_botton.gif" name="IbtnEnter"> </LI></UL>
    
  <DD id=user_bottom>
  <UL>
    <LI class=user_bottom_l></LI>
    <LI class=user_bottom_c> </LI>
    <LI class=user_bottom_r></LI></UL></DD></DL></DIV><SPAN id=ValrUserName 
style="DISPLAY: none; COLOR: red"></SPAN><SPAN id=ValrPassword 
style="DISPLAY: none; COLOR: red"></SPAN><SPAN id=ValrValidateCode 
style="DISPLAY: none; COLOR: red"></SPAN>
<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>
</BODY></HTML>

