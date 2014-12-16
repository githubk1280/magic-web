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
						<h3 class="panel-title">Verify</h3>
					</div>
					<div class="panel-body">
						<div>
							<br />
							<button type="submit" class="btn btn-primary" id="percetBtn"
								style="margin-top: 5px;">Verify</button>
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(
				function() {
					$.ajax({
						url : 'verifytest/json',
						type : 'post',
						dataType:'json',
						contentType:"application/json",
						data: JSON.stringify({name:'test',data:[1,2,3]}),
						success : function(response) {
							alert(JSON.parse(response));
						},
						error : function(err) {
							alert("Failed please re-try /n" + err.status + " "
									+ err.statusText);
						}
					});
				});
	</script>
</body>
</html>