var userName = null;
var telphone = null;
var userPassword = null;
var ruserPassword = null;
var securityCode=null;
var addBtn = null;
var backBtn = null;


$(function(){
	telphone = $("#telphone");
	userName = $("#userName");
	userPassword = $("#userPassword");
	ruserPassword = $("#ruserPassword");
	securityCode = $("#securityCode")
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	telphone.next().html("*");
	userName.next().html("*");
	userPassword.next().html("*");
	ruserPassword.next().html("*");
	
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	telphone.bind("blur",function(){
		//ajax后台验证--telphone是否已存在
		$.ajax({
			type:"GET",//请求类型
			url:path+"/isexisttelphone.html",//请求的url
			data:{method:"isExistTelphone",telphone:telphone.val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.result == "true"){//手机号已存在，错误提示
					validateTip(telphone.next(),imgNo+ " 该手机号已存在",false);
				}else{//账号可用，正确提示
					validateTip(telphone.next(),imgYes+" 该手机号可以使用",true);
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				validateTip(telphone.next(),imgNo+" 您访问的页面不存在",false);
			}
		});

	}).focus();
	
	userName.bind("focus",function(){
		//ajax后台验证--telphone是否已存在
		$.ajax({
			type:"GET",//请求类型
			url:path+"/isexistusername.html",//请求的url
			data:{method:"isExistTelphone",userName:userName.val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.result == "true"){//手机号已存在，错误提示
					validateTip(userName.next(),imgNo+ " 该用户名已存在",false);
				}else{//账号可用，正确提示
					validateTip(userName.next(),imgYes+" 该用户名可以使用",true);
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				validateTip(telphone.next(),imgNo+" 您访问的页面不存在",false);
			}
		});
	}).bind("focus",function(){
		validateTip(userName.next(),"* 用户名长度必须是大于1小于10的字符",false);
	}).bind("blur",function(){
		if(userName.val() != null && userName.val().length > 1
				&& userName.val().length < 10){
			validateTip(userName.next(),imgYes,true);
		}else{
			validateTip(userName.next(),imgNo+" 用户名输入的不符合规范，请重新输入",false);
		}
		
	});
	
	userPassword.bind("focus",function(){
		validateTip(userPassword.next(),"* 密码长度必须是大于6小于20",false);
	}).bind("blur",function(){
		if(userPassword.val() != null && userPassword.val().length > 6
				&& userPassword.val().length < 20 ){
			validateTip(userPassword.next(),imgYes,true);
		}else{
			validateTip(userPassword.next(),imgNo + " 密码输入不符合规范，请重新输入",false);
		}
	});
	
	ruserPassword.bind("focus",function(){
		validateTip(ruserPassword.next(),"* 请输入与上面一只的密码",false);
	}).bind("blur",function(){
		if(ruserPassword.val() != null && ruserPassword.val().length > 6
				&& ruserPassword.val().length < 20 && userPassword.val() == ruserPassword.val()){
			validateTip(ruserPassword.next(),imgYes,true);
		}else{
			validateTip(ruserPassword.next(),imgNo + " 两次密码输入不一致，请重新输入",false);
		}
	});

	securityCode.on("click",function () {
		$.ajax({
			type: "POST",//请求类型
			url: path+"/getphonecode.html",//请求的url
			data: {method: "getphonecode" ,phone: telphone.val()},//请求参数
			dataType: "json",//ajax接口（请求url）返回的数据类型
		});
	})
	
	addBtn.bind("click",function(){
		if(telphone.attr("validateStatus") != "true"){
			telphone.blur();
		}else if(userName.attr("validateStatus") != "true"){
			userName.blur();
		}else if(userPassword.attr("validateStatus") != "true"){
			userPassword.blur();
		}else if(ruserPassword.attr("validateStatus") != "true"){
			ruserPassword.blur();
		}else{
			if(confirm("是否确认提交数据")){
				$("#userForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});