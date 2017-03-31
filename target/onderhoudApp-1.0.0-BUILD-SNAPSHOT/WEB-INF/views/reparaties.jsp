<%@include file='default.jsp'%>
<h3>Reparaties</h3>
<c:if test="${!empty listReparaties}">
	<table class="tg">
		<tr>
			<th width="20">ID</th>
			<th width="120">Onderhoudsaannemer</th>
			<th width="200">Reparatie datum</th>
			<th width="200">Opmerking</th>
			<th width="50"></th>
		</tr>
		<c:forEach items="${listReparaties}" var="reparatie">
			<c:if test="${reparatie.getApuser().getId() == user.id}">
				<tr>
					<td>${reparatie.id}</td>
					<td>${reparatie.getApuser().getBedrijf()}</td>
					<td>${reparatie.getReparatie().getConstatering()}</td>
					<td>${reparatie.getReparatie().getOpmerking()}</td>
					<td><a
						href="<c:url value='/vorstschade/bekijken/${reparatie.id}' />">Inzien</a></td>
					<td><a href="<c:url value='/vorstschade/verwijderen/${reparatie.id}' />" class="confirm" data-text="Weet je zeker dat je ${reparatie.id} wilt verwijderen?">Verwijderen</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>

</c:if>
<%@include file='footer.jsp'%>