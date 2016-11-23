$(function(){
	   $("#logout").click(function(){
		   $.messager.confirm("请确认",'警告：您确认要退出吗？',function(b){
		   		if(b){
		   			window.location.href="logout.do";
		   		}
	    });
				
	   });
	   $("#entrycompany_btn,#add").click(function(){
		   $("#companyform")[0].reset();
		   $('.tipspan').html("");
		  $("#companyinfo_edit").window("refresh");
	   	$("#companyinfo_entry").window("open");
	   });
	   $("#commenlib").click(function(){
		   $("#tt").datagrid("clearSelections");
		   setdatagrid("共享库", 'companyAction.do?method=showCompanyList&zt=6&type=commen',"#tb");
		   clearSearch ();
		   $("#crudbar").addClass("hide");
		   $("#tb1").removeClass("hide");  
		   $("#tb2").addClass("hide");
		   $("#intoMines").removeClass("hide");
		   $("#transf_tb").addClass("hide");
		   $("#tb3").addClass("hide");
		   $("#tb4").addClass("hide");
	});
	   $("#followcompany").click(function(){
		   $("#tt").datagrid("clearSelections");
		   setdatagrid ("线索库",'companyAction.do?method=showCompanyList&zt=1',"#tb");    
		   clearSearch ();
		   $("#crudbar").removeClass("hide");
		   $("#tb1").removeClass("hide");  
		   $("#tb2").addClass("hide");
		   $("#tb3").addClass("hide");
		   $("#tb4").addClass("hide");
		   $("#transf_tb").addClass("hide");
		   $("#followclients").removeClass("hide");
		   $("#intents").addClass("hide");
		   $("#newproject").addClass("hide");
		   $("#writebills").addClass("hide");
		   $("#upbills").addClass("hide");
		   $("#addrecords").removeClass("hide");
		   $("#checkrecords").removeClass("hide");
		   $("#intoMines").addClass("hide");
		   $("#checkcontracts").addClass("hide");
		   $("#checkbills").addClass("hide");
		   $("#giveups").removeClass("hide");
		   
		   
	   });
	   
	   $("#edit").click(function(){
		    var rows = $('#tt').datagrid('getSelections');   
		    if(rows.length==0){
		    	 $.messager.alert("提示","请选择一行！","error");
		    }
		    if(rows.length>1){
		    	$.messager.alert("提示","您选择了多条记录，一次只能修改一条！请取消。","error");
		    }
		    else{
		    	var cid=rows[0].cid;
		    	$.post("companyAction.do?method=getCompany",{"cid":cid},function(data){
		    		var arr=data.split(",");
		    		$("#company_name_edit").val(arr[0]);
		    		$("#company_address_edit").val(arr[1]);
		    		$("#company_linkman_edit").val(arr[2]);
		    		$("#company_sex_edit").val(arr[3]);
		    		$("#company_title_edit").val(arr[4]);
		    		$("#company_email_edit").val(arr[5]);
		    		$("#company_phone_edit").val(arr[6]);
		    		$("#companyform_edit #company_cid").val(arr[7]);
		    		
		    		$("#company_telephone_edit").val(arr[8]);
		    		$("#company_secondlinkman_edit").val(arr[9]);
		    		$("#company_secondphone_edit").val(arr[10]);
		    		$("#company_need_edit").val(arr[11]);
		    		
		    		
		    		//$('.name_tip_edit').html("");
		    		$('#name_tip_edit').html("");
		    		$("#companyinfo_edit").window("refresh");
		    		$("#companyinfo_edit").window("open");	
		    	});
		    }
	   });
	   $("#delete").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   var del_cids="";
				$.messager.confirm("请确认","您选择了多行数据，确认全部删除 ？",function(b){
					if(b){
						del_cids=rows[0].cid;
						   for(var i=1;i<rows.length-1;i++){
							   del_cids=del_cids+","+rows[i].cid;
						   }
						   del_cids=del_cids+","+rows[rows.length-1].cid;
						   $.post("companyAction.do?method=deleteCompany",{"cid":del_cids},function(data){
							   $("#tt").datagrid("load");
						   });
					}
					$("#tt").datagrid("clearSelections");
				});   
		   }
		   else{
				$.messager.confirm("请确认","确定删除？",function(b){
					if(b){
						var del_cid=rows[0].cid;
					   	$.post("companyAction.do?method=deleteCompany",{"cid":del_cid},function(data){
					   	 $("#tt").datagrid("load"); 
					   });
					}
					$("#tt").datagrid("clearSelections");
				});   
		   }
	   });
	   
	   
	  //跟踪客户
	   $("#companyfollow").click(function(){
		   $("#tt").datagrid("clearSelections");
		   setdatagrid("客户跟踪","companyAction.do?method=showCompanyList&zt=2","#tb");
		   clearSearch ();
		   $("#followclients").addClass("hide");
		   $("#intents").removeClass("hide");
		   $("#writebills").removeClass("hide");
		   $("#upbills").addClass("hide");
		   $("#addrecords").removeClass("hide");
		   $("#checkrecords").removeClass("hide");
		   $("#intoMines").addClass("hide");
		   $("#newproject").removeClass("hide");
		   $("#checkcontracts").addClass("hide");
		   $("#checkbills").addClass("hide");
		   $("#giveups").removeClass("hide");
		   $("#crudbar").removeClass("hide");
		   $("#tb1").removeClass("hide");  
		   $("#tb2").addClass("hide");
		   $("#tb3").addClass("hide");
		   $("#tb4").addClass("hide");
		   $("#transf_tb").addClass("hide");
	   });
	   //意向客户
	   $("#intentClient").click(function(){
		   $("#tt").datagrid("clearSelections");
		   setdatagrid("意向客户","companyAction.do?method=showCompanyList&zt=3","#tb");
		   clearSearch ();
		   $("#followclients").removeClass("hide");
		   $("#intents").addClass("hide");
		   $("#writebills").removeClass("hide");
		   $("#upbills").addClass("hide");
		   $("#addrecords").removeClass("hide");
		   $("#checkrecords").removeClass("hide");
		   $("#intoMines").addClass("hide");
		   $("#newproject").removeClass("hide");
		   $("#checkcontracts").addClass("hide");
		   $("#checkbills").addClass("hide");
		   $("#giveups").removeClass("hide");
		   $("#crudbar").removeClass("hide");
		   $("#tb1").removeClass("hide");  
		   $("#tb2").addClass("hide");
		   $("#tb3").addClass("hide");
		   $("#tb4").addClass("hide");
		   $("#transf_tb").addClass("hide");
	   });
	   //签单未到账
	   $("#writenobill").click(function(){
		   $("#tt").datagrid("clearSelections");
		   writenobilldatagrid("签单未到账","companyAction.do?method=showWriteNoBillList&zt=4","#tb");
		   clearSearch ();
		   $("#followclients").addClass("hide");
		   $("#intents").addClass("hide");
		   $("#writebills").addClass("hide");
		   $("#upbills").removeClass("hide");
		   $("#addrecords").removeClass("hide");
		   $("#checkrecords").removeClass("hide");
		   $("#intoMines").addClass("hide");
		   $("#newproject").removeClass("hide");
		   $("#checkcontracts").removeClass("hide");
		   $("#checkbills").removeClass("hide");
		   $("#crudbar").removeClass("hide");
		   $("#tb1").removeClass("hide");  
		   $("#tb2").addClass("hide");
		   $("#tb3").addClass("hide");
		   $("#tb4").addClass("hide");
		   $("#transf_tb").addClass("hide");
	   });
	   //订单跟踪
	   $("#billfollow").click(function(){
		   $("#tt").datagrid("clearSelections");
	   	   setfollowdatagrid("订单跟踪","followAction.do?method=billFollow&zt=5","#tb");
	   	   clearSearch ();
		   $("#followclients").addClass("hide");
		   $("#intents").addClass("hide");
		   $("#writebills").addClass("hide");
		   $("#upbills").addClass("hide");
		   $("#addrecords").removeClass("hide");
		   $("#checkrecords").removeClass("hide");
		   $("#intoMines").addClass("hide");
		   $("#newproject").removeClass("hide");
		   $("#checkcontracts").removeClass("hide");
		   $("#checkbills").removeClass("hide");
		   $("#crudbar").removeClass("hide");
		   $("#tb1").removeClass("hide");  
		   $("#tb2").addClass("hide");
		   $("#tb3").addClass("hide");
		   $("#tb4").addClass("hide");
		   $("#transf_tb").addClass("hide");
	   });
});
function doSearch(){
    
	 $("#tt").datagrid("load",{  
	        key: $("#key").val(),
	        address_search:$("#address_search").val()
	    }); 
}
function clearSearch (){
	
	$("#address_search").val("");
	$("#key").val("");
	doSearch();
}
function setdatagrid (title,url,toolbar){
	$('#tt').datagrid({
	    title:title,
	    width:778,
	    height:500,
	    nowrap:false,
	    striped: true,
	    singleSelect:false,
	    url:url,
	    loadMsg:'数据加载中......',
	    sortName:'CEntrytime',
	    //sortName:'lasttime',
	    sortOrder:'desc',
	    idField:'cid',
	    frozenColumns:[[
	     {field:'ck',checkbox:true}
	    ]],
	    columns:[[
	     {title:'公司id',field:'cid',width:'100',hidden:true,align:'center'},
	     {title:'公司全称',field:'CName',width:'120',sortable:true,align:'center'},
	     {title:'公司地址',field:'CAddress',width:'100',sortable:true,align:'center'},
	     {title:'公司座机',field:'CTelephone',width:'80',sortable:true,align:'center'},
	     {title:'主要联系人',field:'CLinkman',width:'70',sortable:true,align:'center'},
	     {title:'主要联系方式',field:'CPhone',width:'80',sortable:true,align:'center'},
	     {title:'最后跟进时间',field:'lasttime',width:'90',sortable:false,align:'center'},
	     {title:'经办人',field:'entryuser',width:'70',sortable:false,align:'center'},
	     {title:'录入时间',field:'CEntrytime',width:'90',sortable:true,align:'center'}
	    ]],
	    pagination:true,
	    rownumbers:true,
	    pageSize:20,
	    pageList:[10,20,50,100],
	    toolbar:toolbar
	   });
}
function writenobilldatagrid (title,url,toolbar){
	$('#tt').datagrid({
	    title:title,
	    width:778,
	    height:500,
	    nowrap:false,
	    striped: true,
	    singleSelect:false,
	    url:url,
	    loadMsg:'数据加载中......',
	    sortName:'CName',
	    sortOrder:'asc',
	    idField:'cid',
	    frozenColumns:[[
	     {field:'ck',checkbox:true}
	    ]],
	    columns:[[
	     {title:'合同id',field:'conid',width:'125',hidden:true,align:'center'},
	     {title:'合同名',field:'connewname',width:'125',hidden:true,align:'center'},
	     {title:'公司id',field:'cid',width:'125',hidden:true,align:'center'},
	     {title:'欠款',field:'shouldPay',width:'125',hidden:true,align:'center'},
	     {title:'公司全称',field:'CName',width:'125',sortable:true,align:'center'},
	     {title:'公司地址',field:'CAddress',width:'135',sortable:true,align:'center'},
	     {title:'应付款',field:'contractmoney',width:'80',sortable:false,align:'center'},
	     {title:'实付款',field:'paidmoney',width:'80',sortable:false,align:'center'},
	     {title:'合同上传时间',field:'concuptime',width:'95',sortable:false,align:'center'},
	     {title:'经办人',field:'entryuser',width:'80',sortable:false,align:'center'},
	     {title:'最近清单上传时间',field:'lastbilltime',width:'103',sortable:false,align:'center'}
	    ]],
	    pagination:true,
	    rownumbers:true,
	    pageSize:20,
	    pageList:[10,20,50,100],
	    toolbar:toolbar
	   });
}
function setfollowdatagrid (title,url,toolbar){
	$('#tt').datagrid({
	    title:title,
	    width:778,
	    height:500,
	    nowrap:false,
	    striped: true,
	    singleSelect:false,
	    url:url,
	    loadMsg:'数据加载中......',
	    sortName:'CName',
	    sortOrder:'asc',
	    idField:'cid',
	    frozenColumns:[[
	     {field:'ck',checkbox:true}
	    ]],
	    columns:[[
	         {title:'合同id',field:'conid',width:'125',hidden:true,align:'center'},
     	     {title:'公司id',field:'cid',width:'125',hidden:true,align:'center'},
     	     {title:'合同名',field:'connewname',width:'125',hidden:true,align:'center'},
    	     {title:'公司全称',field:'CName',width:'125',sortable:true,align:'center'},
    	     {title:'公司地址',field:'CAddress',width:'135',sortable:true,align:'center'},
    	     {title:'联系人',field:'CLinkman',width:'60',sortable:true,align:'center'},
    	     {title:'项目金额',field:'promoney',width:'100',sortable:false,align:'center'},
    	     {title:'经办人',field:'entryuser',width:'80',sortable:false,align:'center'},
    	     {title:'合同上传时间',field:'conuptime',width:'100',sortable:false,align:'center'},
    	     {title:'签单到账时间',field:'lastbilltime',width:'97',sortable:false,align:'center'}
	    ]],
	    pagination:true,
	    rownumbers:true,
	    pageSize:20,
	    pageList:[10,20,50,100],
	    toolbar:toolbar
	   });
}