$(function(){
	//客户跟踪
	   $("#followclient").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   var cids="";
			   $.messager.confirm("请确认","您选择了多行数据，确认全部跟踪 ？",function(b){
				   if(b){
					   cids=rows[0].cid;
					   for(var i=1;i<rows.length-1;i++){
						   cids=cids+","+rows[i].cid;
					   }
					   cids=cids+","+rows[rows.length-1].cid;
					   $.post("followAction.do?method=getCount",{"zt":2},function(data){
						   if((parseInt(parseInt(data)+parseInt(rows.length)))<=300){
							   $.post("followAction.do?method=followClient",{"cid":cids},function(data){
								   $("#tt").datagrid("load");
							   });
						   }
						   else{
							   $.messager.alert("提示","将会超出"+(parseInt(parseInt(data)+parseInt(rows.length))-300)+"条记录，请重新选择","error");
						   }
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
		   else{
			   var cid=rows[0].cid;
			   $.post("followAction.do?method=getCount",{"zt":2},function(data){
				   if(data<300){
					   $.post("followAction.do?method=followClient",{"cid":cid},function(data){
						   	 $("#tt").datagrid("load"); 
						   });
				   }
				   else{
					   $.messager.alert("提示","最多只能放300条记录，已满","error");
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	   });
	//意向客户
	   $("#intent").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   var cids="";
			   $.messager.confirm("请确认","您选择了多行数据，确认全部更新为意向客户 ？",function(b){
				   if(b){
					   cids=rows[0].cid;
					   for(var i=1;i<rows.length-1;i++){
						   cids=cids+","+rows[i].cid;
					   }
					   cids=cids+","+rows[rows.length-1].cid;
					   $.post("followAction.do?method=getCount",{"zt":3},function(data){
						   if(parseInt(parseInt(data)+parseInt(rows.length))<=100){
							   $.post("followAction.do?method=intentClient",{"cid":cids},function(data){
								   $("#tt").datagrid("load");
							   });
						   }
						   else{
							   $.messager.alert("提示","将会超出"+(parseInt(parseInt(data)+parseInt(rows.length))-100)+"条记录，请重新选择","error");
						   }   
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
		   else{
			   var cid=rows[0].cid;
			   $.post("followAction.do?method=getCount",{"zt":3},function(data){
				   if(data<100){
					   	$.post("followAction.do?method=intentClient",{"cid":cid},function(data){
					   	 $("#tt").datagrid("load"); 
					   });
				   }
				   else{
					   $.messager.alert("提示","最多只能放100条记录，已满","error");
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	   });	
	   
	 //放弃客户
	   $("#giveup").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1)
		   {
			   var cids="";
			   $.messager.confirm("请确认","您选择了多行数据，确认放弃全部客户？",function(b){
				   if(b)
				   {
					   cids=rows[0].cid;
					   for(var i=1;i<rows.length-1;i++){
						   cids=cids+","+rows[i].cid;
					   }
					   cids=cids+","+rows[rows.length-1].cid;
							   $.post("followAction.do?method=giveupClient",{"cid":cids},function(data){
								   $("#tt").datagrid("load");
							   });
  
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
		   else
		   {
			   var cid=rows[0].cid;
			   $.post("followAction.do?method=giveupClient",{"cid":cid},function(data)
			   {
					 $("#tt").datagrid("load"); 
			    }
			   );
				   
				   $("#tt").datagrid("clearSelections");
			}
		   
	   });	   
	   
	  
	   //签单客户
	   $("#writebill").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","签单只能单条操作，请重新选择","error");
			   $("#tt").datagrid("clearSelections");
		   }
		   else{   
			   $("#contractform")[0].reset();
			   $(".contract_tipspan").html("");
			   $("#contract_upload").window("open");	
			   $("#contract_upload #belongcid").val((rows[0].cid));
			   $("#contract_upload #belongcompany").val(rows[0].CName); 
		   }   
	   });
	   //上传清单
	   $("#upbill").click(function(){
			 var rows = $('#tt').datagrid('getSelections');  
			   if(rows.length==0){
				   $.messager.alert("提示","请选择一行！","error");
			   }
			   else if(rows.length>1){
				   $.messager.alert("提示","只能单行操作！","error");
				   $("#tt").datagrid("clearSelections");
			   }
			   else{
				   $("#billupform")[0].reset();
				   $('.bill_tipspan').html("");
				   $("#billupload").window("open");
				   $("#billupload #belongcontract").val(rows[0].conid);
				   $("#billupload #belongcontract1").val(rows[0].conname);
				   $("#billupload #shouldpay").val(rows[0].shouldPay);
				   //0619
				   $("#paynow").numberbox({
					    min:0,
					    max:parseInt($("#shouldpay").val()),
					    precision:2
					    });
				   
			   }
	   });
	   //查看跟进记录
	   $("#checkrecord").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","一次只能查看一行记录","error");
			   $("#tt").datagrid("clearSelections");
		   }
		   else{
			   var cid=rows[0].cid;
			   $("#checkrecordwin").window("open");	
			   setrecorddatagrid("查看记录","followAction.do?method=getRecord&cid="+cid);
		   }
	   });
	   //添加跟进记录
	   $("#addrecord").click(function(){
		   var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","一次只能对一个公司添加跟进记录","error");
			   $("#tt").datagrid("clearSelections");
		   }
		   else{
			   var cid=rows[0].cid;
			   $("#record_add_form")[0].reset();
			   $("#add_record").window("open");
			   $("#r_companyid").val(cid);
		   }
	   });
	   //添加到我的线索库
	$("#intoMine").click(function(){
		 var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   var cids="";
			   $.messager.confirm("请确认","您选择了多行数据，确认全部加入线索 ？",function(b){
				   if(b){
					   cids=rows[0].cid;
					   for(var i=1;i<rows.length-1;i++){
						   cids=cids+","+rows[i].cid;
					   }
					   cids=cids+","+rows[rows.length-1].cid;
					   $.post("followAction.do?method=intoMineClue",{"cid":cids},function(data){
						   $("#tt").datagrid("load");
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
		   else{
			   var cid=rows[0].cid;
			   	$.post("followAction.do?method=intoMineClue",{"cid":cid},function(data){
			   	 $("#tt").datagrid("load"); 
			   });
		   }
	});
	//新建项目
	$("#new_project").click(function(){
		var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","一次只能对一个公司添加跟进记录","error");
			   $("#tt").datagrid("clearSelections");
		   }
		   else{
			   var cid=rows[0].cid;
			   $.messager.confirm("请确认","将会在线索库中添加一条该公司的记录",function(b){
				   if(b){
					   $.post("followAction.do?method=addNewProject",{"cid":cid},function(data){
						   $("#tt").datagrid("clearSelections");
					   });
				   }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	});
	//查看合同
	$("#checkcontract").click(function(){
		var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","一次只能对查看一个公司的合同","error");
			   $("#tt").datagrid("clearSelections");
		   }
		   else{
			  var conname=rows[0].connewname;
			  $.post("wordToHtmlAction.do?method=contract",{"conname":conname},function(data){
				  alert(data);
				  if(data==0){
					  $.messager.alert("提示","获取合同失败","error");
				  }
				  else{
					  window.open("/HunterV1.0/contracthtmls/"+data);
				  }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	});
	//查看清单
	$("#checkbill").click(function(){
		var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   $.messager.alert("提示","一次只能对查看一个公司的清单","error");
			   $("#tt").datagrid("clearSelections");
		   }
		   else{
			  var conid=rows[0].conid;
			  $.post("followAction.do?method=getBillLists",{"conid":conid},function(data){
				  if((data==0)||(data=="")||(data==null)){
					  $.messager.alert("提示","无清单","error");
				  }
				  else{
					  $("#billshtml").window("open");
					  $("#bills").html(data);
				  }
				   $("#tt").datagrid("clearSelections");
			   });
		   }
	});
});
function setrecorddatagrid(title,url){
	$('#records').datagrid({
	    width:816,
	    height:503,
	    nowrap:false,
	    striped: true,
	    singleSelect:true,
	    url:url,
	    loadMsg:'数据加载中......',
	    sortName:'RTime',
	    sortOrder:'desc',
	    idField:'cid',
	    columns:[[
     	     {title:'记录id',field:'rid',width:'125',hidden:true,align:'center'},
    	     {title:'公司全称',field:'CName',width:'125',sortable:true,align:'center'},
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