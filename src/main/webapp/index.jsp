<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>Magic</title>
<link href="" type="">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/theme.css" rel="stylesheet">
<script src="resources/js/jquery-1.9.1.js" type="text/javascript"></script>
<script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"></script>
	<div class="container" style="width: 100%">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Trend</h3>
					</div>
					<div class="panel-body">
						<div style="width: 250px; display: inline-table">
							<label for="from">From</label><input type="text"
								class="form-control" id="from" value="2014-11-20" />
						</div>
						<div style="width: 250px; display: inline-table">
							<label for="to">To</label><input type="text" class="form-control"
								id="to" value="2014-11-20" />
						</div>
						<div style="width: 150px; display: inline-table">
							<br />
							<button type="submit" class="btn btn-default"
								style="margin-top: 5px;">Query</button>
						</div>
						<div style="min-width: 310px; height: 400px; margin: 0 auto"
							id="container" data-highcharts-chart="10"></div>
						<div style="min-width: 310px; height: 400px; margin: 0 auto"
							id="containerPercentage" data-highcharts-chart="10"></div>
					</div>
					<div class="panel-footer"></div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		//&lt;![CDATA[ 
		function renderChart(dataArray) {
			var chart1 = new Highcharts.Chart({
				//$('#container').highcharts({
				chart : {
					type : 'line',
					renderTo : 'container'
				},
				title : {
					text : '近七天数据'
				},
				subtitle : {
					text : new Date().getUTCDate()
				},
				xAxis : {
					title : {
						text : 'Term'
					},
					tickInterval : 1,
					celling : 100,
					min : 0,
					max : 79

				},
				yAxis : {
					title : {
						text : 'Hit Number'
					},
					tickInterval : 1,
					min : 3,
					max : 18

				},
				plotOptions : {
					line : {
						dataLabels : {
							enabled : true
						},
						enableMouseTracking : false
					}
				},
				series : JSON.parse(JSON.parse(dataArray)),
			});
		};
		
		function renderPercentageChart(dataArray) {
			var chart1 = new Highcharts.Chart({
				//$('#container').highcharts({
				chart : {
					type : 'line',
					renderTo : 'containerPercentage'
				},
				title : {
					text : '预测百分比'
				},
				subtitle : {
					text : new Date().getUTCDate()
				},
				xAxis : {
					title : {
						text : 'Term'
					},
					tickInterval : 1,
					celling : 100,
					min : 0,
					max : 79

				},
				yAxis : {
					title : {
						text : 'Hit Number'
					},
					tickInterval : 1,
					min : 3,
					max : 18

				},
				plotOptions : {
					line : {
						dataLabels : {
							enabled : true
						},
						enableMouseTracking : false
					}
				},
				series : JSON.parse(JSON.parse(dataArray)),
			});
		};

		$(document).ready(function() {
			$.ajax({
				url : 'show3',
				type : 'get',
				success : function(response) {
					renderChart(JSON.parse(response).data);
				},
				error : function(err) {
					alert(err.responseText);
				}
			});
			$.ajax({
				url : 'showPercentage',
				type : 'get',
				success : function(response) {
					renderPercentageChart(JSON.parse(response).data);
				},
				error : function(err) {
					alert(err.responseText);
				}
			});
		});

		/**
		 $.ajax({
		 url: 'api/v1/dashboard/month_mention_graphic',
		 type: "GET",
		 dataType: "json",
		 data : {username : "demo"},
		 success: function(data) {
		 chart.addSeries({
		 name: "mentions",
		 data: data.month_mentions_graphic
		 });
		 },
		 cache: false
		 });*/
		//]]&gt;
	</script>
</body>
</html>