<%@ page language="java" import="java.util.*,com.wyqddc.entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="mycss/form.css"  type=text/css rel="stylesheet"/>
<link href="mycss/addcompany.css"  type=text/css rel="stylesheet"/>
<script type="text/javascript" src="myjs/jquery.form.js"></script>
<script type="text/javascript">
$(function(){
	$("#company_edit_subbtn").click(function(){
		var rows = $('#tt').datagrid('getSelections'); 
		var oldname=rows[0].CName;
		oldname=$.trim(oldname);
		var companyname=$("#company_name_edit").val();
		companyname=$.trim(companyname);
		if($("#company_name_edit").val()=="")
		{
			$('#name_tip_edit').html("<font color='red'>公司名不能为空</font>");
		}
		else
		{
		$.post("companyAction.do?method=checkCompanyName",{"company":companyname},function(data){
			if(data=="exist"&&oldname!=companyname){
					$('#name_tip_edit').html("<font color='red'>公司名已经存在</font>");
				}else{
					$("#companyform_edit").ajaxSubmit(function(message){
					$("#companyinfo_edit").window("close");	
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
			style="width:770px; height:500px; border:1px solid #CCC; margin:5px;">
			<div class="exlist">
			
				<form id="companyform_edit" method="post" action="companyAction.do?method=updateCompany">
					<fieldset>
						<legend>企业信息查看与修改</legend>
						<table>
							<tr style="height:60px; vertical-align:middle; padding-top:20px;">
								<td style=" width:110px; ">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">企业全称:</label>
								</td>
								<td>
									<input id="company_name_edit"  name="company_name" style="width:200px" type="text" />&nbsp;<font color="red">*</font><br/>
									<span class="tipspan"  id="name_tip_edit"></span>
								</td>
								<td style=" width:110px; ">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">企业所在地:</label>
								</td>
								<td>
									<input id="company_address_edit" name="company_address"  style="width:200px" type="text" />
								</td>
							</tr>
							<tr style="height:60px;vertical-align:middle; padding-top:20px;">
								<td style=" width:110px;">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">QQ/邮箱:</label>
								</td>
								<td>
									<input id="company_email_edit"  name="company_email" style="width:200px"  type="text" />	
								</td>
								<td style="width:110px;">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">企业座机:</label>
								</td>
								<td>
									<input id="company_telephone_edit" name="company_telephone" style="width:200px"  type="text" />	
								</td>
							</tr>
							<tr style="height:60px;vertical-align:middle; padding-top:20px;">
								<td style=" width:110px;">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">主要联系人:</label>
								</td>
								<td>
									<input id="company_linkman_edit"  name="company_linkman" style="width:200px" type="text" />
								</td>
								<td style="width:110px;">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">性别:</label>
								</td>
								<td>
									<select id="company_sex_edit" name="company_sex" >
										<option value="m" selected="selected">男</option>
										<option value="f">女</option>
									</select>
								</td>
							</tr>
							
							<tr style="height:60px;vertical-align:middle; padding-top:20px;">
								<td style=" width:110px;">
									<label style="height:20px;font-size:16px;line-height:20px; margin-left:20px;margin:0 10px; float:right; ">称谓:</label>
								</td>
								<td>
									<input id="company_title_edit"  name="company_title" style="width:200px"  type="text" />
								</td>
								<td style=" width:110px;">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">主联系方式:</label>
								</td>
								<td>
									<input id="company_phone_edit"  name="company_phone" style="width:200px"  type="text" />	
								</td>
							</tr>
							
							<tr style="height:60px;vertical-align:middle; padding-top:20px;">
								<td style=" width:110px;">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">次要联系人:</label>
								</td>
								<td>
									<input id="company_secondlinkman_edit"  name="company_secondlinkman" style="width:200px"  type="text" />
								</td>
								
								<td style=" width:110px;">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">次联系方式:</label>
								</td>
								<td>
									<input id="company_secondphone_edit"  name="company_secondphone" style="width:200px"  type="text" />	
								</td>
							</tr>
							
							<tr style="height:60px;vertical-align:middle; padding-top:20px;">
								<td style=" width:110px;">
									<label style="height:20px;font-size:16px;line-height:20px;margin:0 10px; float:right;">岗位需求:</label>
								</td>
								<td>
									<input id="company_need_edit"  name="company_need" style="width:200px"  type="text" />
								</td>
							</tr>
							
						</table>
						<input type="hidden" name="cid" id="company_cid"/>
						<div class="row">
						<center>
							<input  id="company_edit_subbtn"  type="button" value="提交" class="button" /> 
							<input type="reset" value="重置" class="button" />
						</center>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
