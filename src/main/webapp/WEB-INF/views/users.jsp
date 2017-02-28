<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<title>user Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
</head>
<body>
	<h1><a href="panel">Back</a></h1>
	<h1>Onderhoudsaannemer toevoegen</h1>
	<c:url var="addAction" value="/user/add"></c:url>

	<form:form action="${addAction}" commandName="user">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Lastname:</td>
				<td><input type="text" name="lastname" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td>Token:</td>
				<td><input type="text" name="token" /></td>
			</tr>
			<tr>
				<td>isAdmin:</td>
				<td><input type="text" name="isAdmin" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message text="Toevoegen"/>" /></td>
			</tr>
		</table>
	</form:form>

	<h3>Onderhoudsaannemers</h3>
	<c:if test="${!empty listUsers}">
		<table class="tg">
			<tr>
				<th width="80">User ID</th>
				<th width="120">Username</th>
				<th width="120">Lastname</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${listUsers}" var="person">
				<c:if test="${not person.isAdmin}">
					<tr>
						<td>${person.id}</td>
						<td>${person.username}</td>
						<td>${person.lastname}</td>
						<td><a href="<c:url value='user/edit/${person.id}' />">Edit</a></td>
						<td><a href="<c:url value='user/remove/${person.id}' />">Delete</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>

	</c:if>
</body>
</html>