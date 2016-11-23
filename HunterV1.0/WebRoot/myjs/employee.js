$(function(){
	$("#employeelib").click(function(){
		
		$("#tt").datagrid("clearSelections");
		setEmployeeGrid("人才库","employeeAction.do?method=showEmployeeList&zt=1","#tb");
		
		 //$("#employeeintents").removeClass("hide");//toolbar按钮显示控制
		 //$("#employeemarks").removeClass("hide");
		 //$("#employeeallocates").removeClass("hide");
		 //$("#checkresumes").removeClass("hide");
		 //$.messager.alert("提示","人才库！","error");
		 $("#tb1").addClass("hide");
		 $("#tb2").addClass("hide");
		 $("#tb3").removeClass("hide");
		 $("#tb4").addClass("hide");
		 $("#employeecrudbar").removeClass("hide");
		 $("#timeSearch").removeClass("hide");
		 $("#transf_tb").addClass("hide");
		 clearEmployeeSearch ();
	});
	
	$("#employee_allocate").click(function(){
		
		$("#tt").datagrid("clearSelections");
		setEmployeeGrid("成功推荐记录","employeeAction.do?method=showEmployeeList&zt=2","#tb");
		//clearEmployeeSearch ();
			
		 //$("#employeeintents").addClass("hide");//toolbar按钮显示控制
		 //$("#employeemarks").addClass("hide");
		 //$("#employeeallocates").addClass("hide");
		 //$("#checkresumes").removeClass("hide");
		 
		 $("#tb1").addClass("hide");
		 $("#tb2").addClass("hide");
		 $("#tb3").removeClass("hide");
		 $("#tb4").addClass("hide");
		 $("#employeecrudbar").removeClass("hide");
		 $("#timeSearch").removeClass("hide");
		 $("#transf_tb").addClass("hide");
		 clearEmployeeSearch ();


	});
	
	//意向人才 0619
	$("#employee_intent").click(function(){
		
		$("#tt").datagrid("clearSelections");
		setEmployeeGrid("意向人才库","employeeAction.do?method=showEmployeeList&zt=3","#tb");
		//clearEmployeeSearch ();
		
		 //$("#employeeintents").addClass("hide");//toolbar按钮显示控制
		 //$("#employeemarks").removeClass("hide");
		 //$("#employeeallocates").removeClass("hide");
		 //$("#checkresumes").removeClass("hide");
		 
		 $("#tb1").addClass("hide");
		 $("#tb2").addClass("hide");
		 $("#tb3").removeClass("hide");
		 $("#tb4").addClass("hide");
	   	 $("#employeecrudbar").removeClass("hide");
		 $("#timeSearch").removeClass("hide");
		 $("#transf_tb").addClass("hide");
		 clearEmployeeSearch ();
	});
	
	//已签人才0619
	$("#employee_mark").click(function(){
		
		$("#tt").datagrid("clearSelections");
		setEmployeeGrid("已签人才库","employeeAction.do?method=showEmployeeList&zt=4","#tb");
		//clearEmployeeSearch ();
		
		 //$("#employeeintents").addClass("hide");//toolbar按钮显示控制
		 //$("#employeemarks").addClass("hide");
		 //$("#employeeallocates").addClass("hide");
		 //$("#checkresumes").removeClass("hide");
		 
		 $("#tb1").addClass("hide");
		 $("#tb2").addClass("hide");
		 $("#tb3").removeClass("hide");
		 $("#tb4").addClass("hide");
		 $("#employeecrudbar").removeClass("hide");
		 $("#timeSearch").removeClass("hide");
		 $("#transf_tb").addClass("hide");
	});
	//其他所有人才employee_otherall
	$("#employee_otherall").click(function(){
		
		 $("#tt").datagrid("clearSelections");
		setEmployeeGrid("其他用户人才资源","employeeAction.do?method=showEmployeeList_allother","#tb");
		
		
		 //$("#employeeintents").addClass("hide");//toolbar按钮显示控制
		 //$("#employeemarks").addClass("hide");
		 //$("#employeeallocates").addClass("hide");
		 //$("#checkresumes").removeClass("hide");
		 
		 $("#tb1").addClass("hide");
		 $("#tb2").addClass("hide");
		 $("#tb3").removeClass("hide");
		 $("#tb4").addClass("hide");
		 $("#employeecrudbar").addClass("hide");
		 $("#timeSearch").removeClass("hide");
		 $("#transf_tb").addClass("hide");
		 clearEmployeeSearch ();
	});
	 $("#entryemployee_btn").click(function(){
		 $(".showresume").remove();
		 $("#openupresume").removeClass("hide");
		   $("#employeeform")[0].reset();
		   $('.employee_tipspan').html("");
		   $("#employeeinfo_entry").window("refresh");
	   	   $("#employeeinfo_entry").window("open");
	   });
	 
	
	$("#employeeadd").click(function(){
		 $("#employeeform")[0].reset();
		 $('.employee_tipspan').html("");
		$("#employeeinfo_entry").window("refresh");
		$("#employeeinfo_entry").window("open");	
	});
	
	//查看简历
	
	$("#checkresume").click(function(){
		var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","一次只能对查看一个求职者的简历","error");
			   $("#tt").datagrid("clearSelections");
		   }
		   else{
			  var EResume_name=rows[0].EResume_name;
			  var EResume_boolean=rows[0].EResume_boolean;
			  if(EResume_boolean=="否"){
				  $.messager.alert("提示","简历未上传，请上传之后查看","error");
				   $("#tt").datagrid("clearSelections");
			   }
			  if(EResume_name!="")
			  {
				  $.post("WordToHtmlServlet?type=resume",{"EResume_name":EResume_name},function(data){
					  if(data==0){
						  $.messager.alert("提示","获取简历失败","error");
					  }
					  else{
						
						  window.open("/HunterV1.0/resumehtmls/"+data);
					  }
				   $("#tt").datagrid("clearSelections");
			   });
			  }
		   }
	});
	
	//分配人才
	 $("#employeeallocate").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择至少一行！","error");
		   }
		   else if(rows.length>1){
			   var allocate_eids="";
			   $.messager.confirm("请确认","您选择了多行数据，确认全部推荐分配？",function(b){
				   if(b){
					   allocate_eids=rows[0].eid;
					   for(var i=1;i<rows.length-1;i++){
						   allocate_eids=allocate_eids+","+rows[i].eid;
					   }
					   allocate_eids=allocate_eids+","+rows[rows.length-1].eid;
					   $.post("employeeAction.do?method=allocateEmployee",{"eid":allocate_eids},function(data){
						   $("#tt").datagrid("load");
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
		   else{
			   $.messager.confirm("请确认","确定推荐分配？",function(b){
				   if(b){
					   var allocate_eid=rows[0].eid;
					   	$.post("employeeAction.do?method=allocateEmployee",{"eid":allocate_eid},function(data){
					   	 $("#tt").datagrid("load"); 
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	   });
	
	 
	 //意向人才
	 $("#employeeintent").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择至少一行！","error");
		   }
		   else if(rows.length>1){
			   var intent_eids="";
			   $.messager.confirm("请确认","您选择了多行数据，确认全部添加意向？",function(b){
				   if(b){
					   intent_eids=rows[0].eid;
					   for(var i=1;i<rows.length-1;i++){
						   intent_eids=intent_eids+","+rows[i].eid;
					   }
					   intent_eids=intent_eids+","+rows[rows.length-1].eid;
					   $.post("employeeAction.do?method=intentEmployee",{"eid":intent_eids},function(data){
						   $("#tt").datagrid("load");
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
		   else{
			   $.messager.confirm("请确认","确定添加意向？",function(b){
				   if(b){
					   var intent_eid=rows[0].eid;
					   	$.post("employeeAction.do?method=intentEmployee",{"eid":intent_eid},function(data){
					   	 $("#tt").datagrid("load"); 
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	   });
	 
	 //已签人才
	 $("#employeemark").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择至少一行！","error");
		   }
		   else if(rows.length>1){
			   var mark_eids="";
			   $.messager.confirm("请确认","您选择了多行数据，确认全部更新为已签？",function(b){
				   if(b){
					   mark_eids=rows[0].eid;
					   for(var i=1;i<rows.length-1;i++){
						   mark_eids=mark_eids+","+rows[i].eid;
					   }
					   mark_eids=mark_eids+","+rows[rows.length-1].eid;
					   $.post("employeeAction.do?method=markEmployee",{"eid":mark_eids},function(data){
						   $("#tt").datagrid("load");
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
		   else{
			   $.messager.confirm("请确认","确定更新为已签？",function(b){
				   if(b){
					   var mark_eid=rows[0].eid;
					   	$.post("employeeAction.do?method=markEmployee",{"eid":mark_eid},function(data){
					   	 $("#tt").datagrid("load"); 
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	   });
	 
	 
	$("#employeeedit").click(function(){
		 var rows = $('#tt').datagrid('getSelections');   
		    if(rows.length==0){
		    	$.messager.alert("提示","请选择一行！","error");
		    }
		    if(rows.length>1){
		    	$.messager.alert("提示","您选择了多条记录，一次只能修改一条！请取消。","error");
		    }
		    else{
		    	var edit_eid=rows[0].eid;
		    	$.post("employeeAction.do?method=editEmployee",{"eid":edit_eid},function(data){
		    		$("#employeeform_edit")[0].reset();
		    		$(".showresume").remove();
		   		 	$("#openupresume_edit_1").removeClass("hide");
		   		    $("#e_resume_show_tip_edit").removeClass("hide");
		   		    check_show=false;
		    		var arr=data.split(",");
		    		$("#employeeform_edit #employee_name_edit").val(arr[0]);
		    		$("#employeeform_edit #employee_sex_edit").val(arr[1]);
		    		$("#employeeform_edit #employee_phone_edit").val(arr[2]);
		    		$("#employeeform_edit #employee_address_edit").val(arr[3]);
		    		$("#employeeform_edit #employee_email_edit").val(arr[4]);
		    		$("#employeeform_edit #cc_edit").combobox('setValue',arr[5] );
		    		$("#employeeform_edit #employee_cerstate_edit").val(arr[6]);
		    		$("#employeeform_edit #employee_eid_edit").val(arr[7]);
		    		if(arr[8]!="")
		    		{
		    			$("#employeeform_edit #e_resume_show_tip_edit").html("<font color='red'>简历已经上传</font>");
		    		}
		    		else
		    			$("#employeeform_edit #e_resume_show_tip_edit").html("<font color='red'>简历未上传</font>");
		    		$('.employee_tipspan').html("");
		    		$("#employeeinfo_edit").window("refresh");
		    		$("#employeeinfo_edit").window("open");	
		    	});
		    }
	});
	
	 $("#employeedelete").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   var del_eids="";
			   $.messager.confirm("请确认","您选择了多行数据，确认全部删除 ？",function(b){
				   if(b){
					   del_eids=rows[0].eid;
					   for(var i=1;i<rows.length-1;i++){
						   del_eids=del_eids+","+rows[i].eid;
					   }
					   del_eids=del_eids+","+rows[rows.length-1].eid;
					   $.post("employeeAction.do?method=deleteEmployee",{"eid":del_eids},function(data){
						   $("#tt").datagrid("load");
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
		   else{
			   $.messager.confirm("请确认","确定删除？",function(b){
				   if(b){
					   var del_eid=rows[0].eid;
					   	$.post("employeeAction.do?method=deleteEmployee",{"eid":del_eid},function(data){
					   	 $("#tt").datagrid("load"); 
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	   });
	 
	$("#editpwd").click(function(){
		$("#passwprd_edit").window("refresh");
		$("#passwprd_edit").window("open");	
	});
	
	//添加跟进记录
	$("#employeeaddrecord").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","一次只能对一个求职者添加跟进记录","error");
			   $("#tt").datagrid("clearSelections");
		   }
		   else{
			   var eid=rows[0].eid;
			   $("#employee_record_add_form")[0].reset();
			   $("#employee_add_record").window("open");
			   $("#r_employeeid").val(eid);
		   }
	});
	
	//查看跟进记录
	   $("#employeecheckrecord").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","一次只能查看一行记录","error");
			   $("#tt").datagrid("clearSelections");
		   }
		   else{
			   var eid=rows[0].eid;
			   $("#employeecheckrecordwin").window("open");	
			   setemployeerecorddatagrid("查看记录","employeeAction.do?method=getRecord&eid="+eid);
		   }
	   });
	
	
});

function setemployeerecorddatagrid(title,url){
	$('#employeerecords').datagrid({
	    width:816,
	    height:503,
	    nowrap:false,
	    striped: true,
	    singleSelect:true,
	    url:url,
	    loadMsg:'数据加载中......',
	    sortName:'RTime',
	    sortOrder:'desc',
	    idField:'eid',
	    columns:[[
     	     {title:'记录id',field:'eid',width:'125',hidden:true,align:'center'},
    	     {title:'求职者姓名',field:'EName',width:'125',sortable:true,align:'center'},
    	     {title:'经办人',field:'entryuser',width:'100',sortable:false,align:'center'},
    	     {title:'主题',field:'RTheme',width:'125',sortable:false,align:'center'},
    	     {title:'时间',field:'RTime',width:'124',sortable:true,align:'center'},
    	     {title:'摘要',field:'RAbstract',width:'290',sortable:false,align:'left'}
    	     
	    ]],
	    pagination:true,
	    rownumbers:true,
	    pageSize:20,
	    pageList:[10,20,50,100]
	   });
}

function doEmployeeSearch(){
	
	
    var key_ename="";
    var key_eaddress="";
    var key_etype="";
    
    key_ename=$("#key_ename").val();
    key_eaddress=$("#key_eaddress").val();
    key_etype=$("#key_etype").val();
    //System.out.println(""+key_ename+","+key_eaddress+","+key_etype);
    //alert("key_ename="+key_ename+",key_eaddress="+key_eaddress+",key_etype="+key_etype);

	 $("#tt").datagrid("load",{  
	        key_ename:key_ename,
	        key_etype:key_etype,
	        key_eaddress:key_eaddress
	    }); 
}

function clearEmployeeSearch (){
	$("#key_ename").val("");
	$("#key_eaddress").val("");
	$("#key_etype").val("");
	doEmployeeSearch();
}

function setEmployeeGrid(title,url,toolbar){
	 $('#tt').datagrid({
		    title:title,
		    width:778,
		    height:500,
		    singleSelect:false,
		    nowrap:false,
		    striped: true,
		    url:url,
		    loadMsg:'数据加载中......',
		    sortName:'EEntrydate',
		    sortOrder:'desc',
		    idField:'eid',
		    frozenColumns:[[
		     {field:'ck',checkbox:true}
		    ]],
		    columns:[[
		     {title:'eid',field:'eid',width:'100',hidden:true,rowspan:2,align:'center'},
		     {title:'姓名',field:'EName',width:'70',sortable:true,rowspan:2,align:'center'},
		     {title:'性别',field:'ESex',width:'50',sortable:true,rowspan:2,align:'center'},
		     {title:'地区',field:'EAddress',width:'100',sortable:true,rowspan:2,align:'center'},
		     {title:'联系方式',field:'EPhone',width:'90',sortable:true,rowspan:2,align:'center'},
		     {title:'证书类型',field:'ECertype',width:'150',sortable:true,rowspan:2,align:'center'},
		     {title:'证书状态',field:'ECerstate',width:'70',sortable:true,rowspan:2,align:'center'},
		     {title:'经办人',field:'EEntryuser',width:'70',sortable:true,rowspan:2,align:'center'},
		     //{title:'简历上传',field:'EResume_boolean',width:'75',sortable:false,rowspan:2,align:'center'},
		     {title:'录入时间',field:'EEntrydate',width:'93',sortable:true,rowspan:2,align:'center'}
		    ]],
		    pagination:true,
		    rownumbers:true,
		    pageSize:20,
		    pageList:[10,20,50,100],
		    toolbar: toolbar
		   });
			  
}