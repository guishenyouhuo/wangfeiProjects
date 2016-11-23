<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<% 
String pageNow = (String)request.getSession().getAttribute("pageNow");
%>
<script type="text/javascript" src="myjs/jquery-1.6.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#moveBtn").click(function(){
			if(window.confirm("确定移动这条留言？"))
			{
				$.post("move.do",{"select":$("#sel").val(),"id":$("#messageid").val(),"last_num":$("#last_num").val()}, function(data){
					if(data==1)
					{
						window.parent.location.href="showmessage.do?pageNow=<%=pageNow%>";
					}
					else
					{
						alert("移动失败！");
					}
				});
			}
		});
		$("#delhref").click(function(){
			var num = $("#last_num").val();
			if(num==20)
			{
				if(window.confirm("回收站的留言删除将会是永久删除，删除之后将无法恢复，数据无价，请慎重！确定要永久删除？"))
				{
					$.post("delete.do",{"id":$("#messageid").val(),"last_num":num},function(data){
					if(data==1)
					{
						window.parent.location.href="showmessage.do?pageNow=<%=pageNow%>";
					}
					else
					{
						alert("删除失败！");
					}
			  	});
				}
			}
			else if(window.confirm("确定删除这条留言？"))
			{
				$.post("delete.do",{"id":$("#messageid").val(),"last_num":num},function(data){
					if(data==1)
					{
						window.parent.location.href="showmessage.do?pageNow=<%=pageNow%>";
					}
					else
					{
						alert("删除失败！");
					}
			  	});
			 }
		});
	});
</script>
<body>
<div align="center">
      <form id="moveForm" name="form1" ">
        <label>
          <select name="select" id="sel">
		<c:forEach items="${users}" var="user">
			<c:if test="${user.gs_num==num}">
				<option selected="selected" value="${user.gs_num}">${user.gs_name}</option>
			</c:if>
			<c:if test="${user.gs_num!=num}">
				<option value="${user.gs_num}">${user.gs_name}</option>
			</c:if>
		</c:forEach>
          </select>
        </label>
        <input type="button" name="Submit" id="moveBtn" value="移动" />

          <input type="hidden" name="MM_update" value="form1">
          <input type="hidden" name="MM_recordId" id = "messageid" value="${id}">
          <input type="hidden" name="last_num" id = "last_num" value="${num}">
      </form>
</div>
<br/>
<br/>
<div align="center"><a href=" javascript:void(0); "  id="delhref">删除留言</a></div>
</body>
</html>