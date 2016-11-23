<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@page import ="java.util.*,java.text.*"%>
<%
	SimpleDateFormat template= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today= template.format(new java.util.Date());
%>

<script type="text/javascript">
$(function(){
	$("#recordsubbtn").click(function(){
	var r_companyid=$("#r_companyid").val();
	var record_theme=$("#record_theme").val();
	var record_time=$("#record_time").val();
	var record_abstract=$("#record_abstract").val();
	if(record_time!=null&&record_time!=""){
		$("#record_add_form").ajaxSubmit(function(message){
			$("#add_record").window("close");	
			$("#tt").datagrid("load");
			$("#tt").datagrid("clearSelections");
		});
	}
  });
});
</script>
<div
	style="width:700px; height:420px; border:1px solid #CCC; margin:5px;">
	<div class="exlist">
		<form method="post" action="recordAction.do" id="record_add_form">
			<fieldset>
			<input type="hidden" name="companyid" id="r_companyid"/>
				<legend>添加跟进记录</legend>
				<div class="row">
					<label>主题:</label>&nbsp;&nbsp;<input style="width:200px" name="record_theme" id="record_theme"
						type="text" />
				</div>
				<div class="row">
					<!--
					<label>时间:</label>&nbsp;&nbsp;<input class="easyui-datetimebox"  name="record_time" editable="false"  id="record_time" style="width:120px"/>
					 -->
					
					<label>时间:</label>&nbsp;&nbsp;<input name="record_time" editable="false"  id="record_time" style="width:150px" value="<%=today%>"/>
					 
				</div>
				<div class="row">
					<label style=" vertical-align:top; margin-right:20px;">摘要:</label><textarea rows="7" cols="70"  name="record_abstract" id="record_abstract"></textarea>
				</div>
				<div class="row">
					<center>
						<input type="button"  id="recordsubbtn"  value="提交" class="button" /> <input
							type="reset" value="重置" class="button" />
					</center>
				</div>
			</fieldset>
		</form>
	</div>
</div>