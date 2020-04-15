<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <script type="text/javascript" src="./js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="./js/common.js"></script>
    <script type="text/javascript" src="./js/userregister.js"></script>
</head>
<body>

    <div id="userregister">
        <h3>信用卡系统用户注册</h3>
        <div style="color: red">
            ${message }
        </div>
        <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath }/doregister.html">
            <table align="center">
                <tr>
                    <td><label for="userName">用户名：</label></td>
                    <td>
                        <input type="text" name="userName" id="userName" value="${user.userName}">
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td><label for="telphone">手机号：</label></td>
                    <td>
                        <input type="text" name="telphone" id="telphone" value="${user.telphone}">
                        <font color="red"></font>
                    </td>

                </tr>
                <tr>
                    <td><label for="securityCode">验证码：</label></td>
                    <td><input type="text" id="code" name="code" value=""></td>
                    <td>
                        <input type="button" name="securityCode" id="securityCode" value="获取验证码">
                        <font color="red"></font>
                    </td>

                </tr>
                <tr>
                    <td><label for="userPassword">登录密码：</label></td>
                    <td>
                        <input type="password" name="userPassword" id="userPassword" value="${user.uPassword}">
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td><label for="ruserPassword">确认密码：</label></td>
                    <td>
                        <input type="password" name="ruserPassword" id="ruserPassword" value="">
                        <font color="red"></font>
                    </td>
                </tr>
            </table>
            <div>
                <input type="button" name="add" id="add" value="注册" >
            </div>
        </form>
        <p>已有账户？<a href="${pageContext.request.contextPath }/login.html">去登录</a></p>
    </div>
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
</body>

