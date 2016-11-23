<%@ page language="java" import="java.util.*,com.wyqddc.entity.*,com.wyqddc.utils.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户关系管理系统</title>
<link href="mycss/base.css" type=text/css rel="stylesheet" />
<link href="mycss/style.css" type=text/css rel="stylesheet" />
<link href="mycss/form.css" type=text/css rel="stylesheet" />
<link href="mycss/manager.css" type=text/css rel="stylesheet" />
 <link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css"/>
 <link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css"/>
 <script type="text/javascript" src="jquery-easyui/jquery-1.4.2.min.js"></script>
 <script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
 <script type="text/javascript" src="jquery-easyui/easyui-lang-zh_CN.js"></script>

<script type=text/javascript src="myjs/leftTree.js"></script> 
<script type=text/javascript src="myjs/commen.js"></script> 
<script type=text/javascript src="myjs/manager.js"></script> 
<script type="text/javascript" src="myjs/showtime.js"></script>
<script type="text/javascript" src="myjs/follow.js"></script>
<script type="text/javascript" src="myjs/checkcompany.js"></script>
<script type=text/javascript src="myjs/employee.js"></script> 
<script type="text/javascript" src="myjs/checkemployee.js"></script>
<script type="text/javascript" src="myjs/memo.js"></script>
<script type="text/javascript">
  $(function(){
   	$("#tb4").removeClass("hide");
    memoRecord ("当日备忘","memoAction.do?method=getMemo&type=today");    
    $("body a").click(function(){
    	$.post("manager.do?method=checkLog",function(data){
    	 if(data=="err"){
    	 	window.location='login.jsp';
    	 }
    	});
    });
  });
</script>
</head>

