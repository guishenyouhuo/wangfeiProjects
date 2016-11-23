//2014.06.06 企业邮箱限制取消
function checkInput(name,linkman,phone,address) {
	var check=false;
	//var emailReg=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var phoneReg=/^[0-9]{7,20}$/;
	//var qqReg=/^[1-9]\d{5,8}$/;
	 var companyname=$.trim($(name).val());
	 var companylinkman=$.trim($(linkman).val());
	 var companyphone=$.trim($(phone).val());
	 var company_address=$.trim($(address).val());
	 //var company_email=$.trim($(email).val());
	 if(companyname==""){
			$(name).parent().children().last().html("<font color='red'>公司名不能为空</font>");
		}
		else $(name).parent().children().last().html("");
		if(companylinkman==""){
			$(linkman).parent().children().last().html("<font color='red'>联系人不能为空</font>");
		}
		else $(linkman).parent().children().last().html("");
		if(companyphone==""){
			$(phone).parent().children().last().html("<font color='red'>联系方式不能为空</font>");
		}
		else if(!phoneReg.exec(companyphone)){
			$(phone).parent().children().last().html("<font color='red'>联系方式格式错误</font>");
		}
		else $(phone).parent().children().last().html("");
		if(company_address==""){
			$(address).parent().children().last().html("<font color='red'>地址不能为空</font>");
		}
		else $(address).parent().children().last().html("");
		/*
		if(company_email==""){
			$(email).parent().children().last().html("<font color='red'>QQ或邮箱不能为空</font>");
		}else if(!emailReg.exec(company_email)&&!qqReg.exec(company_email)){
			$(email).parent().children().last().html("<font color='red'>QQ或邮箱格式错误</font>");
		} 
		else $(email).parent().children().last().html("");  
		*/
		if(companyname!=""&&companylinkman!=""&&companyphone!=""&&phoneReg.exec(companyphone)&&
				company_address!=""){
			check=true;
		}
		return check;
}