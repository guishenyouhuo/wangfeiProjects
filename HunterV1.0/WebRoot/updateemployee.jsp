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
var check_show=false;
$(function()
{
	$("#cc_edit").combobox({url:'data.txt',valueField:'id',textField:'text'});

	$("#employee_submit_edit").click(function()
	{
		//$("#currentusername").val($("#currentuser").html());

		//var check=checkEmployeeInput("#employee_name_edit","#employee_phone_edit","#employee_email_edit","#employee_address_edit");
		//if(check==true)
		//{
		if($("#employee_name_edit").val()=="")
		{
			$("#e_name_tip_edit").html("<font color='red'>求职者姓名不能为空</font>");
		}
		else
		{
			if(check_show==true)
			{
				var resume_update=$("#resume_update_edit").val();
				if(resume_update==null||resume_update=="")
				{
					$("#e_resume_tip_edit").html("<font color='red'>所上传简历不能为空</font>");
				}
				else  if(hasChecked==true)
		   		{
					$("#employeeform_edit").ajaxSubmit(
					function(message)
					{
						$("#employeeinfo_edit").window("close");	
						$("#tt").datagrid("load");
						$("#tt").datagrid("clearSelections");
					}							
				  );
		    	}
			  }
		
			else
			 {
				$("#employeeform_edit").ajaxSubmit(
				function(message)
				{
					$("#employeeinfo_edit").window("close");	
					$("#tt").datagrid("load");
					$("#tt").datagrid("clearSelections");
				}							);
			 }
		}
	}	);
	
	$("#openupresume_edit_1").click(function()
	{
		$("#beforeresume_edit").after("<td class='showresume'><input id='resume_update_edit' name='employee_resume' type='file' onchange='check_resume(this)'/> <font color='red'>*</font></br><span class='showresume' class='employee_tipspan' id='e_resume_tip_edit'></span></td>");
		$("#openupresume_edit_1").addClass("hide");
		$("#e_resume_show_tip_edit").addClass("hide");
		check_show=true;
	}	);
}
);
function check_resume(filePath){
	var AllImgExt=".doc|.docx|";     //全部图片格式类型
	var file = filePath.value;  
    var fileExt = file.substring(file.lastIndexOf("."));
    if(AllImgExt!=0&&AllImgExt.indexOf(fileExt+"|")==-1)	  //判断文件类型是否允许上传
	  {
		hasChecked=false;
		$("#e_resume_tip_edit").html("<font color='red'>文件类型错误，请上传doc或docx类型文件</font>");
	  }
	  else{
	  	hasChecked=true;
	  	$("#e_resume_tip_edit").html("");
	  }
}
</script>

<div class="exlist">
	<form id="employeeform_edit" method="post" enctype="multipart/form-data" action="employeeAction.do?method=updateEmployee">
		<fieldset>
			<legend>求职者信息录入</legend>
			<input type="hidden" name="username" id="currentusername_edit"/>
			<table>
				<tr style="height:60px; vertical-align:middle; padding-top:20px;">
					<td style=" width:100px; ">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">姓名:</label>
					</td>
					<td>
						<input id="employee_name_edit"  name="employee_name" style="width:200px"  type="text" />
						&nbsp;<font color="red">*</font>
					    <br/>
					    <span class="employee_tipspan"  id="e_name_tip_edit"></span>
					</td>
					
					<td style=" width:100px;">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">性别:</label>
					</td>
					<td style=" padding-left:10px;">
						<select id="employee_sex_edit" name="employee_sex">
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
						<input id="employee_phone_edit" name="employee_phone" style="width:200px"  type="text"/>
					</td>
					
					<td style="width:100px;">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">地区:</label>
					</td>
					<td>
						<input id="employee_address_edit" name="employee_address" style="width:200px"  type="text" />
					</td>
				</tr>
				
				<tr style="height:60px; vertical-align:middle">
					<td style=" width:100px;">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">QQ/邮箱:</label>
					</td>
					<td>
						<input id="employee_email_edit" name="employee_email" style="width:200px"  type="text" />
					</td>
					
					<td style=" width:100px;">
						<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">证书状态:</label>
					</td>
					<td style="padding-left:10px;">
						<select id="employee_cerstate_edit" name="employee_cerstate">
							<option value='初始' selected="selected">初始</option>
							<option value='变更'>变更</option>
						</select>
					</td>
				</tr>
				
				<tr style="height:60px; vertical-align:middle">
					<td style="width:100px;">
						<label	style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">证书类型:</label>
					</td>
					<td style="padding-left:10px;" >
							<input id="cc_edit" class="easyui-combobox" name="employee_certype" style="width:200px;"/>
					</td>
					<!-- ********************************************************************************* -->
					<td style="width:100px;" id="beforeresume_edit">
					<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">简历:
					</label>
					</td>
					<td><input type="text" value="如需上传简历，请点击" id="openupresume_edit_1" style="width:200px;"/>
					<br/>
					<span class="employee_tip"  id="e_resume_show_tip_edit"></span>
					</td>
					<!-- ********************************************************************************* -->
				
				</tr>
			</table>
			<input type="hidden" name="eid" id="employee_eid_edit"/>
			<div class="row">
				<center>
					<input type="button" value="提交" class="button" id="employee_submit_edit"/> 
					<input type="reset" value="重置" class="button" />
				</center>
			</div>
		</fieldset>
	</form>
</div>