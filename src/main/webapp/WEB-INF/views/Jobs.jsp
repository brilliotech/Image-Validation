<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  isELIgnored="false"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>

	<head>
		<title>PFG Image Validation</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<link href="<c:url value="/css/jobs.css" />" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	</head>
	<body>
		<!-- Page visualizing Ajax operation -->
		<div id="ajaxDiv" style="position: fixed; width: 1260px; height: 500px; margin: 20% 0 0 50%; left: -630px; top: -250px; z-index: 10; display: none;">
			<h2 style="position: fixed; width: 800px; left: -400px; text-align: center; margin: 15% 0% 0% 50%; color: #337ab7;">Crawling in progress...</h2>
			<h6 style="position: fixed; width: 800px; left: -400px; text-align: center; margin: 18% 0% 0% 50%;">You will be automatically redirected to <b>Crawled URLs</b> page for selection.</h6>
			<img src="<c:url value="/img/1.gif" />" style="position: fixed; width: 40px; left: -20px; margin: 20% 0% 0% 50%;" />
		</div>
		<!-- ------------------------------- -->
		<div class="container-fluid mainD">
			<div class="headerD"><h1>Image Validation Platform</h1></div>
			<div class="container-fluid bodyD">
				<div class="bodyLeftD back-gray">
					<ul>
						<li><a href="CreateJob" id="goToCreateJob"> Create New Job</a></li>
						<li><a href="Jobs" id="goToJobs"><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span> Jobs</a></li>
						<li><a href="Reports" id="goToReports"> Reports</a></li>
					</ul>
				</div>	<!-- Body Left Closes -->
				<div class="bodyRightD">
					<!-- <div id="banner" class="alert alert-success hidden" role="alert" style="text-align: center;">
						<h4>All available Jobs</h4>    <a href="crawledUrls" class="hidden">Go To Crawled URL List Page</a>
					</div> -->
					<!-- ***** All Jobs Page ***** -->		
					<div class="jobsD">	
						<!-- <div class="back-gray" style="width: 100px; padding: 5px 0 5px 0; text-align: center;"><a id="refresh">Refresh</a></div> -->
						<div>
							<table id="jobsTable" class="table-hover">
								<tr class="back-gray">
									<td>Job Name</td>
									<td>Job Created</td>		
									<td>Execution Type</td>
									<td>Available Baseline</td>
									<td>Execute</td>
								</tr>
								<!-- Dynamic Contents Goes Here -->
							</table>
						</div>
					</div>	<!-- Crawled Data Div Closes -->
				</div>	<!-- Body Right Closes  -->
			</div>	<!-- Container Div Closes -->
		</div> <!-- Main Div - The Entire Body Closes -->
		
		<script type="text/javascript">
			$(document).ready(function() {

				var updateAllJobList = function(){
					/* Dynamically updating all the jobs */
					$.get( "allJobs", function( data ) {
						for (var i=0; i<data.jobDetails.length; i++) {
							$('#jobsTable tbody tr').last().after('<tr class="jobData"><td><a href="">'+data.jobDetails[i].name+'</a></td><td>'+data.jobDetails[i].time+'</td><td><select class="executionType"><option>Baseline</option><option>Compare</option></select></td><td></td><td><span class="glyphicon glyphicon-log-in btn" aria-hidden="true"></td></tr>');	
						}
						
						/* Getting Job-Name on Click of Execution-icon */
						$('#jobsTable').delegate('tr .btn','click', function(event) {
							event.preventDefault();
							var jobName = $(this).closest('tr').find('td:eq(0)').text();
							var executionType = $(this).closest('tr').find('td:eq(2) :selected').text();
							var baselineBuildName = $(this).closest('tr').find('td:eq(3) :selected').text();
							var jobType;
							
							
							for (var i=0; i<data.jobDetails.length; i++) {
								if(data.jobDetails[i].name == jobName && data.jobDetails[i].jobType == 'siteDiff')
									jobType = data.jobDetails[i].jobType;
								else if(data.jobDetails[i].name == jobName && data.jobDetails[i].jobType == 'urlDiff')
									jobType = 'urlDiff';
							}
							
							if(executionType === 'Baseline'){
							/* BASELINE Operation */	
								if(jobType === 'siteDiff'){
									/* SITE-Diff Functionality Implemented here.*/
									$.ajax({url:"crawlAndSave",
										type: "POST",
										data: "jobName="+jobName,
										beforeSend: function(){
											$('.mainD').css('opacity',0);
											$('#ajaxDiv').show();
											$('#ajaxDiv h2').text('Crawling in progress.');
										},
										success: function(data) {
											console.log(data);
											window.location.replace('crawledUrls?jobName='+data);
										}
									});
								}
								else{
									/* URL-Diff Functionality Implemented here.*/
									/* If URL Diff redirecting to another page for adding URLs manually. */
									window.location.replace('urlDiffExecute?jobName='+jobName);
								}
							}
							else{
							/* COMPARE Operation */	
								$.ajax({url:"executeCompare",
									type: "POST",
									data: "jobName="+jobName+"&baselineBuildName="+baselineBuildName,
									beforeSend: function(){
										$('.mainD').css('opacity',0);
										$('#ajaxDiv').show();
										$('#ajaxDiv h2').text('Screenshot Comparison in progress.');
										$('#ajaxDiv h6').text('You will be automatically redirected to Reports page.');
									},
									success: function(data) {
										if(data =='true')
										window.location.replace('Reports');
										else
											{
											alert(data);
										  window.location.replace('Jobs');
											}
									}
								});
							}
						});
					});	
				}();
				
				/* Refresh function disabled currently. */
				/* $('#refresh').click(function() {
					console.log('Refresh Clicked.');
					updateAllJobList();
				}); */
				
				/* Mode selection and conditioned. */
				$('#jobsTable').delegate('.executionType','change', function(event) {
					var my_this = $(this);
			        if ($(this).find(':selected').val() === 'Compare') {
			        	var jobName = $(this).closest('tr').find('td:eq(0)').text();
			        	
			            /*  Needs ajax call for list of all baselined builds */
			            $.get( "allBaseLineBuildsByJobName?jobName="+jobName, function( data ) {
			            	if (data.buildNames != null) {
			            		$(my_this).closest('tr').find('td:eq(3)').text('').append('<select class="builds"></select>');
				            	for (var i=0; i<data.buildNames.length; i++){
				            		$(my_this).closest('tr').find('td:eq(3) .builds').append('<option>'+data.buildNames[i]+'</option>');	
				            	}
			            	}
			            	else {
			            		$(my_this).closest('tr').find('td:eq(3)').text('No Baseline Builds Available!!');
			            	}
						});			            
			        } else {
			            $(this).closest('tr').find('td:eq(3)').text('-');
			        }
			        event.preventDefault();
			    });

				/*  Getting the Job-Name on Click  */
				/*$('#jobsTable .jobData :nth-child(2)').on('click', function(event) {
					event.preventDefault();
					 Inserts just beneath the Clicked Item 
					$(this).closest('tr').after('<div>Hello</div>').show(100);
					alert($(this).text());
				});*/

			});
		</script>
	</body>
</html>