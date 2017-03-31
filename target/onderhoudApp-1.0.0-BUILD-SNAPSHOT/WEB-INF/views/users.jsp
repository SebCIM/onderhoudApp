<%@include file='default.jsp'%>
	<h3>Onderhoudsaannemer toevoegen</h3>
	<c:url var="addAction" value="/gebruiker/toevoegen"></c:url>

	<form:form action="${addAction}" commandName="user">
		<table>
			<tr>
				<td>Contactpersoon:</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Bedrijf:</td>
				<td><input type="text" name="bedrijf" /></td>
			</tr>
			<tr>
				<td>Token:</td>
				<td><input class="token" type="text" name="token" /><div class="generateToken">Genereer</div></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Telefoon nummer:</td>
				<td><input type="text" name="tel" /></td>
			</tr>
			<tr>
				<td>District:</td>
				<td><input type="text" name="district" /></td>
			</tr>
			<tr>
				<td>Admin:</td>
				<td><input type="checkbox" name="isAdmin" value="isAdmin"></td>
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
						<td><a href="<c:url value='gebruiker/aanpassen/${person.id}' />">Aanpassen</a></td>
						<td><a href="<c:url value='gebruiker/verwijderen/${person.id}' />" class="confirm"  data-text="Weet je zeker dat je ${person.username} van ${person.bedrijf} wilt verwijderen?" >Verwijderen</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>

	</c:if>
<%@include file='footer.jsp'%>