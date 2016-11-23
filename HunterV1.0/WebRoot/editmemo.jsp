<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	$("#memoeditsubbtn").click(function(){
	if($("#memoedit_time").datetimebox("getValue")!=""){
		$("#memo_edit_form").ajaxSubmit(function(message){
			$("#edit_memo").window("close");	
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
		<form method="post"  action="memoAction.do?method=editMemo"  id="memo_edit_form">
		<input type="hidden" name="meId" id="memid"/>
			<fieldset>
				<legend>添加备忘</legend>
				<div class="row">
					<label>主题:</label>&nbsp;&nbsp;<input style="width:200px" name="memoTheme" id="memoedit_theme"
						type="text" />
				</div>
				<div class="row">
					<label>时间:</label>&nbsp;&nbsp;<input class="easyui-datetimebox"  name="memoTime" editable="false"  id="memoedit_time" style="width:120px"/>
				</div>
				<div class="row">
					<label style=" vertical-align:top; margin-right:20px;">摘要:</label><textarea rows="7" cols="70"  name="memoAbstract" id="memoedit_abstract"></textarea>
				</div>
				<div class="row">
					<center>
						<input type="button"  id="memoeditsubbtn"  value="提交" class="button" /> <input
							type="reset" value="重置" class="button" />
					</center>
				</div>
			</fieldset>
		</form>
	</div>
</div>