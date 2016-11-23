<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@page import ="java.util.*,java.text.*"%>
<%
	SimpleDateFormat template= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today= template.format(new java.util.Date());
	//System.out.println("Time:"+today);
%>

<script type="text/javascript">
$(function(){
	$("#employee_recordsubbtn").click(function(){
	var r_employeeid=$("#r_employeeid").val();
	var record_theme=$("#employee_record_theme").val();
	var record_time=$("#employee_record_time").val();
	var record_abstract=$("#employee_record_abstract").val();
	if(record_time!=null&&record_time!=""){
		$("#employee_record_add_form").ajaxSubmit(function(message){
			$("#employee_add_record").window("close");	
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
		<form method="post" action="employee_recordAction.do" id="employee_record_add_form">
			<fieldset>
			<input type="hidden" name="employeeid" id="r_employeeid"/>
				<legend>添加跟进记录</legend>
				<div class="row">
					<label>主题:</label>&nbsp;&nbsp;<input style="width:200px" name="record_theme" id="employee_record_theme"
						type="text" />
				</div>
				<div class="row">
					<label>时间:</label>&nbsp;&nbsp;<input name="record_time" editable="false"  id="employee_record_time" style="width:150px" value="<%=today%>"/>
				</div>
				<div class="row">
					<label style=" vertical-align:top; margin-right:20px;">摘要:</label><textarea rows="7" cols="70"  name="record_abstract" id="employee_record_abstract"></textarea>
				</div>
				<div class="row">
					<center>
						<input type="button"  id="employee_recordsubbtn"  value="提交" class="button" /> <input
							type="reset" value="重置" class="button" />
					</center>
				</div>
			</fieldset>
		</form>
	</div>
</div>