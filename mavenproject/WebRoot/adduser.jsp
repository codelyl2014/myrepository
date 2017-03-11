<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>增删改</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form method="post" name="userlist" >
		<h1 align="center">增删员工表</h1>
		<center>
			<table>
				
				<tr>
					<td>username</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>displayname</td>
					<td><input type="text" name="displayname"></td>
				</tr>
				<tr>
					<td>position</td>
					<td><input type="text" name="position"></td>
				</tr>
				<tr>
					<td>positionname</td>
					<td><input type="text" name="positionname"></td>
				</tr>
				<tr>
					<td>active</td>
					<td><input type="text" name="active"></td>
				</tr>
				
				<tr>
					<td><input type="button" name="add" value="增加" onclick="document.userlist.action='AddUserServlet';document.userlist.submit();"></td>
					<td><input type="button" name="update" value="修改" onclick="document.userlist.action='UpdateUserServlet';document.userlist.submit();"></td>
				    <td><input type="button" name="back" value="返回" onclick="window.history.back(-1)"><td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>
