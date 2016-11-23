<%@ page language="java" import="java.util.*,com.wyqddc.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<link href="mycss/form.css"  type=text/css rel="stylesheet"/>
<link href="mycss/addcompany.css"  type=text/css rel="stylesheet"/>
<script type="text/javascript" src="myjs/jquery.form.js"></script>
<script type="text/javascript">
var oldchecked=false;
$(function(){
	$("#pwdeditbtn").click(function(){
		var username=$("#currentuser").html();
		$("#pwdeditform #username").val(username);
		var oldpwd=$("#pwdeditform #oldpwd").val();
		oldpwd=$.trim(oldpwd);
		var newpwd=$("#pwdeditform #newpwd").val();
		newpwd=$.trim(newpwd);
		var confirmpwd=$("#pwdeditform #confirmpwd").val();
		confirmpwd=$.trim(confirmpwd);
		if(oldpwd==""){
			$('#pwdeditform #pwd_tip').html("<font color='red'>请输入原密码</font>");
		}
		if(newpwd==""){
			$('#pwdeditform #newpwd_tip').html("<font color='red'>新密码不能为空</font>");
		}
		else $('#pwdeditform #newpwd_tip').html("");
		if(confirmpwd!=newpwd){
			$('#pwdeditform #confirmnewpwd_tip').html("<font color='red'>两次输入密码不一致</font>");
		}
		 else $('#pwdeditform #confirmnewpwd_tip').html("");
		if(oldpwd!=""&&newpwd!=""&&confirmpwd==newpwd&&oldchecked){
					$("#pwdeditform").submit();
					$("#passwprd_edit").window("close");	
				}
	});	
	});
	function checkoldpwd(){
		var username=$("#currentuser").html();
	    var oldpwd=$("#pwdeditform #oldpwd").val();
		oldpwd=$.trim(oldpwd);
		$.post("manager.do?method=CheckPassword",{"username":username,"password":oldpwd},function(data){
			if(data=="0"){
					$('#pwd_tip').html("<font color='red'>密码错误</font>");
				}else{
					$('#pwd_tip').html("");
					oldchecked=true;
				}
			});	
	}
</script>
		<div
			style="width:510px; height:350px; border:1px solid #CCC; margin:5px;">
			<div class="exlist">
				<form id="pwdeditform" method="post" action="manager.do?method=updatePassword">
					<fieldset>
						<legend>修改密码</legend>
						<input type="hidden"  id="username"  name="username" />
							<table class="user_tab">
							<tr><td class="left">密码：</td><td class="middle"><input type="text"  onblur="checkoldpwd()"  id="oldpwd" name="oldpwd"/></td><td class="middle"><span class="pwd_tip" id="pwd_tip"></span></td></tr>
							<tr><td class="left">新密码：</td><td class="middle"><input type="password" id="newpwd" name="newpwd"/></td><td class="middle"><span class="pwd_tip"  id="newpwd_tip"></span></td></tr>
							<tr><td class="left">确认密码：</td><td class="middle"><input type="password" id="confirmpwd" name="confirmpwd"/></td><td class="middle"><span class="pwd_tip" id="confirmnewpwd_tip"></span></td></tr>

							<tr style="text-align: center;"><td colspan="3">
							<input type="button" value="修改" id="pwdeditbtn"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="重置" />
							</td></tr>
							</table>
						
					</fieldset>
				</form>
			</div>
		</div>
