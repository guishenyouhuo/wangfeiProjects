<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
var hasChecked=false;
$(function(){
	$("#contractbtn").click(function(){
		var contractmoney=$("#contractmoney").numberbox("getValue");
		var contractname=$("#contractname").val();
		var floatExp=/^\d+(\.\d+)?$/;
		var intExp=/^\\d+$/;
		if(contractmoney==""){
			$("#con_money_tip").html("<font color='red'>金额不能为空</font>");
		}
		else if(!floatExp.exec(contractmoney)&&!intExp.exec(contractmoney)){
			$("#con_money_tip").html("<font color='red'>金额只能是数字</font>");
		}
		else $("#con_money_tip").html("");
		if(contractname==null||contractname==""){
			$("#up_con_tip").html("<font color='red'>请上传合同</font>");
			hasChecked=false;
		}
		else $("#up_con_tip").html("");
		if(!hasChecked){
			$("#up_con_tip").html("<font color='red'>文件类型错误，请上传doc或docx类型文件</font>");
		}
		else $("#up_con_tip").html("");
		if(hasChecked&&contractmoney!=""&&(contractname!=null&&contractname!="")&&
		   (floatExp.exec(contractmoney)||intExp.exec(contractmoney))){
			$("#contractform").ajaxSubmit(function(message){
					$("#contract_upload").window("close");	
					$("#tt").datagrid("load");
					$("#tt").datagrid("clearSelections");
			});
		}
	});
});
function checkFile(filePath){
	var AllImgExt=".doc|.docx|";     //全部图片格式类型
	var file = filePath.value;  
    var fileExt = file.substring(file.lastIndexOf("."));
    if(AllImgExt!=0&&AllImgExt.indexOf(fileExt+"|")==-1)	  //判断文件类型是否允许上传
	  {
		hasChecked=false;
		$("#up_con_tip").html("<font color='red'>文件类型错误，请上传doc或docx类型文件</font>");
	  }
	  else{
	  	hasChecked=true;
	  	$("#up_con_tip").html("");
	  }
}
</script>
<div
	style="width:750px; height:440px; border:1px solid #CCC; margin:5px;">
	<div class="exlist">
		<form id="contractform"  method="post"  enctype="multipart/form-data"  action="followAction.do?method=upContract">
			<fieldset>
				<legend>上传合同</legend>
				<input type="hidden"  id="belongcid"  name="belongcid"/>
				<div class="row">
					<label>所属企业:</label>&nbsp;&nbsp;<input id="belongcompany" readonly="readonly" name="belongcompany" style="width:200px"
						type="text" />
				</div>
				<div class="row">
					<label>合同金额:</label>&nbsp;&nbsp;<input id="contractmoney" name="contractmoney"  style="width:200px"
						type="text" class="easyui-numberbox"  groupSeparator=","  data-options="min:0,precision:2" />&nbsp;&nbsp;&nbsp;<span class="contract_tipspan" id="con_money_tip"></span>
				</div>
				<div class="row">
					<label style=" vertical-align:top; margin-right:20px;">合同摘要:</label><textarea rows="5" cols="70" id="contractabstract" name="contractabstract"></textarea>
				</div>

				<div class="row">
					<label>上传凭证:</label><label><input
						type="file" id="contractname" name="contractname" onchange="checkFile(this)"/>
						&nbsp;&nbsp;&nbsp;<span class="contract_tipspan" id="up_con_tip"></span>
						</label>
				</div>
				<div class="row">
					<center>
						<input type="button" id="contractbtn" value="提交" class="button" /> <input
							type="reset" value="重置" class="button" />
					</center>
				</div>
			</fieldset>
		</form>
	</div>
</div>