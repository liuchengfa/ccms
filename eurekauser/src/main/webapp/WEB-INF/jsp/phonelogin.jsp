<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>用户登录</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <script type="text/javascript" src="./js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="./js/common.js"></script>
    <script type="text/javascript" src="./js/phonelogin.js"></script>
</head>
<body>
	<div id="userlogin">
		<h3>用户手机验证码登录</h3>
		<form action="/user/phonelogin.html" method="post">
            <div style="color: red">${message}</div>
			<table align="center">
				<tr>
					<td>
						手机号：
					</td>
					<td>
						<input type="text" placeholder="请输入手机号"  id="telphone" name="telphone" value="${phone}">
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
					 <td></td>
					 <td>
						<input type="submit" value="登录" />
					 	<input type="button" value="注册" onclick="window.location.href='register.html'" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
