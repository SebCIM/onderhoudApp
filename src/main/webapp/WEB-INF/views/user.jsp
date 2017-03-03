<%@include file='default.jsp'%>
	<h1><a href="/onderhoudApp/users">Back</a></h1>
	<h1>Onderhoudsaannemer aanpassen</h1>
	<c:url var="editAction" value="/user/edit"></c:url>

	<form:form action="${editAction}" commandName="edituser">
		<table>
		<tr>
				<td><form:label path="id">
						<spring:message text="id" />
					</form:label></td>
				<td><form:input path="id" /></td>
			</tr>
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
					value="<spring:message text="Aanpassen"/>" /></td>
			</tr>
		</table>
	</form:form>

	<h3>Users List</h3>
	<c:if test="${!empty listUsers}">
		<table class="tg">
			<tr>
				<th width="80">ID</th>
				<th width="120">Contactpersoon</th>
				<th width="120">Bedrijf</th>
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
<%@include file='footer.jsp'%>