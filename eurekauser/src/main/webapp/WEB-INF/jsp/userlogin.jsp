<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<title>用户登录</title>
    <script type="text/javascript">
        function refresh(obj) {
            obj.src = "/user/img.html?"+Math.random();
        }
    </script>
</head>

<body>
    <div id="userlogin">
        <h3>用户登录</h3>
        <form name="loginForm" action="${pageContext.request.contextPath }/dologin.html" method="post">
            <table align="center">
                <tr>
                    <td><label for="userName">用户名：</label></td>
                    <td><input type="text" name="userName" id="userName" placeholder="请输入用户名" value="${user.userName}"></td>
                    <td><font color="red"></font></td>
                </tr>
                <tr>
                    <td><label for="userPassword">密码：</label></td>
                    <td><input type=password placeholder="请输入密码" id="userPassword" name="userPassword" value="${user.uPassword}" /></td>
                    <td><font color="red"></font></td>
                </tr>
                <tr>
                    <td><label for="image">随机验证码：</label></td>
                    <td><input type="text" placeholder="请输入验证码" id="code" name="code" value="" ></td>
                    <td>
                        <img id="image" name="image" border="0" height="30px" width="85px" src="/user/img.html" onclick="javascript:refresh(this);"/>
                        看不清？点击图片换一张
                    </td>

                </tr>
                <tr>
                    <td><input type="submit" value="登录" /></td>
                    <td><input type="button" value="手机号登录" onclick="window.location.href='phonelogin.html'" /></td>
                    <td><input type="button" value="注册" onclick="window.location.href='/user/register.html'" /></td>
                </tr>

            </table>
        </form>
        <div style="color: red">${message}</div>
    </div>
</body>
</html>
