<%@include file='default.jsp'%>
<h3>Reparaties</h3>
<c:if test="${!empty listReparaties}">
	<table class="tg">
		<tr>
			<th width="20">ID</th>
			<th width="120">Wegnummer</th>
			<th width="120">Status</th>
			<th width="200">Reparatiedatum</th>
			<th width="200">Reparatiemethode</th>
		</tr>
		<c:forEach items="${listReparaties}" var="reparatie">
			<c:if test="${reparatie.getApuser().getId() == user.id}">
				<tr>
					<td>${reparatie.id}</td>
					<td>${reparatie.getReparatie().getWegenlijst().getWegnummer()}</td>
					<td>${reparatie.getReparatie().getStatus()}</td>
					<td>${reparatie.getReparatie().getReparatiedatum()}</td>
					<td>${reparatie.getReparatie().getReparatiemethoden().getNaam()}</td>
					<td><a
						href="<c:url value='/vorstschade/bekijken/${reparatie.id}' />">Inzien</a></td>
					<td><a href="<c:url value='/vorstschade/verwijderen/${reparatie.id}' />" class="confirm" data-text="Weet je zeker dat je de vorstschade van ${reparatie.getReparatie().getConstatering()} wilt verwijderen?">Verwijderen</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>

</c:if>
<%@include file='footer.jsp'%>