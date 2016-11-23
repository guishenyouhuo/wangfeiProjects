<%@ page language="java" import="java.util.*,com.wyqddc.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<link href="mycss/form.css"  type=text/css rel="stylesheet"/>
<link href="mycss/addcompany.css"  type=text/css rel="stylesheet"/>
<script type="text/javascript" src="myjs/jquery.form.js"></script>
<script type="text/javascript">
$(function(){
	$("#usereditbtn").click(function(){
		var rows = $('#tt').datagrid('getSelections'); 
		var oldname=rows[0].username;
		$("#oldname").val(oldname);
		var username=$("#usereditform #username").val();
		username=$.trim(username);
		var password=$("#usereditform #password").val();
		password=$.trim(password);
		var confirmpassword=$("#usereditform #confirmpassword").val();
		confirmpassword=$.trim(confirmpassword);
		if(username==""){
			$('#usereditform #username_tip').html("<font color='red'>用户名不能为空</font>");
		}
		if(password==""){
			$('#usereditform #password_tip').html("<font color='red'>密码不能为空</font>");
		}
		if(confirmpassword!=password){
			$('#usereditform #confirmpwd_tip').html("<font color='red'>两次输入密码不一致</font>");
		}
		if(username!=""&&password!=""&&confirmpassword==password){
		$.post("manager.do?method=checkUser",{"username":username},function(data){
			if(data=="exist"&&username!=oldname){
					$('#username_tip').html("<font color='red'>该用户已经存在</font>");
				}else{
					$("#usereditform").ajaxSubmit(function(message){
					$("#user_edit").window("close");	
					$("#tt").datagrid("load");
					$("#tt").datagrid("clearSelections");
			});
				}
			});	
		}
	});
});
</script>
		<div
			style="width:510px; height:350px; border:1px solid #CCC; margin:5px;">
			<div class="exlist">
				<form id="usereditform" method="post" action="manager.do?method=updateUser">
					<fieldset>
						<legend>修改用户</legend>
						<input type="hidden" id="oldname" name="oldname" />
							<table class="user_tab">
							<tr><td class="left">用户名：</td><td class="middle"><input type="text" readonly="readonly" id="username" name="username"/></td><td class="middle"><span class="usertipspan" id="username_tip"></span></td></tr>
							<tr><td class="left">密码：</td><td class="middle"><input type="password" id="password" name="password"/></td><td class="middle"><span class="usertipspan"  id="password_tip"></span></td></tr>
							<tr><td class="left">确认密码：</td><td class="middle"><input type="password" id="confirmpassword" name="confirmpassword"/></td><td class="middle"><span class="usertipspan" id="confirmpwd_tip"></span></td></tr>
							<tr><td class="left">用户类型：</td>
							<td class="middle">
							<select id="usertype" name="usertype">
							<option value="user">普通用户</option>
							<option value="manager">管理员</option>
							</select>
							</td><td></td></tr>
							<tr style="text-align: center;"><td colspan="3">
							<input type="button" value="修改" id="usereditbtn"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="重置" />
							</td></tr>
							</table>
						
					</fieldset>
				</form>
			</div>
		</div>
