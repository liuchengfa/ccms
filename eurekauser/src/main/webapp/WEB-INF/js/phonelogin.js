var securityCode=null;
var telphone = null;
$(function() {
    securityCode=$("#securityCode");
    telphone = $("#telphone")
    telphone.bind("blur",function(){
        //ajax后台验证--telphone是否已存在
        $.ajax({
            type:"GET",//请求类型
            url:"/user/isexisttelphone.html",//请求的url
            data:{method:"isExistTelphone",telphone:telphone.val()},//请求参数
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                if(data.result == "false"){//手机号已存在，错误提示
                    validateTip(telphone.next()," 该手机号未注册，请注册后再登录！",false);
                }
            }
        });
    });
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
})