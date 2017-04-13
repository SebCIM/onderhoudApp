<%@include file='default.jsp'%>
<h1>
	<a href="/onderhoudApp/vorstschade/overzicht">Terug</a>
</h1>
<h3>Vorstschade ${reparatie.getReparatie().getDatumtijd()}</h3>
<c:url var="editAction" value="/vorstschade/aanpassen"></c:url>
<form:form action="${editAction}" commandName="reparatie">
	<table>
		<tr class="hidden">
			<td>Id:</td>
			<td><input type="text" name="Id"
				value="${reparatie.getReparatie().getId()}" /></td>
		</tr>
		<tr>
			<td>Contactpersoon:</td>
			<td>${reparatie.getApuser().getUsername()}</td>
		</tr>
		<tr>
			<td>Soort:</td>
			<td>${reparatie.getReparatie().getSoort()}</td>
		</tr>
		<tr>
			<td class="filterText">District:</td>
			<td class="filterDropDown"><select name="districtId">
					<option value="0">Alle</option>
					<c:forEach items="${listDistricten}" var="district">
						<c:choose>
							<c:when
								test="${reparatie.getReparatie().getDistrict().getId() == district.getId()}">
								<option value="${district.getId()}" selected>${district.getDistrictCode()}, ${district.getDistrictGebiedAfkorting()}, ${district.getDistrictNaam()}</option>
							</c:when>
							<c:otherwise>
								<option value="${district.getId()}">${district.getDistrictCode()}, ${district.getDistrictGebiedAfkorting()}, ${district.getDistrictNaam()}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Wegnummer:</td>
			<td class="filterDropDown"><select name="rijksweg">
					<option value="0">Alle</option>
					<c:forEach items="${ListWegen}" var="weg">
						<c:choose>
							<c:when
								test="${reparatie.getReparatie().getWegenlijst().getId() == weg.getId()}">
								<option value="${weg.getId()}" selected>${weg.getWegnummer()},
									${weg.getAanduiding()}</option>
							</c:when>
							<c:otherwise>
								<option value="${weg.getId()}">${weg.getWegnummer()},
									${weg.getAanduiding()}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></td>
		</tr>
		<c:choose>
			<c:when
				test="${reparatie.getReparatie().getHectometerbordEind() != ''}">
				<tr>
					<td>Hectometerbord Begin:</td>
					<td><input type="text" name="hectometerbordBegin"
						value="${reparatie.getReparatie().getHectometerbordBegin()}" /></td>
				</tr>
				<tr>
					<td>Hectometerbord Eind:</td>
					<td><input type="text" name="hectometerbordEind"
						value="${reparatie.getReparatie().getHectometerbordEind()}" /></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>Hectometerbord:</td>
					<td><input type="text" name="hectometerbordBegin"
						value="${reparatie.getReparatie().getHectometerbordBegin()}" /></td>
				</tr>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when
				test="${reparatie.getReparatie().getReparatiedatum() != null}">
				<tr>
					<td>Status:</td>
					<td><select name="status" id="status">
							<option value="In onderhoudsplanning">In
								onderhoudsplanning</option>
							<option value="Gerepareerd" selected>Gerepareerd</option>
					</select></td>
				</tr>
				<tr>
					<td>Datum reparatie:</td>
					<td>
						<div class='input-group date' id='datetimepicker9'>
							<input type='text' name="reparatiedatum" class="form-control" value="${reparatie.getReparatie().getReparatiedatum()}" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"> </span>
							</span>
						</div> <script type="text/javascript">
							$(function() {
								$('#datetimepicker9').datetimepicker({
									viewMode : 'years',
									format : "YYYY-MM-DD HH:mm:SS"
								});
							});
						</script>
					</td>
				</tr>
				<tr>
					<td>Reparatie Methode:</td>
					<td class="filterDropDown"><select name="reparatiemethode">
					<option value="0">Alle</option>	
					<c:forEach items="${listMethoden}" var="methode">
						<c:choose>
							<c:when
								test="${reparatie.getReparatie().getReparatiemethoden().getId() == methode.getId()}">
								<option value="${methode.getId()}" selected>${methode.getNaam()}</option>
							</c:when>
							<c:otherwise>
								<option value="${methode.getId()}">${methode.getNaam()}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>Status:</td>
					<td><select name="status" id="status">
							<option value="In onderhoudsplanning">In
								onderhoudsplanning</option>
							<option value="Gerepareerd">Gerepareerd</option>
					</select></td>
				</tr>
				<tr class="rdatum">
					<td>Datum reparatie:</td>
					<td>
						<div class='input-group date' id='datetimepicker9'>
							<input type='text' name="reparatiedatum" class="form-control" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"> </span>
							</span>
						</div> <script type="text/javascript">
							$(function() {
								$('#datetimepicker9').datetimepicker({
									viewMode : 'years',
									format : "YYYY-MM-DD HH:mm:SS"
								});
							});
						</script>
					</td>
				</tr>
				<tr class="rmethode">
					<td>Reparatie Methode:</td>
					<td><select name="reparatiemethode">
							<c:forEach items="${listMethoden}" var="methode">
								<option value="${methode.getId()}">${methode.getNaam()}</option>
							</c:forEach>
					</select></td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<td>Opmerking:</td>
			<td><textarea name="opmerking" rows="4" cols="50">${reparatie.getReparatie().getOpmerking()}</textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message text="Aanpassen"/>" /></td>
		</tr>
		<c:if test="${reparatie.getApuser().getId() == user.id}">
			<tr>
				<th width="30">Verwijderen</th>
				<td><a
					href="<c:url value='vorstschade/verwijderen/${reparatie.id}' />"
					class="confirm"
					data-text="Weet je zeker dat je ${reparatie.getApuser().getUsername()} wilt verwijderen?">Verwijderen</a></td>
			</tr>
		</c:if>
	</table>
</form:form>
<%@include file='footer.jsp'%>