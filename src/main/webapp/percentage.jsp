<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>Magic-percentage</title>
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
				<%@ include file="nav"%>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Percentage</h3>
					</div>
					<div class="panel-body">
						<div style="width: 250px; display: inline-table">
							<label for="from">Target</label><input type="text"
								class="form-control" id="target" value="3" />
						</div>
						<div style="width: 150px; display: inline-table">
							<br />
							<button type="submit" class="btn btn-default" id="queryTarget"
								style="margin-top: 5px;">Query</button>
						</div>
						<div style="min-width: 310px; height: 400px; margin: 0 auto;display:none"
							id="containerPercentage" data-highcharts-chart="10"></div>
					</div>
					<div class="panel-footer"></div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		//&lt;![CDATA[ 

		function renderPercentageChart(dataArray) {
			var target = dataArray.target;
			var chart1 = new Highcharts.Chart({
				//$('#container').highcharts({
				chart : {
					type : 'line',
					renderTo : 'containerPercentage'+target,
				},
				title : {
					text : target,
				},
				xAxis : {
					title : {
						text : 'Hit Numbers',
					},
					tickInterval : 1,
					celling : 100,
					min : 0,
					max : 18,

				},
				yAxis : {
					title : {
						text : 'Percentage * 100'
					},
					min : 0,
// 					max : 100

				},
				plotOptions : {
					line : {
						dataLabels : {
							enabled : true
						},
						enableMouseTracking : false
					}
				},
				series : dataArray.composites,
			});
		};

		$(document).ready(function() {
			$('#queryTarget').on('click',function(){
				var target = $('#target').val();
				$.ajax({
					url : 'showPercentage/'+target,
					type : 'get',
					success : function(response) {
						var resArray = JSON.parse(JSON.parse(JSON.parse(response).data));
						var currSVG = $('#containerPercentage');
						var cloneSVG = currSVG.clone();
						cloneSVG.attr('id','containerPercentage'+target);
						cloneSVG.css('display','block'); 
// 						currSVG.prepend(cloneSVG);
						currSVG.parent().append(cloneSVG);
						renderPercentageChart(resArray);
					},
					error : function(err) {
						alert(err.responseText);
					}
				});
			});		
			$('.nav li').each(function(index,val){
				$(val).removeClass("active");
				$('.percentage').addClass("active");
			});
			});	

		//]]&gt;
	</script>
</body>
</html>