<body>
	<div style=" width:1000px; margin:auto; height:660px; border:#CCC;">
    	<div style="width:100%; height:80px; background-color:#bbc5e5; position:relative;">
        	<img src="images/pic/LOGO_1.png" height="70px" style="margin-left:20px; margin-top:5px; float:left"/>
            <div style="font-size:20px; position:absolute; bottom:0px; left:120px; font-weight:bold; color:#135698; font-family:Microsoft YaHei;">客户关系管理系统
            </div>
            <div  style="position: absolute;right:15px; top:10px; font-size: 13px; color:#135698; font-weight:bold; font-family: Microsoft YaHei;" >
            <c:if test="${!empty user}">
        	<span id="currentuser" >${user.username}</span>
        	</c:if>
        	<c:if test="${empty user }">
        	<script>window.location='login.jsp'</script>
        	</c:if>
        	您好，当前时间为：
            <span id="date"></span>
			</div>
        </div>
        <div style=" height:30px; width:15%; background:url(images/pic/nav_left_1.png) repeat-x; float:left; padding-left:20px;">
        	<span style=" line-height:40px; font-weight:bold; color:#5D7786;">CRM管理系统</span>
        </div>
        <div style=" height:30px; width:77px;background:url(images/pic/nav_left_2.png); background-repeat:no-repeat;float:left;">
        </div>
        <div style=" height:30px; width:30%;background:url(images/pic/nav_mid.png) repeat-x; float:left;">
        	<span style=" line-height:30px; font-size:12px;color:#5D7786;">当前用户:
        	<c:if test="${!empty user }">
        	<span id="currentuser" >${user.username}</span>
        	</c:if>
        	<c:if test="${empty user }">
        	<script>window.location='login.jsp'</script>
        	</c:if>
        	</span>
        </div>
        <div style=" height:30px; width:61px; background:url(images/pic/nav_right_1.png) no-repeat; float:left;">
        </div>
        <div style=" height:30px; width:100%;background:url(images/pic/nav_right_2.png) repeat-x;">
        	<div style="padding-right:20px; padding-top:2.5px;">
        	<a href="javascript:void(0);"  id="entryemployee_btn" class="easyui-linkbutton"  style=" float:right;color:#5D7786;margin-left:10px;">录入求职者信息</a>
        	<a href="javascript:void(0);"  id="entrycompany_btn"  class="easyui-linkbutton"  style=" float:right;color:#5D7786;margin-left:10px;">录入企业信息</a>

            </div>
        </div>
        <div style="clear:both;">
      	</div>
		<!--#####################mid-leftstart######################-->
		<div style="overflow:hidden; width:1000px;">
			<div style="width:210px; height:500px; border:#CCC solid 1px; margin:5px 0px 5px; float:left; background-color: #F4F4F4">
	        	<div class="pnav-cnt">
	   				 <div class="pnav-box" id="letter-a">
	        			<div class=box-title id="company">
	        				<a id="fold" class="btn-fold " href="#"></a>
	           				<a id="unfold" class="btn-unfold hidden" href="#"></a>
	        				<span class="pnav-letter ">企业客户管理</span>
	                    </div>
	        			<ul class="box-list hidden">
	            			<li><a href="javascript:void(0);"  id="followcompany">线索库 </a> </li>
	            			<li><a href="javascript:void(0);"  id="companyfollow">客户跟踪</a></li>
	            			<li><a href="javascript:void(0);" id="intentClient">意向客户</a></li>
	                		<li><a href="javascript:void(0);" id="writenobill">签单未到账</a></li>
	                		<li><a href="javascript:void(0);" id="billfollow" >订单跟踪</a></li>
	                        <li><a href="javascript:void(0);"  id="commenlib">共享库 </a></li>
	       	   		  </ul>
	    			</div>
	    			<div class="pnav-box" id="letter-b">
	        			<div class=box-title id="worker">
	                    	<a id="fold" class="btn-fold" href="#"></a>
	                        <a id="unfold" class="btn-unfold hidden" href="#"></a>
	                        <span class="pnav-letter ">求职者管理</span>
	                    </div>
	        			<ul class="box-list hidden">
	            			<li><a href="javascript:void(0);" id="employeelib">人才库</a></li>
	            			<li><a href="javascript:void(0);" id="employee_intent">意向人才</a></li>
	            			<li><a href="javascript:void(0);" id="employee_mark">已签人才</a></li>
	                        <li><a href="javascript:void(0);" id="employee_allocate">成功分配记录</a></li>
	                        <li><a href="javascript:void(0);" id="employee_otherall">其他人才</a></li>
					    </ul>
					 </div>
					 <c:if test="${user.usertype=='manager'}">
						 <div class="pnav-box" id="letter-c">
		        			<div class=box-title id="manager">
		                    	<a id="fold" class="btn-fold" href="#"></a>
		                        <a id="unfold" class="btn-unfold hidden" href="#"></a>
		                        <span class="pnav-letter ">员工及调度管理</span>
		                    </div>
		        			<ul class="box-list hidden">
		            			<li><a href="javascript:void(0);" id="manageUser">员工管理</a></li>
		                        <li><a href="javascript:void(0);" id="transfSrc">资源调度</a></li>
						    </ul>
						 </div>
					 </c:if>
					 <div class="pnav-box" id="letter-d">
		        			<div class=box-title id="memo">
		                    	<a id="fold" class="btn-fold" href="#"></a>
		                        <a id="unfold" class="btn-unfold hidden" href="#"></a>
		                        <span class="pnav-letter ">备忘录管理</span>
		                    </div>
		        			<ul class="box-list hidden">
		            			<li><a href="javascript:void(0);" id="todayMemo">当日备忘</a></li>
		                        <li><a href="javascript:void(0);" id="otherMemos">其他备忘</a></li>
						    </ul>
					</div>
				</div>
	   		</div>
     <!--#####################mid-leftend######################-->
     <!--**************************************以下为模块变化部分，用相应div进行替换**********************************************-->   
       <div style="width:778px; height:500px; border:1px solid #CCC; margin:5px  5px; margin-left:220px;">
				<div id="tb" style="padding:5px;height:auto">
					
				<div id="tb1" class="hide">
					<div style="margin-bottom:5px" id="crudbar">
						<a href="javascript:void(0);" id="add" class="easyui-linkbutton"
							iconCls="icon-add" title="添加" plain="true">添加</a> <a
							href="javascript:void(0);" id="edit" class="easyui-linkbutton"
							iconCls="icon-edit" title="修改" plain="true">查看及修改</a> <a
							href="javascript:void(0);" id="delete" class="easyui-linkbutton"
							iconCls="icon-remove" title="删除" plain="true">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="followclients"><a href="javascript:void(0);"  id="followclient" class="easyui-linkbutton" >客户跟踪</a>&nbsp;&nbsp;</span>
							<span id="intents"><a href="javascript:void(0);"  id="intent" class="easyui-linkbutton" >意向</a>&nbsp;&nbsp;</span>
							<span id="writebills"><a href="javascript:void(0);"  id="writebill" class="easyui-linkbutton" >签单</a>&nbsp;&nbsp;</span>
							<span id="upbills"><a href="javascript:void(0);"  id="upbill" class="easyui-linkbutton" >上传清单</a>&nbsp;&nbsp;</span>
							<span id="addrecords"><a href="javascript:void(0);"  id="addrecord" class="easyui-linkbutton" >添加跟进记录</a>&nbsp;&nbsp;</span>
							<span id="checkrecords"><a href="javascript:void(0);"  id="checkrecord" class="easyui-linkbutton" >查看跟进记录</a>&nbsp;&nbsp;</span>
							<span id="checkcontracts"><a href="javascript:void(0);"  id="checkcontract" class="easyui-linkbutton" >查看合同</a>&nbsp;&nbsp;</span>
							<span id="checkbills"><a href="javascript:void(0);"  id="checkbill" class="easyui-linkbutton" >查看清单</a>&nbsp;&nbsp;</span>
							<span id="giveups"><a href="javascript:void(0);"  id="giveup" class="easyui-linkbutton" >放弃</a>&nbsp;&nbsp;</span>
							<span id="newproject"><a href="javascript:void(0);"  id="new_project" class="easyui-linkbutton"  style="float: right;">新建项目</a></span>
					</div>
					<div id="timeSearch">
						&nbsp;公司地址: <input type="text" id="address_search" style="width:150px; height: 20px;line-height: 20px;"/>
								&nbsp;&nbsp;&nbsp; 公司名称： <input id="key" type="text"
								style="width:150px; height: 20px;line-height: 20px;"/> <a
									href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doSearch();">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" onclick="clearSearch();">清空查询条件</a>
									<span id="intoMines"><a href="javascript:void(0);" class="easyui-linkbutton"  id="intoMine"  style="float: right;">添加线索</a></span>
					</div>
					</div>
					<div id="tb2" class="hide">
						<div style="margin-bottom:5px" id="usercrudbar">
							<a href="javascript:void(0);" id="useradd" class="easyui-linkbutton"
								iconCls="icon-add" title="添加" plain="true">添加</a> <a
								href="javascript:void(0);" id="useredit" class="easyui-linkbutton"
								iconCls="icon-edit" title="修改" plain="true">查看及修改</a> <a
								href="javascript:void(0);" id="userdelete" class="easyui-linkbutton"
								iconCls="icon-remove" title="删除" plain="true">删除</a>
						</div>
						<div id="usersearch">
						    &nbsp; 用户名： <input id="userKey" type="text"
								style="width:100px; height: 20px;line-height: 20px;"/> <a
									href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doUserSearch();">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" onclick="clearUserSearch();">清空查询条件</a>
						</div>
					</div>
					
					<div id="tb3" class="hide">
					<div style="margin-bottom:5px" id="employeecrudbar">
							<a href="javascript:void(0);" id="employeeadd" class="easyui-linkbutton"
								iconCls="icon-add" title="添加" plain="true">添加</a> 
							<a href="javascript:void(0);" id="employeeedit" class="easyui-linkbutton"
								iconCls="icon-edit" title="修改" plain="true">查看及修改</a> 
							<a href="javascript:void(0);" id="employeedelete" class="easyui-linkbutton"
								iconCls="icon-remove" title="删除" plain="true">删除</a>&nbsp;&nbsp;&nbsp;
								
							<span id="employeeintents"><a href="javascript:void(0);" id="employeeintent" class="easyui-linkbutton">意向人才</a>&nbsp;&nbsp;&nbsp;</span>
							<span id="employeemarks"><a href="javascript:void(0);" id="employeemark" class="easyui-linkbutton">已签人才</a>&nbsp;&nbsp;&nbsp;</span>
							<span id="employeeallocates"><a href="javascript:void(0);" id="employeeallocate" class="easyui-linkbutton">分配</a>&nbsp;&nbsp;&nbsp;</span>
							<span id="employeeaddrecords"><a href="javascript:void(0);"  id="employeeaddrecord" class="easyui-linkbutton" >添加跟进记录</a>&nbsp;&nbsp;</span>
							<span id="employeecheckrecords"><a href="javascript:void(0);"  id="employeecheckrecord" class="easyui-linkbutton" >查看跟进记录</a>&nbsp;&nbsp;</span>
							<span id="checkresumes"><a href="javascript:void(0);" id="checkresume" class="easyui-linkbutton">查看简历</a></span>
						</div>
					<div id="timeSearch">	
								&nbsp;&nbsp;&nbsp; 求职者姓名： <input id="key_ename"  type="text"
								style="width:70px; height: 20px;line-height: 20px;"/> 
								&nbsp;&nbsp;&nbsp; 求职者地区： <input id="key_eaddress"  type="text"
								style="width:70px; height: 20px;line-height: 20px;"/>
								&nbsp;&nbsp;&nbsp; 证书类型： <input id="key_etype" type="text"
								style="width:70px; height: 20px;line-height: 20px;"/>
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doEmployeeSearch();">查询</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" onclick="clearEmployeeSearch();">清空查询条件</a>
					</div>
				</div>
					
					<c:if test="${user.usertype=='manager'}">
					<div id="transf_tb"  class="hide">
						<div id="searchSrc">
						&nbsp; 公司地址： <input id="tran_ser_address" type="text"
								style="width:100px; height: 20px;line-height: 20px;"/>
							&nbsp;&nbsp;&nbsp; 公司名： <input id="tran_ser_com" type="text"
								style="width:100px; height: 20px;line-height: 20px;"/> <a
									href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-search" onclick="SearchSrc();">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" onclick="clearSrcSearch();">清空查询条件</a>
									<span id="transfs"><a href="javascript:void(0);"  id="transf" class="easyui-linkbutton"  style="float: right;">调整资源</a></span>
						</div>
					</div>
					</c:if>
					<!--630  -->
					<div id="tb4" class="hide">
							<a href="javascript:void(0);" id="memoadd" class="easyui-linkbutton"
								iconCls="icon-add" title="添加" plain="true">添加</a> 
							<a href="javascript:void(0);" id="memoedit" class="easyui-linkbutton"
								iconCls="icon-edit" title="修改" plain="true">查看及修改</a> 
							<a href="javascript:void(0);" id="memodelete" class="easyui-linkbutton"
								iconCls="icon-remove" title="删除" plain="true">删除</a>
					</div>
				</div>
				<table id="tt" class="hide">
				</table>
		</div>
	</div>
      <!--***************************************************************************************************************-->

        <div style=" height:30px; width:980px;background:url(images/pic/nav_right_2.png) repeat-x; padding-left:20px;">
        	<span style=" line-height:30px; font-size:12px;color:#5D7786;">版权所有©武汉环球猎人信息咨询有限公司</span>
            <div style="padding-right:20px; padding-top:2.5px; float:right;">
            <a href="javascript:void(0);"  id="logout"  class="easyui-linkbutton"  style=" float:right;color:#5D7786;margin-left:10px;">退出系统</a>
            <a href="javascript:void(0);"  id="editpwd"  class="easyui-linkbutton"  style=" float:right;color:#5D7786;margin-left:10px;">修改密码</a>
            </div>
        </div>
    </div>
    <!-- -----隐藏弹出窗口--- -->
    <div id="companyinfo_entry" class="easyui-window"  collapsible="false"  resizable="false"  maximizable="false" closed="true" modal="true"
					shadow="true" minimizable="true" title="录入企业信息"
					style="width:800px;height:560px;">
					<jsp:include page="entrycompany.jsp" />
	</div>
	
	<div id="companyinfo_edit" class="easyui-window" closed="true" collapsible="false"  resizable="false"  maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="修改"
					style="width:800px;height:450px;">
					<jsp:include page="updatecompany.jsp" />
	</div>
	<div id="user_add" class="easyui-window" closed="true" collapsible="false"  resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="添加"
					style="width:540px;height:400px;">
					<jsp:include page="adduser.jsp" />
	</div>
	<div id="user_edit" class="easyui-window" closed="true"  collapsible="false"  resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="修改"
					style="width:540px;height:400px;">
					<jsp:include page="updateuser.jsp" />
	</div>
	<div id="passwprd_edit" class="easyui-window" closed="true"  collapsible="false" resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="修改密码"
					style="width:540px;height:400px;">
					<jsp:include page="updatepassword.jsp" />
	</div>
	<div id="contract_upload" class="easyui-window" closed="true"  collapsible="false"  resizable="false"  maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="上传合同"
					style="width:780px;height:500px;">
					<jsp:include page="upcontract.jsp" />
	</div>
	<div id="billupload" class="easyui-window" closed="true"  collapsible="false" resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="上传清单"
					style="width:605px;height:510px;"> 
					<jsp:include page="upbill.jsp" />
	</div>
	<div id="checkrecordwin" class="easyui-window" closed="true"  collapsible="false"  resizable="false"  maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="查看记录"
					style="width:830px;height:540px;">
					<table id="records" class="hide"></table>
	</div>
	<div id="employeecheckrecordwin" class="easyui-window" closed="true"  collapsible="false"  resizable="false"  maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="查看记录"
					style="width:830px;height:540px;">
					<table id="employeerecords" class="hide"></table>
	</div>
	<div id="add_record" class="easyui-window" closed="true"  collapsible="false" resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="添加跟进记录"
					style="width:728px;height:480px;"> 
					<jsp:include page="addrecord.jsp" />
	</div>
	<!-- 6.24 -->
	<div id="employee_add_record" class="easyui-window" closed="true"  collapsible="false" resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="添加跟进记录"
					style="width:728px;height:480px;"> 
					<jsp:include page="employee_addrecord.jsp" />
	</div>
	
	<div id="billshtml" class="easyui-window" closed="true"  collapsible="false" resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="清单"
					style="width:900px;height:540px;"> 
					<jsp:include page="bills.html" />
	</div>
	<div id="tranUserdatagrid" class="easyui-window" closed="true"  collapsible="false" resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="调度目标"
					style="width:344px;height:420px;"> 
	<c:if test="${user.usertype=='manager'}">
	    <div id="u_tb">
	    	&nbsp; 用户名： <input id="tran_userKey" type="text"
								style="width:100px; height: 20px;line-height: 20px;"/> <a
									href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-search" onclick="trandoUserSearch();">查询</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" onclick="tranclearUserSearch();">清空查询条件</a>
	    </div>
		<table id="srctransf" class="hide">
		</table>
		<div style="text-align: center;">
		<a href="javascript:void(0);"  id="transf_subbtn" class="easyui-linkbutton" style="width:325px;height:28px;">确定</a>
		</div>
   </c:if>
   </div>
   <div id="employeeinfo_entry" class="easyui-window"  collapsible="false"  maximizable="false" closed="true" modal="true"
					shadow="true" minimizable="true" title="求职者信息录入"
					style="width:800px;height:440px;">
					<jsp:include page="entryemployee.jsp" />
	</div>
	
	<div id="employeeinfo_edit" class="easyui-window" closed="true" collapsible="false"  maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="求职者信息修改"
					style="width:778px;height:450px;">
					<jsp:include page="updateemployee.jsp" />
	</div>
	<!-- 629添加备忘 -->
	<div id="add_memo" class="easyui-window" closed="true"  collapsible="false" resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="添加备忘"
					style="width:728px;height:480px;"> 
					<jsp:include page="addmemo.jsp" />
	</div>
	<div id="edit_memo" class="easyui-window" closed="true"  collapsible="false" resizable="false"   maximizable="false"  modal="true"
					shadow="true" minimizable="true" title="修改备忘"
					style="width:728px;height:480px;"> 
					<jsp:include page="editmemo.jsp" />
	</div>
</body>
</html>