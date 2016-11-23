<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
var imghasChecked=false;
$(function(){
	$("#upbillbtn").click(function(){
		var floatExp=/^\d+(\.\d+)?$/;
		var intExp=/^\\d+$/;
		var paynow=$("#paynow").numberbox("getValue");
		var billfile=$("#billfile").val();
		if(paynow==""){
			$("#bill_money_tip").html("<font color='red'>金额不能为空</font>");
		}
		else if(!floatExp.exec(paynow)&&!intExp.exec(paynow)){
			$("#bill_money_tip").html("<font color='red'>金额只能是数字</font>");
		}
		else $("#bill_money_tip").html("");
		if(billfile==null||billfile==""){
			$("#bill_file_tip").html("<font color='red'>请上传凭证</font>");
			imghasChecked=false;
		}
		else $("#bill_file_tip").html("");
		if(!imghasChecked){
			$("#bill_file_tip").html("<font color='red'>请上传图片格式凭证</font>");
		}
		else $("#bill_file_tip").html("");
		if(imghasChecked&&paynow!=""&&(billfile!=null&&billfile!="")&&(floatExp.exec(paynow)||intExp.exec(paynow))){
			$("#billupform").ajaxSubmit(function(message){
					$("#billupload").window("close");	
					$("#tt").datagrid("load");
					$("#tt").datagrid("clearSelections");
			});
		}
	});
	
});
function checkBill(filePath){
	var AllImgExt=".jpg|.jpeg|.gif|.bmp|.png|"//全部图片格式类型
	var file = filePath.value;  
    var fileExt = file.substring(file.lastIndexOf("."));
    if(AllImgExt!=0&&AllImgExt.indexOf(fileExt+"|")==-1)	  //判断文件类型是否允许上传
	  {
		imghasChecked=false;
		$("#bill_file_tip").html("<font color='red'>请上传图片格式凭证</font>");
	  }
	  else{
	  	imghasChecked=true;
	  	$("#bill_file_tip").html("");
	  }
}
</script>
<div
	style="width:580px; height:450px; border:1px solid #CCC; margin:5px;">
	<div class="">
		<form method="post"  id="billupform"  enctype="multipart/form-data"  action="billupAction.do">
			<fieldset>
				<legend>上传清单</legend>
				<input type="hidden" id="belongcontract" name="belongcontract"/>
				<div class="row">
					<label>所属合同:</label> &nbsp;&nbsp;<input readonly="readonly" style="width:200px" id="belongcontract1" name="belongcontract1"
						type="text" />
				</div>

				<div class="row">
					<label>应付金额:</label>&nbsp;&nbsp; <input readonly="readonly" style="width:200px" id="shouldpay" name="shouldpay"
						type="text" />
				</div>

				<div class="row">
					<label>实付金额:</label>&nbsp;&nbsp; <input style="width:200px" id="paynow" name="paynow"
						type="text" class="easyui-numberbox" groupSeparator=","  />&nbsp;&nbsp;&nbsp;<span class="bill_tipspan" id="bill_money_tip"></span>
				</div>

				<div class="row">
					<label style=" vertical-align:top; margin-right:20px;">清单摘要:</label>
					<textarea rows="5" cols="50"  id="billabstract" name="billabstract"></textarea>
				</div>

				<div class="row">
					<label>上传凭证:</label>&nbsp;&nbsp;  <input id="billfile" name="billfile"  onchange="checkBill(this)"
						type="file" />&nbsp;&nbsp;&nbsp;<span class="bill_tipspan" id="bill_file_tip"></span>
				</div>
				<div class="row">
					<center>
						<input type="button" id="upbillbtn" value="提交" class="button" /> <input
							type="reset" value="重置" class="button" />
					</center>
				</div>
			</fieldset>
		</form>
	</div>
</div>