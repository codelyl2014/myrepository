<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

<title>质量月报</title>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/boostrap.min.js"></script>

<style type="text/css">
.disptables {
	float: left;
	width: 33%;
	/* margin-top:10px; */
	margin-left: auto;
	margin-right: auto;
	boder: 1px;
}

.row {
	margin-top: 10px;
}

h2 {
	margin-top: 8px;
	margin-bottom: 7px;
}

th {
	text-align: center;
}

.topbread {
	padding: 2px 7px;
	margin-bottom: 7px;
}

tr {
	text-align: center;
	background-color: #F5FFFA;
}

.b {
	font-weight: bold;
}

.alert-info {
	border-color: #F8F8FF;
	background-color: #f5f5f5;
	/* color: #000000; */
}
</style>

</head>

<body>
	<div class="container" style="width:80%;">
		<div>
			<ol class="breadcrumb topbread ">
				<li class="active"><span style="font-weight:bold;">质量月报统计系统</span>
				</li>
				<li><a href="showpageworktime.jsp" target="_blank">技术与平台中心员工工时计算系统</a>
				</li>
				<li style="float:right;"><a href="manageuser.jsp"
					target="_blank">技术与平台中心人员</a>
				</li>
			</ol>
		</div>
		<div class="bs-docs-section">
			<h2 align="center">质量月报统计系统</h2>
			<div style="height: auto;margin-bottom: 10px;margin-top: 30px; ">
				<form name="inputdate" id="calProjMeasure">

					<div class="row">
						<div class="col-md-2">项目id</div>
						<div class="col-md-4">
							<input name="department" class="form-control" type="text" />
						</div>
						<div class="col-md-2">
							<a href="searchid.jsp" target="_blank">项目id查询</a>
						</div>
						<div class="col-md-4"></div>

					</div>
					<div class="row">
						<div class="col-md-2">开始时间：</div>
						<div class="col-md-4">
							<input type="date" class="form-control" name="startdate" />
						</div>
						<div class="col-md-2">结束时间 ：</div>
						<div class="col-md-4">
							<input type="date" class="form-control" name="enddate" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<input class="btn btn-primary form-control" value="计算度量项"
								onclick="calMeasure()" />
						</div>
						<div class="col-md-3">
							<input class="btn btn-primary form-control" value="计算测试工时(仅技平)"
								onclick="calTestTime()" />
						</div>
						<div class="col-md-3">
							<input class="btn btn-primary form-control" value="计算开发工时(仅技平)"
								onclick="calDevelopTime()" />
						</div>
						<div class="col-md-3">
							<input class="btn btn-primary form-control" value="计算开发工时(所有)"
								onclick="calAllDevelopTime()" />
						</div>
						<!--<div class="col-md-3"></div>-->
					</div>

				</form>
			</div>
			<div class="alert alert-info alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<span style="font-weight: bold;">温馨提示：</span>查询之后，如果不手动刷新页面，历史查询记录就不会消失哦~
			</div>
			<div class="bs-example bs-example-tabs"
				data-example-id="togglable-tabs" id="myTab">
				<ul id="myTabs" class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#qualityboard"
						data-toggle="tab" class="b">项目度量项结果</a>
					</li>
					<li role="presentation"><a href="#testerwork"
						data-toggle="tab" class="b">测试工时<span class="small">（仅技术与平台中心测试人员）</span>
					</a>
					</li>
					<li role="presentation"><a href="#developerwork"
						data-toggle="tab" class="b">开发工时<span class="small">（仅技术与平台中心开发人员）</span>
					</a>
					</li>
					<li role="presentation"><a href="#alldeveloperwork"
						data-toggle="tab" class="b">开发工时<span class="small">（所有开发人员）</span>
					</a>
					</li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<p></p>
					<div class="tab-pane fade in active" id="qualityboard">
						<div style="width: 100%;">
							<table id="testertable" class="table" border="1" cellspacing="1"
								cellpadding="1" style="align-content: center; width: 100%;">

								<thead style="background-color:#B0E0E6;">
									<tr>
										<th align="center">项目id</th>
										<th align="center">项目名称</th>
										<th align="center">BUG总数</th>
										<th align="center">缺陷有效率</th>
										<th align="center">缺陷及时率</th>
										<th align="center">缺陷reopen率</th>
										<th align="center">缺陷遗漏率</th>
										<th align="center">P0P1验证时长/天</th>
										<th align="center">P2P3验证时长/天</th>
										<th align="center">开始-截止时间</th>
										<th align="center">统计时间</th>
									</tr>
								</thead>
								<tbody id="projMeasBody">
									<!-- <tr>
											<td align="center">12518</td>
											<td align="center">车享家线上线下一体化</td>
											<td align="center">240</td>
											<td align="center">98.88%</td>
											<td align="center">98.88%</td>
											<td align="center">98.88%</td>
											<td align="center">98.88%</td>
											<td align="center">6</td>
											<td align="center">5</td>
											<td align="center">2016-12-13——2016-12-14</td>
											<td align="center">2016-12-14 15:15:52</td>
										</tr> -->
								</tbody>
							</table>
						</div>
					</div>
					<div class="tab-pane fade" id="testerwork">
						<div>
							<table id="testertable" class="table" border="1" cellspacing="1"
								cellpadding="1" style="align-content: center; width: 100%;">
								<thead>
									<tr>
										<th align="center">项目id</th>
										<th align="center">测试总工时/天</th>
										<th align="center">开始-截止时间</th>
										<th align="center">统计时间</th>
									</tr>
								</thead>
								<tbody id="testerWorkBody">
									<!-- <tr>
											<td align="center">12518</td>
											<td align="center">车享家线上线下一体化</td>
											<td align="center">106.0</td>
											<td align="center">2016-12-13——2016-12-14</td>
											<td align="center">2016-12-14 15:15:52</td>
										</tr> -->
								</tbody>
							</table>
						</div>
					</div>
					<div class="tab-pane fade" id="developerwork">
						<div>
							<table id="testertable" class="table" border="1" cellspacing="1"
								cellpadding="1" style="align-content: center; width: 100%;">
								<thead>
									<tr>
										<th align="center">项目id</th>
										<th align="center">开发总工时/天</th>
										<th align="center">开始-截止时间</th>
										<th align="center">统计时间</th>
									</tr>
								</thead>
								<tbody id="developerWorkBody">
									<!-- <tr>
											<td align="center">12518</td>
											<td align="center">车享家线上线下一体化</td>
											<td align="center">106.0</td>
											<td align="center">2016-12-13——2016-12-14</td>
											<td align="center">2016-12-14 15:15:52</td>
										</tr> -->
								</tbody>
							</table>
						</div>
					</div>
					<div class="tab-pane fade" id="alldeveloperwork">
						<div>
							<table id="testertable" class="table" border="1" cellspacing="1"
								cellpadding="1" style="align-content: center; width: 100%;">
								<thead>
									<tr>
										<th align="center">项目id</th>
										<th align="center">项目名称</th>
										<th align="center">开发总工时/天</th>
										<th align="center">开始-截止时间</th>
										<th align="center">统计时间</th>
									</tr>
								</thead>
								<tbody id="alldeveloperWorkBody">
									<!-- <tr>
											<td align="center">12518</td>
											<td align="center">车享家线上线下一体化</td>
											<td align="center">209.0</td>
											<td align="center">2016-12-17——2016-12-24</td>
											<td align="center">2016-12-15 15:56:34</td>
										</tr> -->
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div class="modal fade" id="loading">
		<div class="modal-dialog">
			<div class="modal-content"
				style="height:250px;position: relative;width: 300px; ">
				<!--<div class="modal-content" style="height:100%;position: relative;width: 100%; "> -->
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
	function calMeasure() {
		$('#loading').modal({
			backdrop : 'static',
			keyboard : false,
			show : true
		});
		//$('#loading').modal('show');
		console.log($("#calProjMeasure").serialize());
		$
				.ajax({
					type : "post",
					url : "CalculateMeasure",
					data : $("#calProjMeasure").serialize(),
					success : function(data) {
						if (data == '[]') {
							$('#loading').modal('hide');
							alert("无相关数据，请修改查询条件！");
						} else {
							var projList = eval(data);
							console.log(projList);
							//var projList=data;
							var tbody = "";
							tbody += '<tr>';
							tbody += '<td>' + projList[0].projectid + '</td>';
							tbody += '<td>' + projList[0].projname + '</td>';
							tbody += '<td>' + projList[0].totalbugs + '</td>';
							var valid = Math.round(projList[0].valid * 10000) / 100.00;
							var beforepre = Math
									.round(projList[0].beforepre * 10000) / 100.00;
							var reopen = Math.round(projList[0].reopen * 10000) / 100.00;
							var productenv = Math
									.round(projList[0].productenv * 10000) / 100.00;
							var p0p1 = Math.round(projList[0].p0p1 / 288) / 100.00;
							var p2p3 = Math.round(projList[0].p2p3 / 288) / 100.00;
							tbody += '<td>' + valid + '%</td>';
							tbody += '<td>' + beforepre + '%</td>';
							tbody += '<td>' + reopen + '%</td>';
							tbody += '<td>' + productenv + '%</td>';
							tbody += '<td>' + p0p1 + '</td>';
							tbody += '<td>' + p2p3 + '</td>';
							tbody += '<td>' + projList[0].batch + '</td>';
							tbody += '<td>' + projList[0].createddate + '</td>';
							tbody += '</tr>';
							$('#loading').modal('hide');
							$("#myTab li:eq(0) a").tab('show');
							//$("#qualityboard").addClass('in active');
							$("#projMeasBody").append(tbody);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						//alert(errorThrown);
						console.log(errorThrown);
					}
				});
	}

	function calTestTime() {
		$('#loading').modal({
			backdrop : 'static',
			keyboard : false,
			show : true
		});
		//$('#loading').modal('show');
		console.log($("#calTeseterWork").serialize())
		$
				.ajax({
					type : "post",
					url : "CalculateTestTime",
					data : $("#calProjMeasure").serialize(),
					success : function(data) {
						if (data == '[]') {
							$('#loading').modal('hide');
							alert("无相关数据，请修改查询条件！");
						} else {
							var testworkList = eval(data);
							console.log(testworkList);
							//var testworkList=data;
							var tbody = "";
							tbody += '<tr>';
							var sumworked = Math
									.round(testworkList[0].sumworked / 288) / 100.00;
							tbody += '<td>' + testworkList[0].projid + '</td>';
						/* 	tbody += '<td>' + testworkList[0].pname
									+ '</td>'; */
							tbody += '<td>' + sumworked + '</td>';
							tbody += '<td>' + testworkList[0].batch + '</td>';
							tbody += '<td>' + testworkList[0].createddate
									+ '</td>';
							tbody += '</tr>';
							/* for (i=0,len=projList.length;i<len;i++) {
								tbody+='<tr>';
								tbody+='<td>'+projList[i]['id']+'</td>';
								tbody+='</tr>';
							} */
							//console.log(tbody);
							$('#loading').modal('hide');
							$("#myTab li:eq(1) a").tab('show');
							//$("#testerwork").addClass('in active');
							$("#testerWorkBody").append(tbody);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						//alert(errorThrown);
						console.log(errorThrown);
					}
				});
	}

	function calDevelopTime() {
		$('#loading').modal({
			backdrop : 'static',
			keyboard : false,
			show : true
		});
		//$('#loading').modal('show');
		//console.log($("#calDeveloperWork").serialize())
		$
				.ajax({
					type : "post",
					url : "CalculateDevelopTime",
					data : $("#calProjMeasure").serialize(),
					success : function(data) {
						console.log(data);
						if (data == '[]') {
							$('#loading').modal('hide');
							alert("无相关数据，请修改查询条件！");
						} else {
							var devworkList = eval(data);
							console.log(devworkList);
							//var devworkList=data;
							var tbody = "";
							tbody += '<tr>';
							tbody += '<td>' + devworkList[0].projid + '</td>';
							/* tbody += '<td>' + devworkList[0].pname + '</td>'; */
							var sumworked = Math
									.round(devworkList[0].sumworked / 288) / 100.00;
							tbody += '<td>' + sumworked + '</td>';
							tbody += '<td>' + devworkList[0].batch + '</td>';
							tbody += '<td>' + devworkList[0].createddate
									+ '</td>';
							tbody += '</tr>';
							/* for (i=0,len=projList.length;i<len;i++) {
								tbody+='<tr>';
								tbody+='<td>'+projList[i]['id']+'</td>';
								tbody+='</tr>';
							} */
							$('#loading').modal('hide');
							$("#myTab li:eq(2) a").tab('show');
							//$("#developerwork").addClass('in active');
							$("#developerWorkBody").append(tbody);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						//alert(errorThrown);
						console.log(errorThrown);
					}
				});
	}

	function calAllDevelopTime() {
		$('#loading').modal({
			backdrop : 'static',
			keyboard : false,
			show : true
		});
		//$('#loading').modal('show');
		//console.log($("#calDeveloperWork").serialize())
		$.ajax({
			type : "post",
			url : "CalculateAllDevWorklog",
			data : $("#calProjMeasure").serialize(),
			success : function(data) {
				console.log(data);
				if (data == '[]') {
					$('#loading').modal('hide');
					alert("无相关数据，请修改查询条件！");
				} else {
					var devworkList = JSON.parse(data);
					console.log(devworkList);
					//var devworkList=data;
					var tbody = "";
					tbody += '<tr>';
					tbody += '<td>' + devworkList.project + '</td>';
					tbody += '<td>' + devworkList.pname + '</td>';
					var sumworked = Math.round(devworkList.sum / 288) / 100.00;
					tbody += '<td>' + sumworked + '</td>';
					tbody += '<td>' + devworkList.batch + '</td>';
					tbody += '<td>' + devworkList.createddate + '</td>';
					tbody += '</tr>';
					/* for (i=0,len=projList.length;i<len;i++) {
						tbody+='<tr>';
						tbody+='<td>'+projList[i]['id']+'</td>';
						tbody+='</tr>';
					} */
					//$("#alldeveloperWorkBody").tab('show');
					$("#myTab li:eq(3) a").tab('show');
					$('#loading').modal('hide');
					$("#alldeveloperWorkBody").append(tbody);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert(errorThrown);
				console.log(errorThrown);
			}
		});
	}
</script>

</html>