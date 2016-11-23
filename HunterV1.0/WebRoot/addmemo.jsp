<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	$("#memoaddsubbtn").click(function(){
	if($("#memoadd_time").datetimebox("getValue")!=""){
		$("#memo_add_form").ajaxSubmit(function(message){
			$("#add_memo").window("close");	
			$("#tt").datagrid("load");
			$("#tt").datagrid("clearSelections");
		});
	   }
	   else {
	   	alert("请填写信息");
	   }
	});
});
</script>
<div
	style="width:700px; height:420px; border:1px solid #CCC; margin:5px;">
	<div class="exlist">
		<form method="post"  action="memoAction.do?method=addMemo"  id="memo_add_form">
			<fieldset>
				<legend>添加备忘</legend>
				<div class="row">
					<label>主题:</label>&nbsp;&nbsp;<input style="width:200px" name="memoTheme" id="memoadd_theme"
						type="text" />
				</div>
				<div class="row">
					<label>时间:</label>&nbsp;&nbsp;<input class="easyui-datetimebox"  name="memoTime" editable="false"  id="memoadd_time" style="width:120px"/>
				</div>
				<div class="row">
					<label style=" vertical-align:top; margin-right:20px;">摘要:</label><textarea rows="7" cols="70"  name="memoAbstract" id="memoadd_abstract"></textarea>
				</div>
				<div class="row">
					<center>
						<input type="button"  id="memoaddsubbtn"  value="提交" class="button" /> <input
							type="reset" value="重置" class="button" />
					</center>
				</div>
			</fieldset>
		</form>
	</div>
</div>