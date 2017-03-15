<%@include file='default.jsp'%>
	<h1><a href="/onderhoudApp/users">Back</a></h1>
	<h3>Onderhoudsaannemer aanpassen</h3>
	<c:url var="editAction" value="/user/edit"></c:url>

	<form:form action="${editAction}" commandName="edituser">
		<table>
			<tr class="hidden">
				<td>Id:</td>
				<td><form:input path="Id" /></td>
			</tr>
				<td>Gebruiker:</td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td>Bedrijf:</td>
				<td><form:input path="bedrijf" /></td>
			</tr>
			<tr>
				<td>Token:</td>
				<td><form:input path="token" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Telefoon nummer:</td>
				<td><form:input path="tel" /></td>
			</tr>
			<tr>
				<td>District:</td>
				<td><form:input path="district" /></td>
			</tr>
			<tr>
				<td>Admin:</td>
				<td><form:checkbox path="isAdmin" /></td>
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
				<th width="20">ID</th>
				<th width="120">Contactpersoon</th>
				<th width="120">Bedrijf</th>
				<th width="120">Email</th>
				<th width="120">Telefoon nummer</th>
				<th width="60">District</th>
				<th width="30">Aanpassen</th>
				<th width="30">Verwijderen</th>
			</tr>
			<c:forEach items="${listUsers}" var="person">
				<c:if test="${not person.isAdmin}">
					<tr>
						<td>${person.id}</td>
						<td>${person.username}</td>
						<td>${person.bedrijf}</td>
						<td>${person.email}</td>
						<td>${person.tel}</td>
						<td>${person.district}</td>
						<td><a href="<c:url value='user/edit/${person.id}' />">Aanpassen</a></td>
						<td><a href="<c:url value='user/remove/${person.id}' />" class="confirm"  data-text="Weet je zeker dat je ${person.username} van ${person.bedrijf} wilt verwijderen?" >Verwijderen</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>
<%@include file='footer.jsp'%>