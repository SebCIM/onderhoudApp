<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="/onderhoudApp/resources/assets/css/main.css" />
<link rel="stylesheet"
	href="/onderhoudApp/resources/assets/css/custom.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="/onderhoudApp/resources/assets/js/jquery.confirm.min.js"
	type="text/javascript"></script>
<script src="/onderhoudApp/resources/assets/js/main.js"
	type="text/javascript"></script>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<!-- Latest jQuery, Moment and Bootstrap + Datepicker -->
<link rel="stylesheet" type="text/css" media="screen"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<script async="" src="//www.google-analytics.com/analytics.js"></script>

<script type="text/javascript"
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script
	src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
<link
	href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css"
	rel="stylesheet">

<title>${title}</title>
</head>
<body>
	<div class="site-outer-wrapper">
		<div class="site-container">
			<header role="banner" class="site-header"> <nav> <c:if
				test="${!empty user}">Welkom ${user.username},
						<a href="/onderhoudApp/loguit" title="Visit our English website">Uitloggen</a>
			</c:if> </nav> <a href="" class="logo"> <!-- <img
				src="/onderhoudApp/resources/images/logo.png" width="234"
				height="72"
				alt="Rijkswaterstaat Ministerie van Infrastructuur en Milieu. Water. Wegen. Werken." />  -->
			</a> </header>
			<div class="title">
				<div class="page-title">
					<h2>Vorstschade</h2>
				</div>
			</div>
		</div>
		<div role="navigation" class="site-nav">
			<div class="container">
				<div id="nav-inpage" class="nav-inpage"></div>
				<nav id="nav" class="nav-main">
				<ul>
					<li class="main-item "><a href="/onderhoudApp">Home</a></li>
					<li class="main-item "><a href="/onderhoudApp/vorstschade/invoeren">Invoeren vorstschade</a>
					<li class="main-item "><a href="/onderhoudApp/vorstschade/overzicht">Invoeren reparatie</a>
						<c:if test="${user.isAdmin}">
							<li class="main-item "><a href="/onderhoudApp/gebruikers">Gebruikers</a>
							</li>
						</c:if>
					<li class="main-item "><a href="/onderhoudApp/printen">Meldingen overzicht</a></li>
				</ul>
				<ul class="quicklinks">
					<li><a href="/over-ons/contact/index.aspx">Contact</a></li>
					<li><a href="/english/index.aspx"
						title="Visit our English website">Uitloggen</a></li>
				</ul>
				</nav>
			</div>
		</div>
		<div class="content-container">