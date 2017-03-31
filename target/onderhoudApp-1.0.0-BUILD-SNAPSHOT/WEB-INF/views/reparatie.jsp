<%@include file='default.jsp'%>
<h1>
	<a href="/onderhoudApp/vorstschade/overzicht">Terug</a>
</h1>
<h3>Reparatie ${reparatie.getReparatie().getDatumtijd()}</h3>
<table class="tg">
	<tr>
		<th width="120">Reparatie Nr</th>
		<td>${reparatie.getId()}</td>
	</tr>
	<tr>
		<th width="120">Contactpersoon</th>
		<td>${reparatie.getApuser().getUsername()}</td>
	</tr>
	<tr>
		<th width="120">Soort</th>
		<td>${reparatie.getReparatie().getSoort()}</td>
	</tr>
	<tr>
		<th width="120">District</th>
		<td>${reparatie.getReparatie().getDistrict()}</td>
	</tr>
	<tr>
		<th width="120">Rijksweg</th>
		<td>${reparatie.getReparatie().getWegenlijst().getAanduiding()}</td>
	</tr>
	<tr>
		<th width="60">Hectometerbord</th>
		<td>${reparatie.getReparatie().getHectometerbord()}</td>
	</tr>
	<c:if test="${reparatie.getApuser().getId() == user.id}">
		<tr>
			<th width="30">Aanpassen</th>
			<td><a href="<c:url value='vorstschade/aanpassen/${reparatie.id}' />">Aanpassen</a></td>
		</tr>
		<tr>
			<th width="30">Verwijderen</th>
			<td><a href="<c:url value='vorstschade/verwijderen/${reparatie.id}' />"
				class="confirm"
				data-text="Weet je zeker dat je ${reparatie.getApuser().getUsername()} wilt verwijderen?">Verwijderen</a></td>
		</tr>
	</c:if>
</table>
<%@include file='footer.jsp'%>