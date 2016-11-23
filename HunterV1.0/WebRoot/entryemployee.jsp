<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link href="mycss/form.css"  type=text/css rel="stylesheet"/>
<link href="mycss/addcompany.css"  type=text/css rel="stylesheet"/>
<script type="text/javascript" src="myjs/jquery.form.js"></script>

<script type="text/javascript">
var hasChecked=false;
$(function()
{
	var check_show=false;
	$("#cc").combobox({url:'data.txt',valueField:'id',textField:'text'});

	$("#employee_submit").click(function()
	{
		//$("#currentusername").val($("#currentuser").html());

		//var check=checkEmployeeInput("#employee_name","#employee_phone","#employee_email","#employee_address");
		//if(check==true)
		//{
		if($("#employee_name").val()=="")
		{
			$("#e_name_tip").html("<font color='red'>求职者姓名不能为空</font>");
		}
		else
		{
			if(check_show==true)
			{
				var resume_update=$("#resume_update").val();
				if(resume_update==null||resume_update=="")
				{
					$("#e_resume_tip_add").html("<font color='red'>所上传简历不能为空</font>");
				}
				else  if(hasChecked==true)
		   		{
					$("#employeeform").ajaxSubmit(
					function(message)
					{
						$("#employeeinfo_entry").window("close");	
						$("#tt").datagrid("load");
						$("#tt").datagrid("clearSelections");
					}							
												);
		    	}
			  }
			
			else
			 {
				$("#employeeform").ajaxSubmit(
				function(message)
				{
					$("#employeeinfo_entry").window("close");	
					$("#tt").datagrid("load");
					$("#tt").datagrid("clearSelections");
				}							);
			 }
		}	 
			
	});
	$("#openupresume").click(function()
	{
		$("#beforeresume").after("<td class='showresume'><input id='resume_update' name='employee_resume' type='file' onchange='check_resume_1(this)'/> <font color='red'>*</font></br><span class='showresume' class='employee_tipspan' id='e_resume_tip_add'></span></td>");
		$("#openupresume").addClass("hide");
		$("#e_resume_show_tip").addClass("hide")
		check_show=true;
	}	);
}
);


function check_resume_1(filePath){
	var AllImgExt=".doc|.docx|";     //全部图片格式类型
	var file = filePath.value;  
    var fileExt = file.substring(file.lastIndexOf("."));
    if(AllImgExt!=0&&AllImgExt.indexOf(fileExt+"|")==-1)	  //判断文件类型是否允许上传
	  {
		hasChecked=false;
		$("#e_resume_tip_add").html("<font color='red'>文件类型错误，请上传doc或docx类型文件</font>");
	  }
	  else{
	  	hasChecked=true;
	  	$("#e_resume_tip_add").html("");
	  }
}
</script>
<div class="exlist">
	<form id="employeeform" method="post" enctype="multipart/form-data" action="employeeAction.do?method=addEmployee">
		<fieldset>
			<legend>求职者信息录入</legend>
			<!-- 
			<input type="hidden" name="username" id="currentusername"/>
			 -->
			<table>
				<tr style="height:60px; vertical-align:middle; padding-top:20px;">
					<td style=" width:100px; ">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">姓名:</label>
					</td>
					<td>
						<input id="employee_name"  name="employee_name" style="width:200px"  type="text" />
						&nbsp;<font color="red">*</font>
					<br/>
					<span class="employee_tipspan"  id="e_name_tip"></span>
					</td>
					
					<td style=" width:100px;">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">性别:</label>
					</td>
					<td style="padding-left:10px;">
						<select id="employee_sex" name="employee_sex">
							<option value='m' selected="selected">男</option>
							<option value='f'>女</option>
						</select>
					</td>
				</tr>
				<tr style="height:60px; vertical-align:middle">
					<td style="width:100px;">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">联系方式:</label>
					</td>
					<td>
						<input id="employee_phone" name="employee_phone" style="width:200px"  type="text"/>
					</td>
					
					<td style="width:100px;">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">地区:</label>
					</td>
					<td>
						<input id="employee_address" name="employee_address" style="width:200px"  type="text" />
					</td>
				</tr>
				<tr style="height:60px; vertical-align:middle">
					<td style=" width:100px;">
					<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">QQ/邮箱:</label>
					</td>
					<td>
						<input id="employee_email" name="employee_email" style="width:200px"  type="text" />
					</td>
					<td style=" width:100px;">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">证书状态:</label>
					</td>
					<td style=" padding-left:10px;">
						<select id="employee_cerstate" name="employee_cerstate">
							<option value='初始' selected="selected">初始</option>
							<option value='变更'>变更</option>
						</select>
					</td>
				</tr>
				<tr style="height:60px; vertical-align:middle">
					<td style="width:100px;">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">证书类型:</label>
					</td>
					<td style="padding-left:10px;" >
						<input id="cc" class="easyui-combobox" name="employee_certype" style="width:200px;"/>
					</td>
					
					
					<!-- ********************************************************************************* -->
					<td style="width:100px;" id="beforeresume">
					<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">简历:
					</label>
					</td>
					<td><input type="text" value="如需上传简历，请点击" id="openupresume" style="width:200px;"/>
					<br/>
					<span class="employee_tipspan"  id="e_resume_show_tip"></span>
					</td>
					<!-- ********************************************************************************* -->
				
				</tr>
			</table>
			
			<div class="row">
				<center>
					<input type="button" value="提交" class="button" id="employee_submit"/> 
					<input type="reset" value="重置" class="button" />
				</center>
			</div>
		</fieldset>
	</form>
</div>