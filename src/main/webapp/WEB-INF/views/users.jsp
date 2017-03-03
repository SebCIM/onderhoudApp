<%@include file='default.jsp'%>
	<h1>Onderhoudsaannemer toevoegen</h1>
	<c:url var="addAction" value="/user/add"></c:url>

	<form:form action="${addAction}" commandName="user">
		<table>
			<tr>
				<td>Contactpersoon:</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Bedrijf:</td>
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