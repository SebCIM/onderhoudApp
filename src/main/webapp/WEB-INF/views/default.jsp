<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>${title}</title>
</head>
<body>
	<div class="site-outer-wrapper">
		<div class="site-container">
			<header role="banner" class="site-header"> <nav> <c:if
				test="${!empty user}">Welkom ${user.username},
						<a href="/onderhoudApp/logout" title="Visit our English website">Uitloggen</a>
			</c:if> </nav> <a href="" class="logo"><img
				src="/onderhoudApp/resources/images/logo.png" width="234"
				height="72"
				alt="Rijkswaterstaat Ministerie van Infrastructuur en Milieu. Water. Wegen. Werken." /></a>
			</header>
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
					<li class="main-item "><a href="/onderhoudApp/repair">Invoeren</a>
					<li class="main-item "><a href="/onderhoudApp/overzicht">Overzicht</a>
						<c:if test="${user.isAdmin}">
							<li class="main-item "><a href="/onderhoudApp/users">Gebruikers</a>
							</li>
						</c:if>
					<li class="main-item "><a href="#">Printen</a></li>
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