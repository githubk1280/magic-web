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
						<div style="width: 250px;">
							<label for="from">Crawling...</label><input type="text"
								class="form-control" id="crawlVal" value="2014 11 20 1" disabled />
						</div>
						<div>
							<br />
							<button type="submit" class="btn btn-primary" id="crawl"
								style="margin-top: 5px;">Verify</button>
						</div>
						<hr />
						<div style="width: 250px;">
							<label for="from">Trend...</label><input type="text"
								class="form-control" id="trendVal" value="2014 11 20 1" disabled />
						</div>
						<div>
							<br />
							<button type="submit" class="btn btn-primary" id="trend"
								style="margin-top: 5px;">Verify</button>
						</div>
						<hr />
						<div style="width: 250px;">
							<label for="from">Term...</label><input type="text"
								class="form-control" id="termVal" value="2014 11 20 1" disabled />
						</div>
						<div>
							<br />
							<button type="submit" class="btn btn-primary" id="term"
								style="margin-top: 5px;">Verify</button>
						</div>
						<hr />
						<div style="width: 250px;">
							<label for="from">Percentage...</label><input type="text"
								class="form-control" id="percetBtnVal" value="2014 11 20 1"
								disabled />
						</div>
						<div>
							<br />
							<button type="submit" class="btn btn-primary" id="percetBtn"
								style="margin-top: 5px;">Verify</button>
						</div>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>Count</th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="panel-footer"></div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			 $('button').each(function(index,val){
				 var id = val.id;
				 $(val).bind('click',function(ent){ 
					 $.ajax({
				      url: 'verify/'+ent.target.id,
				      type: 'get',
				      success: function(response) {
				        if (JSON.parse(response).success == true) {
				          var result = JSON.parse(JSON.parse(response).data);
				          $('.table tbody tr').remove();
				          $(result).each(function(index,val){
				        	  if(index %2 == 0){
				        		  $('.table').append('<tr><td>'+val.count+'</td><td>'+formatTime(val.hitTime)+'</td></tr>');
				        	  }else{
				        		  $('.table').append('<tr class="success"><td>'+val.count+'</td><td>'+formatTime(val.hitTime)+'</td></tr>');
				        	  }
				          });
				        } else {
				          alert(JSON.parse(response).data);
				        }
				      },
				      error: function(err) {
				        alert("Failed please re-try \n " + err.status + " " + err.statusText);
				      }
				    });});
			 });
			  $('#crawl').on('click',
			  function() {
			  });
			  $('.nav li').each(function(index, val) {
			    $(val).removeClass("active");
			    $('.fetchverify').addClass("active");
			  });
			 function formatTime(longTimes) {
					var d = new Date(longTimes);
					var result = "";
					result += d.getFullYear();
					result += "-";
					result += (d.getMonth()+1);
					result += "-";
					result += d.getDate() > 10 ? d.getDate() : "0" + d.getDate();
					result += " ";
					result += d.getHours() > 10 ? d.getHours() : "0" + d.getHours();
					result += ":";
					result += d.getMinutes() > 10 ? d.getMinutes() : "0" + d.getMinutes();
					result += ":";
					result += d.getSeconds() > 10 ? d.getSeconds() : "0" + d.getSeconds();
					return result;
				};
				
			  function clickHandle (id){
				 
			  };
			});
	</script>
</body>
</html>