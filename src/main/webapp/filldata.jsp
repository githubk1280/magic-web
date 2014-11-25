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
						<h3 class="panel-title">Fetch</h3>
					</div>
					<div class="panel-body">
						<div style="width: 250px; ">
							<label for="from">Crawling...</label><input type="text"
								class="form-control" id="crawlVal" value="2014 11 20 1" />
						</div>
						<div>
							<br />
							<button type="submit" class="btn btn-primary" id="crawl"
								style="margin-top: 5px;">Fetch</button>
						</div>
						<hr/>
						<div style="width: 250px; ">
							<label for="from">Trend...</label><input type="text"
								class="form-control" id="trendVal" value="2014 11 20 1" />
						</div>
						<div>
							<br />
							<button type="submit" class="btn btn-primary" id="trend"
								style="margin-top: 5px;">Fetch</button>
						</div>
						<hr/>
						<div style="width: 250px; ">
							<label for="from">Term...</label><input type="text"
								class="form-control" id="termVal" value="2014 11 20 1" />
						</div>
						<div>
							<br />
							<button type="submit" class="btn btn-primary" id="term"
								style="margin-top: 5px;">Fetch</button>
						</div>
						<hr/>
						<div style="width: 250px; ">
							<label for="from">Percentage...</label><input type="text"
								class="form-control" id="percetBtnVal" value="2014 11 20 1" />
						</div>
						<div>
							<br />
							<button type="submit" class="btn btn-primary" id="percetBtn"
								style="margin-top: 5px;">Fetch</button>
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		//&lt;![CDATA[ 

		$(document).ready(function() {
			$('#crawl').on('click',function(){
				var val = $('#crawlVal').val();
				$.ajax({
					url : 'crawl/'+val,
					type : 'get',
					success : function(response) {
						var resArray = JSON.parse(JSON.parse(JSON.parse(response).data));
						renderPercentageChart(resArray);
					},
					error : function(err) {
						alert(err.responseText);
					}
				});
			});		
			$('.nav li').each(function(index,val){
				$(val).removeClass("active");
				$('.fetch').addClass("active");
			});
			});	

		//]]&gt;
	</script>
</body>
</html>