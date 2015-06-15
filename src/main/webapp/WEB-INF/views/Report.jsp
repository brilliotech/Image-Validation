<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Report Page</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link href="<c:url value="/css/report.css" />" rel="stylesheet" type="text/css"/>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	</head>
	<body style="font-size: 13px;">
		<div id="imageGallery" style="position: fixed; width: 1032px; height: 500px; margin: 20% 0 0 50%; left: -512px; top: -250px; z-index: 10; box-shadow: 1px 1px 15px 1px #888888; display: none;">
			<div style="width: 100%; height: 20px;"><a><span class="glyphicon glyphicon-remove-circle close" aria-hidden="true" style="float: right;"></span></a></div>
			<div>
				<div style="float: left; text-align: center; padding: 0 2px 0 2px;">
					<h5 style="font-weight: bold; color: #337ab7;">Actual Image</h5>
					<a id="actualImg" href="<spring:url value="" />" target="_blank"><img src="<spring:url value="" />" style="height: 430px; width: 340px;"/> </a>
				</div>
				<div style="float: left; text-align: center; padding: 0 2px 0 2px;">
					<h5 style="font-weight: bold; color: #337ab7;">Difference Image</h5>
					<a id="diffImg" href="<spring:url value="" />" target="_blank"><img src="<spring:url value="" />" style="height: 430px; width: 340px;"/> </a>
				</div>
				<div id="expImage" style="float: left; text-align: center; padding: 0 2px 0 2px;">
					<h5 style="font-weight: bold; color: #337ab7;">Expected Image</h5>
					<a id="expectedImg" href="<spring:url value="" />" target="_blank"><img src="<spring:url value="" />" style="height: 430px; width: 340px;"/> </a>
				</div>
			</div>
		</div>
		
		<div class="container-fluid mainD">
			<div class="headerD"><h1>Image Validation Platform</h1></div>
			<div class="container-fluid bodyD">
				<div class="bodyLeftD well">
					<ul>
						<li><a href="CreateJob" id="goToCreateJob"> Create New Job</a></li>
						<li><a href="Jobs" id="goToJobs"> Jobs</a></li>
						<li><a href="Reports" id="goToReports"><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span> Reports</a></li>
					</ul>
				</div>
				<div class="bodyRightD">
					 <div class="jobListD">	
						<div>
							<table id="jobListTable" class="table-hover">
								<tr class="back-gray">
									<td>Available Jobs</td>
								</tr>
								<tr></tr>
								<!-- Dynamic Contents Goes Here -->
							</table>
						</div>
					</div>	<!-- Crawled Data Div Closes -->
					<div class="buildListD">	
						<div>
							<table id="buildListTable" class="table-hover">
								<tr class="back-gray">
									<td>Executed Builds</td>
								</tr>
								<tr></tr>
								<!-- Dynamic Contents Goes Here -->
							</table>
						</div>
					</div>	<!-- Crawled Data Div Closes -->
					<div class="imageListD">	
						<div>
							<!-- <table id="imageListTable" class="table-hover">
								<tr class="back-gray">
									<td>Execution Results</td>
								</tr>
							</table> -->
							<table id="imageListDeatailsTable" class="table-hover">								
								<tr class="back-gray">
									<td>URL</td>
									<td>Images</td>
									<td>Threshold [%]</td>
									<td>Match [%]</td>
									<td>Status</td>
								</tr>
								<!-- Dynamic Contents Goes Here -->
							</table>
						</div>
					</div>	<!-- Crawled Data Div Closes -->
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			$(document).ready(function() {
				
				/* ** Updating All Jobs Available ** */
				$.get( "allJobs", function( data ) {
					for (var i=0; i<data.jobDetails.length; i++) {
						$('#jobListTable tbody tr').last().after('<tr class="jobData back-yellow active"><td class="job">'+data.jobDetails[i].name+'</td></tr>');
					}					
				});
				
				/* ** Updating All build of a job Available ** */
				$('#jobListTable').delegate('td.job','click', function(event) {
					event.preventDefault();
					var jobName = $(this).text();
					$('#jobListTable td.job').css('color','black').css('font-weight','normal');
					$(this).css('color','green').css('font-weight','bold');
					
					$.get( "allCompareBuilds?jobname="+jobName, function( data ) {
						$('#buildListTable tr.buildData, #imageListDeatailsTable tr.resultData').remove();
						for (var i=0; i<data.buildDetails.length; i++) {
							$('#buildListTable tr').last().after('<tr class="buildData back-blue active"><td id="'+jobName+'$'+data.buildDetails[i].name+'" class="build">'+data.buildDetails[i].name+'</td></tr>');
						}					
						console.log(data);
					});
				});
				
				/* ** Updating All ImageResults of a build of a job Available ** */
				$('#buildListTable').delegate('td.build','click', function(event) {
					event.preventDefault();
					var my_id = $(this).attr('id');
					var buildName = $(this).text();
					var jobName = my_id.split('$')[0];
					
					$('#buildListTable td.build').css('color','black').css('font-weight','normal');
					$(this).css('color','green').css('font-weight','bold');
					
					$.get( "allImageResults?buildName="+buildName+"&jobName="+jobName, function( data ) {
						console.log(data);
						$('#imageListDeatailsTable tr.resultData').remove();
						for (var i=0; i<data.length; i++) {
							if (data[i].status === 'PASS')
								$('#imageListDeatailsTable tr').last().after('<tr class="resultData back-green active"><td class="left">'+data[i].url_name+'</td><td><span id="'+my_id+'$'+data[i].url_name+'" class="glyphicon glyphicon-camera open" aria-hidden="true"></span></td><td>'+data[i].threshold+'</td><td>'+data[i].diffPercent+'</td><td>'+data[i].status+'</td></tr>');
							else
								$('#imageListDeatailsTable tr').last().after('<tr class="resultData back-red active"><td class="left">'+data[i].url_name+'</td><td><span id="'+my_id+'$'+data[i].url_name+'" class="glyphicon glyphicon-camera open" aria-hidden="true"></span></td><td>'+data[i].threshold+'</td><td>'+data[i].diffPercent+'</td><td>'+data[i].status+'</td></tr>');
						}
						
						$('#imageListDeatailsTable').delegate('tr .open','click', function(event) {
							event.preventDefault();
							var my_id1 = $(this).attr('id');
							
							var jobName = my_id1.split("$")[0];
							var buildName = my_id1.split("$")[1];
							var urlName = my_id1.split("$")[2];
							
							for (var i=0; i<data.length; i++) {
								if(data[i].url_name == urlName){
									$('#actualImg').attr('href','/Image-Validation/localImg'+data[i].actualImage);
									$('#actualImg img').attr('src','/Image-Validation/localImg'+data[i].actualImage);
									$('#diffImg').attr('href','/Image-Validation/localImg'+data[i].difference);
									$('#diffImg img').attr('src','/Image-Validation/localImg'+data[i].difference);
									$('#expectedImg').attr('href','/Image-Validation/localImg'+data[i].expectedImage);
									$('#expectedImg img').attr('src','/Image-Validation/localImg'+data[i].expectedImage);
								}
							}
							$('.mainD').css('opacity', 0);
							$('#imageGallery').slideDown(300);
						});
					});
				});
								
				$('#imageGallery div .close').click(function(){
					$('#imageGallery').slideUp(300);
					$('.mainD').css('opacity', 1);
				});
				
			});
		</script>
	</body>
</html>