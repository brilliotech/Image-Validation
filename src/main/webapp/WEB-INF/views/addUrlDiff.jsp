<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>
<head>
		<title>PFG Image Validation</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
			<link href="<c:url value="/css/crawled.css" />" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	</head>
	<body>
		<% String jobNameReq = request.getParameter("jobName");
		 HttpSession sessionOne = request.getSession();
		 sessionOne.setAttribute("jobName", jobNameReq);%>
		<div id="ajaxDiv" style="position: fixed; width: 1260px; height: 500px; margin: 20% 0 0 50%; left: -630px; top: -250px; z-index: 10; display: none;">
			<h2 style="position: fixed; width: 600px; left: -300px; text-align: center; margin: 15% 0% 0% 50%; color: #3c763d;"></h2>
			<h6 style="position: fixed; width: 800px; left: -400px; text-align: center; margin: 18% 0% 0% 50%;">You will be automatically redirected to <b>Jobs</b> page.</h6>
			<img src="<c:url value="/img/1.gif" />" style="position: fixed; width: 40px; left: -20px; margin: 22% 0% 0% 50%;" />
		</div>
		<div class="container-fluid mainD">
			<div class="headerD"><h1>Image Validation Platform</h1></div>
			<div class="container bodyD">
				<div class="bodyLeftD back-gray">
					<ul>
						<li><a href="CreateJob"> Create New Job</a></li>
						<li><a href="Jobs"><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span> Jobs</a></li>
						<li><a href="Reports"> Reports</a></li>
					</ul>
				</div>	<!-- Body Left Closes -->
				<div class="bodyRightD">
					<!-- ***** Crawled Urls page ***** -->
					<div class="alert alert-success" role="alert" style="text-align: center; width: 100%;"><h5>Crawled URLs for Job - <%= jobNameReq%></h5></div>
					<div class="jobsD">	
						<div style="float: left; width: 94%;">
							<form:form action="executeUrlDiffBaseline" method="post" modelAttribute="basicUrlsfromDB" >
								<table id="jobsTable" class="table-hover">
									<tr class="back-gray warning">
										<td>URL</td>
										<td>Domain</td>		
										<td>Tags</td>
										<td><form:hidden path="jobName" /></td>
									</tr>
									<c:forEach items="${basicUrlsfromDB.basicUrls}" var="basicURLInfo" varStatus="i">
						                <tr>
						                    <td class="urlName"><form:input type="text" path="basicUrls[${i.index}].basicUrl"/></td>
						                    <td class="domain"><form:input type="text" path="basicUrls[${i.index}].domain" /></td>          
						                    <td class="tag"><form:input type="text" path="basicUrls[${i.index}].tag"/></td>
						                </tr>
						            </c:forEach>
									<!-- Dynamic Contents Goes Here -->
								</table>
								<div style="margin-top: 20px; float: right;">
									<input type="submit" id="screenShotButton" value="Take Screenshot" class="btn btn-success" />
									<input type="reset" id="" value="Reset" class="btn btn-danger" />
								</div>
							</form:form>
						</div>
						<div style="float: right; width: 5%;">
							<input type="button" id="addUrlButton" value="+" class="btn btn-primary"/>
						</div>
					</div>	<!-- Crawled Data Div Closes -->
				</div>	<!-- Body Right Closes  -->
			</div>	<!-- Container Div Closes -->
		</div> <!-- Main Div - The Entire Body Closes -->
		<p style="position: fixed; text-align: center; width: 400px; bottom: 0; left: -200px; margin-left: 50%;">© Copyright 2015 Brillio. All Rights Reserved.</p>
		<script type="text/javascript">
			$(document).ready(function() {
				var jobName = '<%= jobNameReq%>';
				console.log('Job Name in JS:: '+jobName);
				/* $.get( "crawledUrlsInfo?jobName="+jobName, function( data ) {
					for (var i=0; i<data.length; i++) {
						console.log(data[i].url);
						$('#jobsTable tbody tr').last().after('<tr class="jobData"><td>'+(i+1)+'</td><td><input type="checkbox" name="ckeckbox" value="'+(i)+'"/></td><td><input class="noBorder" name="basicUrl" type="text" value="'+data[i].url+'"/></td><td><input name="domain" type="text" /></td><td><input name="tag" type="text" /></td></tr>');	
					}
					console.log(data);
				}); */
				$('#addUrlButton').click(function(){
					var count = $('.urlName').size();
					$('#jobsTable tbody tr').last().after('<tr><td class="urlName"><input type="text" name="basicUrls['+count+'].basicUrl" /></td><td class="domain"><input type="text" name="basicUrls['+count+'].domain" /></td><td class="tag"><input type="text" name="basicUrls['+count+'].tag" /></td></tr>');	
				});
				
				$('#screenShotButton').click(function() {
					$('.mainD').css('opacity',0);
					$('#ajaxDiv').show();
					$('#ajaxDiv h2').text('Screenshot Capture in progress.');
				});
			});
		</script>
	</body>
</html>