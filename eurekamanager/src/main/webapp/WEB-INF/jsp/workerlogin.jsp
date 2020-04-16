<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理人员登录</title>
</head>
<body>
    <form action="/manager/dologin.html" method="post">
        <table>
            <thead><h2>管理员后台登录</h2></thead>
            <tr>
                <td>账&nbsp;&nbsp;&nbsp;号:</td>
                <td><input type="text" id="anum" name="anum" value="${admin.anum}"/></td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;&nbsp;码:</td>
                <td><input type="password" id="aPassword" name="aPassword" value="${admin.aPassword}"/></td>
            </tr>
        </table>
        <input type="submit" value="登录"/>&nbsp;&nbsp;<input type="reset" value="重置"/>
    </form>
    <div style="color: #ff8e54">${message}</div>
</body>
</html>