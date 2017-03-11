<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>人员增删改</title>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<div class="container">
		<h2 align="center" style="margin-bottom: 40px;">人员信息</h2>
		<input type="button" name="add" class="btn btn-primary"
			style="float:right;margin-bottom:10px;" value="增加"
			onclick="adduser()">
		<table class="table table-bordered">
			<thead>
				<th>id</th>
				<th>username</th>
				<th>displayname</th>
				<th>position</th>
				<th>positionname</th>
				<th>active</th>
				<th>修改</th>
				<th>删除</th>
			</thead>
			<tbody id="userbody"></tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		console.log("error")
		$.ajax(
		{
					type : "post",
					url : "workers/list",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					success : function(data) {
						console.log(data);
						var userlist=eval(data);
						//var userlist = data;
						var tbody = "";
						for (i = 0, len = userlist.length; i < len; i++) {
							tbody += '<tr>';
							tbody += '<td>' + userlist[i].id + '</td>';
							tbody += '<td>' + userlist[i].user_name + '</td>';
							tbody += '<td>' + userlist[i].display_name
									+ '</td>';
							tbody += '<td>' + userlist[i].position + '</td>';
							tbody += '<td>' + userlist[i].position_name
									+ '</td>';
							tbody += '<td>' + userlist[i].active + '</td>';
							//tbody += '<td>' + '<input type="button" name="update" value="修改" onclick="window.location.href=\'adduser.jsp\'">' + '</td>';
							tbody += '<td>'
									+ '<input type="button" name="update" value="修改" data-id='
									+ userlist[i].id
									+ ' onclick="modifyuser()">' + '</td>';
							tbody += '<td>'
									+ '<input type="button" name="update" value="删除" data-id='
									+ userlist[i].id + ' onclick="deleteuser('
									+ userlist[i].id + ')">' + '</td>';
							//tbody += '<td>' + '<input type="button" name="delete" value="删除" onclick="window.location.href=\'adduser.jsp\'">' + '</td>';
							tbody += '</tr>';
						}
						$("#userbody").append(tbody);

					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						//alert(errorThrown);
						console.log(errorThrown);
					}
				});

	});

	function deleteuser(uid) {
		console.log(uid);
		alert('确定');
		$.ajax({
			type : "get",
			url : "DeleteUserServlet",
			data : 'id=' + uid,
			success : function() {
				alert("delete success!");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert(errorThrown);
				console.log(errorThrown);
			}
		});
	}

	function adduser() {
		alert('the function is on the way');
	}
	function modifyuser() {
		alert('the function is on the way');
	}
</script>
</html>
