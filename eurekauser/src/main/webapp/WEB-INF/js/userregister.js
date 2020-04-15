var backBtn = null;
var addBtn=null;
var path = null;
var imgYes = "<img width='15px' src='./images/y.png' />";
var imgNo = "<img width='15px' src='./images/n.png' />";
var userName = null;
var userPassword=null;
var ruserPassword=null;
var telphone=null;
var securityCode=null;
$(function() {
    userName = $("#userName");
    userName.next().html("");
    userName.bind("blur", function () {
        //ajax后台验证--用户名是否已存在
        $.ajax({
            type: "GET",//请求类型
            url: "/user/isexistusername.html",//请求的url
            data: {method: "isExistUserName", userName: userName.val()},//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {//data：返回数据（json对象）
                if (data.result == "true") {//用户名已存在，错误提示
                    validateTip(userName.next(), imgNo+" 该用户名已存在", false);
                } else {//账号可用，正确提示
                    validateTip(userName.next(), imgYes, true);
                }
            }
        });
    }).bind("focus", function () {
        validateTip(userName.next(), "* 用户名长度必须是大于1小于10的字符", false);
    }).bind("blur", function () {
        if (userName.val() != null && userName.val().length > 1
            && userName.val().length < 10) {
            validateTip(userName.next(),imgYes, true);
        } else {
            validateTip(userName.next(), imgNo + " 用户名输入的不符合规范，请重新输入", false);
        }
    });

    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    telphone = $("#telphone")
    telphone.bind("blur",function(){
        //ajax后台验证--telphone是否已存在
        $.ajax({
            type:"GET",//请求类型
            url:"/user/isexisttelphone.html",//请求的url
            data:{method:"isExistTelphone",telphone:telphone.val()},//请求参数
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                if(data.result == "true"){//手机号已存在，错误提示
                    validateTip(telphone.next(),imgNo+ " 该手机号已被注册",false);
                }
            }
        });

    });

    securityCode=$("#securityCode");
    securityCode.bind("click",function () {
        $.ajax({
            type: "GET",//请求类型
            url: "/user/getphonecode.html",//请求的url
            data: {method: "getphonecode",phone: telphone.val()},//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {//data：返回数据（json对象）
                if (data.result == "true") {//用户名已存在，错误提示
                    validateTip(securityCode.next(), " 请输入手机号！", false);
                }
            }
        });
    }).bind("click",function () {
        validateTip(securityCode.next(), " 验证码已发送！", true);
    });

    userPassword = $("#userPassword");
    userPassword.bind("focus",function(){
        validateTip(userPassword.next(),"* 密码长度必须是大于5小于20",false);
    }).bind("blur",function(){
        if(userPassword.val() != null && userPassword.val().length >= 6
            && userPassword.val().length < 20 ){
            validateTip(userPassword.next(),imgYes,true);
        }else{
            validateTip(userPassword.next(),imgNo + " 密码输入不符合规范，请重新输入",false);
        }
    });

    ruserPassword = $("#ruserPassword");
    ruserPassword.bind("focus",function(){
        validateTip(ruserPassword.next(),"* 请输入与上面一致的密码",false);
    }).bind("blur",function(){
        if(ruserPassword.val() != null && ruserPassword.val().length >= 6
            && ruserPassword.val().length < 20 && userPassword.val() == ruserPassword.val()){
            validateTip(ruserPassword.next(),imgYes,true);
        }else{
            validateTip(ruserPassword.next(),imgNo + " 两次密码输入不一致，请重新输入",false);
        }
    });

    addBtn = $("#add");
    addBtn.bind("click",function(){
        if(confirm("是否确认提交数据")){
            $("#userForm").submit();
        }
    });
})