<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>工作人员主页</title>
	<link type="text/css" rel="stylesheet" href="./css/style.css" />
	<script type="text/javascript" src="./js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(function (){
			function test(divObj,aobj) {
				var myDiv = $(divObj);
				$(aobj).mouseover(function (event) {
					$(divObj).fadeIn();;//调用显示DIV方法
					$(document).one("mouseover", function () {//对document绑定一个影藏Div方法
						$(divObj).hide();
					});
					event.stopPropagation();//阻止事件向上冒泡
				});

				$(divObj).mouseover(function (event) {
					event.stopPropagation();//阻止事件向上冒泡
				});
			}
			test("#myDiv", "#submeau");
			test("#myDiv2", "#submeau2");
			test("#myDiv3", "#submeau3");
		});
	</script>
</head>
<body>
	<div style="height: 100%">
		<div id="workerman">
			<div style="color: cornsilk">
				<div style="color:violet">
				  <div style="float: right">
					<input type="button" value="重登录"/>
					<input type="button" value="退出"/>
				  </div>
				欢迎您：user+管理员</div>
			</div>
		</div>
		<div id="th">
			<div id="opr" style="float: right">
			   后台管理人员的最近五次操作：按时间降序排列
			</div>
			<div id="manage" style="float: left">
				<div class="bs">
				  	<a id="submeau" href="javascript:;">账户管理</a>
					<div id="myDiv" style="display: none" >
						<li class="no_circle"><a>账户首页管理</a></li>
						<li class="no_circle"><a>账户交易历史</a></li>
					</div>
				</div>
				<div class="bs">
				  	<a id="submeau2" href="javascript:;">业务管理</a>
					<div id="myDiv2" style="display: none">
						<li class="no_circle"><a>修改密码</a></li>
						<li class="no_circle"><a>挂失处理</a></li>
						<li class="no_circle"><a>账号冻结</a></li>
						<li class="no_circle"><a>销卡处理</a></li>
						<li class="no_circle"><a>激活</a></li>
					</div>
				</div>
				<div class="bs">
				  	<a id="submeau3" href="javascript:;">动态反馈</a>
					<div id="myDiv3" style="display: none">
						<li class="no_circle"><a>信用卡申请处理</a></li>
						<li class="no_circle"><a>用户额度处理”</a></li>
					</div>
				</div>
				<div class="bs">
				  	<a href="">分期管理</a>
				</div>
			</div>
			<div id="reminder">
				<div style="color: #F5E612;background: #FCFBFB">
					温馨提醒：您的上次登录时间为+date，请确保上次操作为本人操作
				</div>
				<div>
					银行近期公告:
				</div>
			</div>
		</div>
	</div>
</body>
</html>