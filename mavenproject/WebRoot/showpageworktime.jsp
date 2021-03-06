<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

<title>计算工时</title>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/boostrap.min.js"></script>
<style type="text/css">
.row {
	margin-top: 10px;
}

h2 {
	margin-top: 25px;
	margin-bottom: 25px;
}

th {
	text-align: center;
}

.topbread {
	padding: 2px 7px;
	margin-bottom: 7px;
}

.loading {
	position: absolute;
	width: 300px;
	/*top: 250px;*/
	left: 50%;
	margin-top: 20px;
	margin-left: -150px;
	text-align: center;
	padding: 7px 0 0 0;
	font: bold 11px Arial, Helvetica, sans-serif;
}
</style>

</head>

<body>
	<div class="container">
		<div>
			<ol class="breadcrumb topbread ">
				<li class="active"><span style="font-weight:bold;">技术与平台中心员工工时计算系统</span>
				</li>
				<li><a href="showpagereport.jsp" target="_blank">质量月报统计系统</a>
				</li>
				<li style="float:right;"><a href="manageuser.jsp"
					target="_blank">技术与平台中心人员</a></li>
			</ol>
		</div>
		<div style="margin-left:auto;margin-right:auto;">
			<h2 align="center">技术与平台中心员工工时计算系统</h2>
			<form method="post" name="inputdate" id="inputdateform">
				<!--<form method="POST" name="inputdate" action="Calculate">-->

				<div class="row">
					<div class="col-md-2">开始时间：</div>
					<div class="col-md-3">
						<input type="date" class="form-control" id="startdate"
							name="startdate" />
					</div>
					<div class="col-md-2">结束时间：</div>
					<div class="col-md-3">
						<input type="date" class="form-control" id="enddate"
							name="enddate" />
					</div>
					<div class="col-md-2" style="float: right;">
						<!--<input type="submit" value="开始计算" name="startcal" />-->
						<input type="button" class="btn btn-primary form-control"
							value="开始计算" onclick="calWorklog()" />
					</div>
				</div>
			</form>
		</div>
		<hr />

		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					技术与平台中心人员的项目总工时 <a data-toggle="collapse" href="#lxltb1"
						style="float: right;">展开/折叠</a>
				</h4>
			</div>
			<div id="lxltb1" class="panel-collapse collapse">
				<div class="panel-body">
					<table class="table table-bordered" id="lxltb11">
						<thead>
							<tr>
								<th>项目名称</th>
								<th>技术与平台人员总工时/H</th>
								<th>起止时间段</th>
								<th>统计时间</th>
							</tr>
						</thead>
						<tbody id="lxlbody1"></tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					技术与平台中心人员不满足每天8H的人员名单 <a data-toggle="collapse" href="#lxltb2"
						style="float: right;">展开/折叠</a>
				</h4>
			</div>
			<div id="lxltb2" class="panel-collapse collapse">
				<div class="panel-body">
					<table class="table table-bordered" id="lxltb22">
						<thead>
							<tr>
								<th>域账户</th>
								<th>姓名</th>
								<th>异常日期</th>
								<th>实际填写工时/H</th>
								<th>起止时间段</th>
								<th>统计时间</th>
							</tr>
						</thead>
						<tbody id="lxlbody2"></tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					技术与平台中心人员不满足每天8H的人员项目占比 <a data-toggle="collapse" href="#lxltb3"
						style="float: right;">展开/折叠</a>
				</h4>
			</div>
			<div id="lxltb3" class="panel-collapse collapse">
				<div class="panel-body">
					<table class="table table-bordered" id="lxltb33">
						<thead>
							<tr>
								<th>项目名称</th>
								<th>异常人员总工时/H</th>
								<th>技术与平台人员总工时/H</th>
								<th>占比</th>
								<th>起止时间段</th>
								<th>统计时间</th>
							</tr>
						</thead>
						<tbody id="lxlbody3"></tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					技术与平台中心人员不满足理论总工时80%的人员名单 <a data-toggle="collapse" href="#lxltb4"
						style="float: right;">展开/折叠</a>
				</h4>
			</div>
			<div id="lxltb4" class="panel-collapse collapse">
				<div class="panel-body">
					<table class="table table-bordered" id="lxltb44">
						<thead>
							<tr>
								<th>域账户</th>
								<th>姓名</th>
								<th>实际填写总工时/H</th>
								<th>起止时间段</th>
								<th>统计时间</th>
							</tr>
						</thead>
						<tbody id="lxlbody4"></tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					技术与平台中心人员不满足理论总工时80%的人员项目占比 <a data-toggle="collapse"
						href="#lxltb5" style="float: right;">展开/折叠</a>
				</h4>
			</div>
			<div id="lxltb5" class="panel-collapse collapse">
				<div class="panel-body">
					<table class="table table-bordered" id="lxltb55">
						<thead>
							<tr>
								<th>项目名称</th>
								<th>异常人员总工时/H</th>
								<th>技术与平台人员总工时/H</th>
								<th>占比</th>
								<th>起止时间段</th>
								<th>统计时间</th>
							</tr>
						</thead>
						<tbody id="lxlbody5"></tbody>
					</table>
				</div>
			</div>
		</div>

	</div>

	<div class="modal fade" id="loading">
		<div class="modal-dialog">
			<div class="modal-content"
				style="height:250px;position: relative;width: 300px;margin-top: 200px; ">
				<div class="modal-header" style="widith:20px;padding:0;">
					<h4 class="modal-title" style="widith:20px;text-align:center;">正在加载中，请稍候。。。</h4>
				</div>
				<div class="modal-body" style="padding-left:40px;padding-top:0">
					<img src="img/loading.gif" />
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function calWorklog() {
		if ($.trim($("#startdate").val()) == "") {
			alert("请输入开始日期！");
			return;
		} else if ($.trim($("#enddate").val()) == "") {
			alert("请输入结束日期！");
			return;
		} else {
			$('#loading').modal({
				backdrop : 'static',
				keyboard : false,
				show : true
			});
			$.ajax({
				type : "post",
				url : "Calculate",
				//url: "../theme/files/5p.json",
				async : true,
				data : $("#inputdateform").serialize(),
				success : function(str) {
					if (str == '[]') {
						$('#loading').modal('hide');
						alert("无相关数据，请修改查询条件！");
					} else {
						//console.log(str);
						//var data = eval('(' + str + ')');
						var data =  JSON.parse(str);
						console.log(data);
						var tb1 = tb2 = tb3 = tb4 = tb5 = '';
						var lxl1 = data.project_worklog;
						var lxl2 = data.user_everyday;
						var lxl3 = data.every_worklog;
						var lxl4 = data.user_total;
						var lxl5 = data.total_worklog;
						var len1 = data.project_worklog.length;
						var len2 = data.user_everyday.length;
						var len3 = data.every_worklog.length;
						var len4 = data.user_total.length;
						var len5 = data.total_worklog.length;

						for (i = 0; i < len1; i++) {
							tb1 += '<tr>';
							tb1 += '<td>' + lxl1[i].projname + '</td>';
							tb1 += '<td>' + (lxl1[i].sumworked / 3600)
									+ '</td>';
							tb1 += '<td>' + lxl1[i].batch + '</td>';
							tb1 += '<td>' + lxl1[i].createddate + '</td>';
							tb1 += '</tr>';
						}
						

						for (i = 0; i < len2; i++) {
							tb2 += '<tr>';
							tb2 += '<td>' + lxl2[i].lower_user_name + '</td>';
							tb2 += '<td>' + lxl2[i].display_name + '</td>';
							tb2 += '<td>' + lxl2[i].workday + '</td>';
							tb2 += '<td>' + (lxl2[i].timeworked / 3600)
									+ '</td>';
							tb2 += '<td>' + lxl2[i].batch + '</td>';
							tb2 += '<td>' + lxl2[i].createddate + '</td>';
							tb2 += '</tr>';
						}
						

						for (i = 0; i < len3; i++) {
							tb3 += '<tr>';
							tb3 += '<td>' + lxl3[i].projname + '</td>';
							tb3 += '<td>' + (lxl3[i].sum_everyday_work / 3600)
									+ '</td>';
							tb3 += '<td>' + (lxl3[i].sumworked / 3600)
									+ '</td>';
							var perc3 = Math.round(lxl3[i].sum_everyday_work
									/ lxl3[i].sumworked * 10000) / 100.00
							tb3 += '<td>' + (perc3 || '-') + '%</td>';
							tb3 += '<td>' + lxl3[i].batch + '</td>';
							tb3 += '<td>' + lxl3[i].createddate + '</td>';
							tb3 += '</tr>';
						}
						

						for (i = 0; i < len4; i++) {
							tb4 += '<tr>';
							tb4 += '<td>' + lxl4[i].lower_user_name + '</td>';
							tb4 += '<td>' + lxl4[i].display_name + '</td>';
							tb4 += '<td>' + (lxl4[i].timeworked / 3600)
									+ '</td>';
							tb4 += '<td>' + lxl4[i].batch + '</td>';
							tb4 += '<td>' + lxl4[i].createddate + '</td>';
							tb4 += '</tr>';
						}
						

						for (i = 0; i < len5; i++) {
							tb5 += '<tr>';
							tb5 += '<td>' + lxl5[i].projname + '</td>';
							tb5 += '<td>' + (lxl5[i].sum_total_work / 3600)
									+ '</td>';
							tb5 += '<td>' + (lxl5[i].sumworked / 3600)
									+ '</td>';
							var perc5 = Math.round(lxl5[i].sum_total_work
									/ lxl5[i].sumworked * 10000) / 100.00
							tb5 += '<td>' + (perc5 || '-') + '%</td>';
							tb5 += '<td>' + lxl5[i].batch + '</td>';
							tb5 += '<td>' + lxl5[i].createddate + '</td>';
							tb5 += '</tr>';
						}
						$('#loading').modal('hide');
						$("#lxltb1").collapse('show');
						$("#lxltb1").collapse('show');
						$("#lxltb2").collapse('show');
						$("#lxltb3").collapse('show');
						$("#lxltb4").collapse('show');
						$("#lxltb5").collapse('show');
						
						$('#lxlbody1').empty();
						$('#lxlbody2').empty();
						$('#lxlbody3').empty();
						$('#lxlbody4').empty();
						$('#lxlbody5').empty();
						$('#lxlbody1').append(tb1);
						$('#lxlbody2').append(tb2);
						$('#lxlbody3').append(tb3);
						$('#lxlbody4').append(tb4);
						$('#lxlbody5').append(tb5);
						
						//$("#lxltb1").addClass('in');
						/* $("#lxltb2").addClass('in');
						$("#lxltb3").addClass('in');
						$("#lxltb4").addClass('in');
						$("#lxltb5").addClass('in'); */
					}

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(errorThrown);
				}
			});
		}
	}
</script>

</html>