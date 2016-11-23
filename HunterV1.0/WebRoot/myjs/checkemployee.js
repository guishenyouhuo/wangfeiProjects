function checkEmployeeInput(e_name,e_phone,e_email,e_address) {
	var check=false;
	var emailReg=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var qqReg=/^[1-9]\d{5,8}$/;
	var phoneReg=/^[0-9]{7,20}$/;
	var employee_name=$.trim($(e_name).val());
	var employee_phone=$.trim($(e_phone).val());
	var employee_email=$.trim($(e_email).val());
	var employee_address=$.trim($(e_address).val());
	
	if(employee_name=="")
	{
			$(e_name).parent().children().last().html("<font color='red'>求职者姓名不能为空</font>");
	}
	else 
		$(e_name).parent().children().last().html("");
	

	if(employee_phone=="")
	{
			$(e_phone).parent().children().last().html("<font color='red'>联系方式不能为空</font>");
	}
	else 
		if(!phoneReg.test(employee_phone))
		{
			$(e_phone).parent().children().last().html("<font color='red'>联系方式格式错误</font>");
		}
		else 
			$(e_phone).parent().children().last().html("");
		
	if(employee_address=="")
	{
			$(e_address).parent().children().last().html("<font color='red'>所在地区不能为空</font>");
	}
	else 
		$(e_address).parent().children().last().html("");
		
	if(employee_email=="")
	{
			$(e_email).parent().children().last().html("<font color='red'>QQ/邮箱不能为空</font>");
	}
	else 
		if(!emailReg.test(employee_email)&&!qqReg.test(employee_email))
		{
			$(e_email).parent().children().last().html("<font color='red'>QQ/邮箱格式错误</font>");
		} 
	else 
		$(e_email).parent().children().last().html("");
		  
	if(employee_name!=""&&employee_phone!=""&&phoneReg.test(employee_phone)&&
			employee_address!=""&&employee_email!=""&&emailReg.test(employee_email))
	{
			check=true;
	}
	if(employee_name!=""&&employee_phone!=""&&phoneReg.test(employee_phone)&&
			employee_address!=""&&employee_email!=""&&qqReg.test(employee_email))
	{
		check=true;
	}
	
	return check;
}