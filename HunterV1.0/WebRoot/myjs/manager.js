$(function(){
	$("#manageUser").click(function(){
		 $("#tb1").addClass("hide");
		 $("#tb2").removeClass("hide");
		 $("#tb3").addClass("hide");
		 $("#tb4").addClass("hide");
		 $("#transf_tb").addClass("hide");
		 $("#tt").datagrid("clearSelections");
		 $("#tt").datagrid("clearSelections");
		setManagerGrid("员工信息管理","manager.do?method=showUserList","#tb");
	});
	$("#useradd").click(function(){
		 $("#useraddform")[0].reset();
		 $('.usertipspan').html("");
		$("#user_add").window("refresh");
		$("#user_add").window("open");	
	});
	$("#useredit").click(function(){
		 var rows = $('#tt').datagrid('getSelections');   
		    if(rows.length==0){
		    	$.messager.alert("提示","请选择一行！","error");
		    }
		    if(rows.length>1){
		    	$.messager.alert("提示","您选择了多条记录，一次只能修改一条！请取消。","error");
		    }
		    else{
		    	var username=rows[0].username;
		    	$.post("manager.do?method=getUserByname",{"username":username},function(data){
		    		var arr=data.split(",");
		    		$("#usereditform #username").val(arr[0]);
		    		$("#usereditform #password").val(arr[1]);
		    		$("#usereditform #confirmpassword").val(arr[1]);
		    		$("#usereditform #usertype").val(arr[2]);
		    		$('.usertipspan').html("");
	    			$("#user_edit").window("refresh");
	    			$("#user_edit").window("open");	
		    	});
		    }
	});
	
	   $("#userdelete").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   var usernames="";
			   var flag=1;
			   $.messager.confirm("请确认","您选择了多行数据，确认全部删除 ？",function(b){
				   if(b){
					   usernames=rows[0].username;
					   if(rows[0].usertype=="manager"){
						   flag=0;
					   }
					   for(var i=1;i<rows.length-1;i++){
						   usernames=usernames+","+rows[i].username;
						   if(rows[i].usertype=="manager"){
							   flag=0;
						   }
					   }
					   if(flag==1){
						   usernames=usernames+","+rows[rows.length-1].username;
						   $.post("manager.do?method=deleteUser",{"username":usernames},function(data){
							   $("#tt").datagrid("load");
						   });
					   }
					   else{
						   $.messager.alert("提示",'不能删除管理员！',"error");
					   }
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
		   else{
			   $.messager.confirm("请确认","确定删除？",function(b){
				   if(b){
					   var username=rows[0].username;
					   if(rows[0].usertype=="user"){
						   	$.post("manager.do?method=deleteUser",{"username":username},function(data){
						   	 $("#tt").datagrid("load"); 
						   });
					   }
					   else{
						   $.messager.alert("提示",'不能删除管理员！',"error");
					   }
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	   });
	$("#editpwd").click(function(){
		$("#pwdeditform")[0].reset();
		$(".pwd_tip").html("");
		$("#passwprd_edit").window("refresh");
		$("#passwprd_edit").window("open");	
	});
	//资源调度
	$("#transfSrc").click(function(){
		$("#tt").datagrid("clearSelections");
		$("#tb1").addClass("hide");
		$("#tb2").addClass("hide");
		$("#tb3").addClass("hide");
		$("#tb4").addClass("hide");
		$("#transf_tb").removeClass("hide");
		setTransfSrcGrid("资源调度","manager.do?method=getAllResource","#tb");
	});
	//调整
	$("#transf").click(function(){
		var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","一次请调整一行","error");
		  }
		   else{
			   $("#tranUserdatagrid").window("open");
			   setUser();
		   }
	});
	//提交调整
	$("#transf_subbtn").click(function(){
		var cid=$("#tt").datagrid("getSelected").cid;
		var usernameobj=$("#srctransf").datagrid("getSelected");
		if(usernameobj==null){
			$.messager.alert("提示","请选择目标","error");
		}
		else{
			username=usernameobj.username;
			$.post("manager.do?method=adjuastSrc",{"cid":cid,"username":username},function(data){
				$("#tranUserdatagrid").window("close");
				$("#tt").datagrid("clearSelections");
				$("#tt").datagrid("load"); 
			});
		}
		 $("#transf_subbtn").datagrid("clearSelections");
	});
});
function doUserSearch(){
	 $("#tt").datagrid("load",{  
	        userkey: $("#userKey").val()
	    }); 
}
function clearUserSearch(){
	$("#userKey").val("");
	doUserSearch();
}
function SearchSrc(){
	$("#tt").datagrid("load",{  
		tran_ser_comkey: $("#tran_ser_com").val(),
        tran_ser_address:$("#tran_ser_address").val()
    }); 
}
function clearSrcSearch(){
	$("#tran_ser_com").val("");
	$("#tran_ser_address").val("");
	SearchSrc();
}
function trandoUserSearch(){
	 $("#srctransf").datagrid("load",{  
	        key: $("#tran_userKey").val()
	    }); 
}
function tranclearUserSearch(){
	$("#tran_userKey").val("");
	trandoUserSearch();
}
function setManagerGrid(title,url,toolbar){
	$('#tt').datagrid({
	    title:title,
	    width:778,
	    height:500,
	    nowrap:false,
	    striped: true,
	    singleSelect:false,
	    url:url,
	    loadMsg:'数据加载中......',
	    sortName:'username',
	    sortOrder:'asc',
	    idField:'username',
	    frozenColumns:[[
	     {field:'ck',checkbox:true}
	    ]],
	    columns:[[
	     {title:'用户名',field:'username',width:'200',sortable:true,align:'center'},
	     {title:'密码',field:'password',width:'200',sortable:true,align:'center'},
	     {title:'用户类型',field:'usertype',width:'175',sortable:true,align:'center'},
	     {title:'用户状态',field:'zt',width:'125',sortable:false,align:'center'}
	    ]],
	    pagination:true,
	    rownumbers:true,
	    pageSize:20,
	    pageList:[10,20,50,100],
	    toolbar:toolbar
	   });
}
function setTransfSrcGrid(title,url,toolbar){
	$('#tt').datagrid({
	    title:title,
	    width:778,
	    height:500,
	    nowrap:false,
	    striped: true,
	    singleSelect:true,
	    url:url,
	    loadMsg:'数据加载中......',
	    sortName:'CZt',
	    sortOrder:'asc',
	    idField:'Cid',
	    frozenColumns:[[
	     {field:'ck',checkbox:true}
	    ]],
	    columns:[[
	     	     {title:'公司id',field:'cid',width:'125',hidden:true,align:'center'},
	    	     {title:'公司全称',field:'CName',width:'125',sortable:true,align:'center'},
	    	     {title:'公司地址',field:'CAddress',width:'145',sortable:true,align:'center'},
	    	     {title:'联系人',field:'CLinkman',width:'70',sortable:true,align:'center'},
	    	     {title:'联系方式',field:'CPhone',width:'105',sortable:false,align:'center'},
	    	     {title:'经办人',field:'entryuser',width:'80',sortable:false,align:'center'},
	    	     {title:'开发状态',field:'CZt',width:'70',sortable:true,align:'center'},
	    	     {title:'最后跟进时间',field:'lasttime',width:'103',sortable:false,align:'center'}
	    ]],
	    pagination:true,
	    rownumbers:true,
	    pageSize:20,
	    pageList:[10,20,50,100],
	    toolbar:toolbar
	   });
}
	function setUser(){
		$('#srctransf').datagrid({
		    width:330,
		    height:350,
		    nowrap:false,
		    striped: true,
		    singleSelect:true,
		    url:"manager.do?method=getUsers",
		    loadMsg:'数据加载中......',
		    sortName:'username',
		    sortOrder:'asc',
		    idField:'username',
		    frozenColumns:[[
       	     {field:'ck',checkbox:true}
       	    ]],
		    columns:[[
		     	     {title:'用户名',field:'username',width:'150',sortable:true,align:'center'},
		     	     {title:'用户类型',field:'usertype',width:'130',sortable:true,align:'center'}
		    ]],
		    toolbar:"#u_tb"
		});
}