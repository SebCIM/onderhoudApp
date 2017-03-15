<%@include file='default.jsp'%>
	<h3>Reparaties</h3>
	<c:if test="${!empty listReparaties}">
		<table class="tg">
			<tr>
				<th width="20">ID</th>
				<th width="120">Onderhoudsaannemer</th>
				<th width="200">Reparatie</th>
				<th width="50"></th>
				<th width="50"></th>
			</tr>
			<c:forEach items="${listReparaties}" var="reparatie">
				<%-- <c:if test="${reparatie.apuserid == user.id}"> --%>
					<tr>
						<td>${reparatie.id}</td>
						<td>${reparatie.getApuser().getUsername()}</td>
						<td>${reparatie.getReparatie().getDatumtijd()}</td>
						<td><a href="<c:url value='user/edit/${reparatie.id}' />">Aanpassen</a></td>
						<td><a href="<c:url value='user/remove/${reparatie.id}' />" class="confirm"  data-text="Weet je zeker dat je ${reparatie.id} wilt verwijderen?" >Verwijderen</a></td>
					</tr>
				<%-- </c:if> --%>
			</c:forEach>
		</table>

	</c:if>
<%@include file='footer.jsp'%>