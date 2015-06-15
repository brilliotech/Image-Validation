<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html >
<html>
<head>
<title>PFG Image Validation</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="<c:url value="/css/config.css" />" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<%
		String error = "";
	%>
	<%-- <img src="<spring:url value="/localImg/1.gif" />" /> --%>
	<div class="container-fluid mainD">
		<div class="headerD">
			<img src="<spring:url value="/img/pfglogo.png" />" style="position: fixed; width: 90px; left: 95px"/>		
			<h1>Image Validation Platform</h1>
		</div>
		<div class="container bodyD">
			<div class="bodyLeftD well">
				<ul>
					<li><a href="CreateJob" id="goToCreateJob"><span
							class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
							Create New Job</a></li>
					<li><a href="Jobs" id="goToJobs"> Jobs</a></li>
					<li><a href="Reports" id="goToReports"> Reports</a></li>
				</ul>
			</div>
			<div class="bodyRightD">
				<div class="panel panel-default">
					<div class="panel-heading"
						style="background-color: #008A8A; border-color: #008A8A;">
						<h3 class="panel-title">Configure Your Job</h3>
					</div>
					<div class="panel-body">
						<form:form action="newJob" method="post"
							modelAttribute="jobDetails">
							<div id="configD">
								<table id="formTable">
									<tr class="item">
										<td>Job Name</td>
										<td><form:input path="job_Name" type="text" id="job_Name"
												name="job_Name" /><span id="jobCheckIcon" class=""
											aria-hidden="true" data-toggle="tooltip"
											data-placement="right" title=""></span></td>
									</tr>
									<tr class="item">
										<td>Job Type</td>
										<td><form:select class="jobType" path="job_type">
												<option value="siteDiff">Site Diff</option>
												<option value="urlDiff">URL Diff</option>
											</form:select></td>
									</tr>
									<tr class="item">
										<td id="base_url_label">Base URL</td>
										<td><form:input path="base_url" type="text" id="base_url"
												name="base_url" /></td>
									</tr>
									<tr class="item">
										<td id="crawl_level_label">Crawl Level</td>
										<td><form:input path="crawl_level" type="number"
												id="crawl_level" name="crawl_level" min="0" max="1"
												style="text-align: center;" /></td>
									</tr>

									<tr class="item">
										<td>Threshold [%]</td>
										<td><form:input path="threshold" type="number"
												id="threshold" name="threshold" min="0" max="100"
												style="text-align: center;" /></td>
									</tr>
									<tr class="item">
										<td>Browser Type</td>
										<td><form:select path="userAgent">
												<option value="Chrome">Chrome</option>
												<option value="Firefox">Mozilla Firefox</option>
												<option value="ie">Internet Explorer</option>
												<option value="Safari">Safari</option>
											</form:select></td>
									</tr>
								</table>
							</div>
							<div style="margin-top: 20px; float: right;">
								<input type="submit" id="screenShotButton" value="Save"
									class="btn btn-default"
									style="background-color: #008A8A; border-color: #008A8A; color: white;" />
								<input type="reset" id="screenShotButton" value="Reset"
									class="btn btn-danger" />
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<p style="position: fixed; text-align: center; width: 400px; bottom: 0; left: -200px; margin-left: 50%;">© Copyright 2015 Brillio. All Rights Reserved.</p>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							/* Not allowing user to enter CRAWL LEVEL and BASE URL in URLDiff Mode. */
							$('form')
									.delegate(
											'.jobType',
											'change',
											function(event) {
												var my_this = $(this);
												if ($(this).find(':selected')
														.val() === 'urlDiff') {
													$('#base_url, #crawl_level')
															.prop('disabled',
																	true);
													$(
															'#base_url_label, #crawl_level_label, #base_url, #crawl_level')
															.css('opacity', 0.5);
												} else {
													$('#base_url, #crawl_level')
															.prop('disabled',
																	false);
													$(
															'#base_url_label, #crawl_level_label, #base_url, #crawl_level')
															.css('opacity', 1);
												}
												event.preventDefault();
											});

							/* Runtime acknowlegement of Job-Name Availibility. */
							$('#job_Name')
									.blur(
											function() {
												console.log($(this).val());
												$
														.get(
																"checkJob?jobName="
																		+ ($(this)
																				.val()),
																function(result) {
																	console
																			.log(result);
																	if (result == false)
																		$(
																				'#jobCheckIcon')
																				.removeClass(
																						'glyphicon glyphicon-remove-sign')
																				.addClass(
																						'glyphicon glyphicon-ok-sign')
																				.attr(
																						'title',
																						'Job Name is Valid.')
																				.css(
																						'color',
																						'green');
																	else
																		$(
																				'#jobCheckIcon')
																				.removeClass(
																						'glyphicon glyphicon-ok-sign')
																				.addClass(
																						'glyphicon glyphicon-remove-sign')
																				.attr(
																						'title',
																						'Job Name is already present.')
																				.css(
																						'color',
																						'red');
																});
											});

						});
	</script>
</body>
</html>