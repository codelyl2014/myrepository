<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>searchid page</title>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
	<div class="container">
		<h2 align="center" style="margin-bottom: 40px;">项目id对照表</h2>
		<table class="table table-bordered"
			style="width: 80%;margin-right: auto;margin-left: auto;">
			<thead>
				<th>项目ID</th>
				<th>项目名称</th>
			</thead>
			<tbody id="projbody"></tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
	$(function() {
	console.log("error")
		$.ajax({
			type : "post",
			url : "JiraServlet",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log(data);
				//var projList=eval(data);
				var projList = data;
				var tbody = "";
				for (i = 0, len = projList.length; i < len; i++) {

					tbody += '<tr>';
					tbody += '<td>' + projList[i].ID + '</td>';
					tbody += '<td>' + projList[i].pname + '</td>';
					tbody += '</tr>';
				}
				$("#projbody").append(tbody);

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert(errorThrown);
				console.log(errorThrown);
			}
		});

	})
</script>
</html>
