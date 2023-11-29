<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<style>
.logC {
	width: 100%;
	margin: 0px auto;
	margin-top: 30px;
}
</style>
</head>
<body>

	<!-- 登录body -->
	<div class="logDiv">
		<div class="logGet">
			<!-- 头部提示信息 -->
			<div class="loginForm" style="height:500px">
				<div class="logD logDtip">
					<p class="p1">注册</p>
				</div>
				<!-- 登录表单 -->
				<form action="userServlet?method=addUser&kind=user"
					enctype="multipart/form-data" method="post">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th width="100">相片 :</th>
							<td style="text-align: center;">
								<div>
									<input type="file" name="file"><br />
								</div>
							</td>
						</tr>
						<tr>
							<th width="100">&nbsp;</th>
							<td style="text-align: center;"></td>
						</tr>
						<tr>
							<th width="100">学号/工号 :</th>
							<td>
								<div class="txtbox floatL" style="width: 215px;">
									<input name="id" id="id" type="text" size="20" />
								</div>
							</td>
						</tr>
						<tr>
							<th width="100">&nbsp;</th>
							<td style="text-align: center;"></td>
						</tr>
						<tr>
							<th>姓名 :</th>
							<td>
								<div class="txtbox floatL" style="width: 215px;">
									<input name="name" id="name" type="text" size="20" />
								</div>
							</td>
						</tr>
						<tr>
							<th width="100">&nbsp;</th>
							<td style="text-align: center;"></td>
						</tr>
						<tr>
							<th>所属学院 :</th>
							<td>
								<div class="txtbox floatL" style="width: 215px;">
									<select name="college" id="college">
										<option selected="selected"></option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<th width="100">&nbsp;</th>
							<td style="text-align: center;"></td>
						</tr>
						<tr>
							<th>专业 :</th>
							<td>
								<div class="txtbox floatL" style="width: 215px;">
									<select name="major" id="major">
										<option selected="selected"></option>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<th width="100">&nbsp;</th>
							<td style="text-align: center;"></td>
						</tr>
						<tr>
							<th>班级 :</th>
							<td>
								<div class="txtbox floatL" style="width: 215px;">
									<select name="classes" id="classes">
										<option selected="selected"></option>
									</select>
								</div>
							</td>
						</tr>

						<tr>
							<td colspan="3" style="text-align: center;"><div
									style="margin-left: 60px" class="logC">
									<input type="submit" value="注册" />
								</div>&nbsp;</td>
						</tr>
						<tr>
							<th width="100">&nbsp;</th>
							<td style="text-align: center;"></td>
						</tr>
						<tr>
							<td colspan="3" style="text-align: center;"><font
								color="red">${msg }</font></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!-- 登录body  end -->
		<br><br> <!-- 登录页面底部 -->
		<div class="logFoot">
			<div style="text-align: center">
				<!-- 块级标签 --><br><br><br>
				<a style="color: white; font-size: 18px" href="login.jsp">用户登录</a>
			</div>

		</div>
		<!-- 登录页面底部end -->

	</div>
	<script type="text/javascript">
		function check() {
			var password = document.getElementById("password").value;
			var repassword = document.getElementById("repassword").value;
			if (!password) {
				alert("请输入密码！");
				return false;
			}
			if (!repassword) {
				alert("请再次输入密码！");
				return false;
			}
			if (repassword != password) {
				alert("两次密码不一致！");
				return false;
			}
			return true;
		}
	</script>
	<script src="js/cascade.js" type="text/javascript"></script>
</body>
</html>