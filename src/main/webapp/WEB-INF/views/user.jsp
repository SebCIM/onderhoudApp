<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
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
	<c:if test="${!empty user}">
		<table class="tg">
			<tr>
				<td>Userid: ${user.id}</td>
				<td>Username: <input type="text" value="${user.username}" /></td>
				<td>Lastname: <input type="text" value="${user.lastname}" /></td>
				<td>Password: <input type="text" value="${user.password}" /></td>
				<td>Token: <input type="text" value="${user.token}" /></td>
			</tr>
		</table>
	</c:if>
	<h1>Add a User</h1>
	<c:url var="addAction" value="/user/add"></c:url>

	<form:form action="${addAction}" commandName="user">
		<table>
			<tr>
				<td><form:label path="username">
						<spring:message text="username" />
					</form:label></td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td><form:label path="lastname">
						<spring:message text="lastname" />
					</form:label></td>
				<td><form:input path="lastname" /></td>
			</tr>
			<tr>
				<td><form:label path="password">
						<spring:message text="password" />
					</form:label></td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td><form:label path="token">
						<spring:message text="token" />
					</form:label></td>
				<td><form:input path="token" /></td>
			</tr>
			<tr>
				<td><form:label path="isAdmin">
						<spring:message text="isAdmin" />
					</form:label></td>
				<td><form:input path="isAdmin" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message text="Add User"/>" /></td>
			</tr>
		</table>
	</form:form>

	<h3>Users List</h3>
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
				<tr>
					<td>${person.id}</td>
					<td>${person.username}</td>
					<td>${person.lastname}</td>
					<td><a href="<c:url value='/edit/${person.id}' />">Edit</a></td>
					<td><a href="<c:url value='/remove/${person.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>