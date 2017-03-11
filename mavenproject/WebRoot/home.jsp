<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>跳转页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
a {
	background-color: transparent;
	text-decoration: none;
}

a:active,a:hover {
	outline: 0;
	text-decoration: underline;
}

.container {
	width: 60%;
	height: 80%;
	margin-top: 10%;
	padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

.panel {
	border: 1px solid #00F;
	margin-top: 20px;
	border-radius: 4px;
	-webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}
</style>
</head>

<body>
	<div class="container">
		<div class="panel">
			<h2 align="center">
				<a href="showpageworktime.jsp">技术与平台中心员工工时计算系统</a>
			</h2>
		</div>
		<div class="panel">
			<h2 align="center">
				<a href="showpagereport.jsp">质量月报统计系统</a>
			</h2>
		</div>
	</div>
</body>
</html>
