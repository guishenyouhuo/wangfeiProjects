$(function(){
	$("#todayMemo").click(function(){
		   $("#tb1").addClass("hide");
		   $("#tb2").addClass("hide");
		   $("#tb3").addClass("hide");
		   $("#transf_tb").addClass("hide");
		   $("#tb4").removeClass("hide");
		   memoRecord ("当日备忘","memoAction.do?method=getMemo&type=today");    
	   });
	$("#otherMemos").click(function(){
		   $("#tb1").addClass("hide");
		   $("#tb2").addClass("hide");
		   $("#tb3").addClass("hide");
		   $("#transf_tb").addClass("hide");
		   $("#tb4").removeClass("hide");
		   memoRecord ("其他备忘","memoAction.do?method=getMemo&type=others");    
	});
	//添加
	$("#memoadd").click(function(){
		$("#memo_add_form")[0].reset();
		$("#add_memo").window("open");
	});
	//修改
	$("#memoedit").click(function(){
		var rows = $('#tt').datagrid('getSelections');   
	    if(rows.length==0){
	    	 $.messager.alert("提示","请选择一行！","error");
	    }
	    if(rows.length>1){
	    	$.messager.alert("提示","您选择了多条记录，一次只能修改一条！请取消。","error");
	    }
	    else{
			$("#memo_edit_form")[0].reset();
			$("#memid").val($('#tt').datagrid('getSelections')[0].meId);
			$("#memoedit_theme").val(rows[0].memoTheme);
			$("#memoedit_time").datetimebox("setValue",rows[0].memoTime);
			$("#memoedit_abstract").val(rows[0].memoAbstract);
			$("#edit_memo").window("open");
	    }
	});
	//删除
	$("#memodelete").click(function(){
		var rows = $('#tt').datagrid('getSelections');  
		   if(rows.length==0){
			   $.messager.alert("提示","请选择一行！","error");
		   }
		   else if(rows.length>1){
			   var del_meIds="";
				$.messager.confirm("请确认","您选择了多行数据，确认全部删除 ？",function(b){
					if(b){
						del_meIds=rows[0].meId;
						   for(var i=1;i<rows.length-1;i++){
							   del_meIds=del_meIds+","+rows[i].meId;
						   }
						   del_meIds=del_meIds+","+rows[rows.length-1].meId;
						   $.post("memoAction.do?method=deleteMemo",{"meId":del_meIds},function(data){
							   $("#tt").datagrid("load");
						   });
					}
					$("#tt").datagrid("clearSelections");
				});   
		   }
		   else{
				$.messager.confirm("请确认","确定删除？",function(b){
					if(b){
						var del_meId=rows[0].meId;
					   	$.post("memoAction.do?method=deleteMemo",{"meId":del_meId},function(data){
					   	 $("#tt").datagrid("load"); 
					   });
					}
					$("#tt").datagrid("clearSelections");
				});   
		   }
	});
});

function memoRecord(title,url){
	$('#tt').datagrid({
	    title:title,
	    width:778,
	    height:500,
	    nowrap:false,
	    striped: true,
	    singleSelect:false,
	    url:url,
	    loadMsg:'数据加载中......',
	    sortName:'memoTime',
	    sortOrder:'desc',
	    idField:'meId',
	    frozenColumns:[[
	     {field:'ck',checkbox:true}
	    ]],
	    columns:[[
	     {title:'备忘id',field:'meId',width:'60',hidden:true,align:'center'},
	     {title:'备忘人',field:'username',width:'60',sortable:false,align:'center'},
	     {title:'待办事件',field:'memoTheme',width:'120',sortable:true,align:'center'},
	     {title:'事件摘要',field:'memoAbstract',width:'371',sortable:true,align:'center'},
	     {title:'待办时间',field:'memoTime',width:'150',sortable:true,align:'center'}
	    ]],
	    pagination:true,
	    rownumbers:true,
	    pageSize:20,
	    pageList:[10,20,50,100],
	    toolbar:"#tb"  
	   });
}