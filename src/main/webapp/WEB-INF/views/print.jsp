<%@include file='default.jsp'%>
<form:form action="${filterAction}" method="GET" commandName="filter">
	<table>
		<tr>
			<c:if test="${user.isAdmin}">
				<td class="filterText">Aannemer:</td>
				<td class="filterDropDown"><select name="aannemer">
						<option value="0">Alle</option>
						<c:forEach items="${listAannemers}" var="aannemer">
							<c:choose>
								<c:when test="${filterAannemer == aannemer.getId()}">
									<option value="${aannemer.getId()}" selected>${aannemer.getBedrijf()}</option>
								</c:when>
								<c:otherwise>
									<option value="${aannemer.getId()}">${aannemer.getBedrijf()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
			</c:if>
			<td class="filterText">Van:</td>
			<td>
				<div class='input-group date' id='datetimepicker1'>
					<input type='text' name="start" class="form-control" value="<c:if test="${filterEindDatum != null}">${filterStartDatum}</c:if>"/> <span
						class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"> </span>
					</span>
				</div> <script type="text/javascript">
					$(function() {
						$('#datetimepicker1').datetimepicker({
							viewMode : 'years',
							format : "YYYY-MM-DD"
						});
					});
				</script>
			</td>
			<td class="filterText">Tot:</td>
			<td>
				<div class='input-group date' id='datetimepicker2'>
					<input type='text' name="eind" class="form-control" value="<c:if test="${filterEindDatum != null}">${filterEindDatum}</c:if>" /> <span
						class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"> </span>
					</span>
				</div> <script type="text/javascript">
					$(function() {
						$('#datetimepicker2').datetimepicker({
							viewMode : 'years',
							format : "YYYY-MM-DD"
						});
					});
				</script>
			</td>
		</tr>
		<tr>
			<td class="filterText">District:</td>
			<td class="filterDropDown"><select name="district">
					<option value="0">Alle</option>
					<c:forEach items="${listDistricten}" var="district">
						<c:choose>
							<c:when test="${filterDistrict == district.getId()}">
								<option value="${district.getId()}" selected>${district.getDistrictGebiedAfkorting()}</option>
							</c:when>
							<c:otherwise>
								<option value="${district.getId()}">${district.getDistrictGebiedAfkorting()}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></td>
			<td class="filterText"><input type="submit" value="Filter"
				name="filter"></td>
		</tr>
	</table>
</form:form>
<h3>Reparaties</h3>
<c:if test="${!empty listReparaties}">

	<table class="tg">
		<tr>
			<th width="80">Onderhoudsaannemer</th>
			<th width="30">District</th>
			<th width="30">Rijksweg</th>
			<th width="30">Hectometer</th>
			<th width="30">Baan</th>
			<th width="30">Strook</th>
			<th width="150">Schade d.d</th>
			<th width="150">Opmerking</th>
		</tr>
		<c:forEach items="${listReparaties}" var="reparatie">
			<c:if
				test="${filterDistrict == reparatie.getReparatie().getDistrict().getId()}">
				<c:if
					test="${reparatie.getApuser().getId() == user.id || user.isAdmin}">
					<tr>
						<td>${reparatie.getApuser().getBedrijf()}</td>
						<td>${reparatie.getReparatie().getDistrict().getDistrictGebiedAfkorting()}</td>
						<td>${reparatie.getReparatie().getWegenlijst().getAanduiding()}</td>
						<td>${reparatie.getReparatie().getHectometerbord()}</td>
						<td>${reparatie.getReparatie().getBaan().getBaanNaam()}</td>
						<td>${reparatie.getReparatie().getStrook().getStrook()}</td>
						<td>${reparatie.getReparatie().getConstatering()}</td>
						<td>${reparatie.getReparatie().getOpmerking()}</td>
					</tr>
				</c:if>
			</c:if>
			<c:if test="${filterDistrict == 0}">
				<c:if
					test="${reparatie.getApuser().getId() == user.id || user.isAdmin}">
					<tr>
						<td>${reparatie.getApuser().getBedrijf()}</td>
						<td>${reparatie.getReparatie().getDistrict().getDistrictGebiedAfkorting()}</td>
						<td>${reparatie.getReparatie().getWegenlijst().getAanduiding()}</td>
						<td>${reparatie.getReparatie().getHectometerbord()}</td>
						<td>${reparatie.getReparatie().getBaan().getBaanNaam()}</td>
						<td>${reparatie.getReparatie().getStrook().getStrook()}</td>
						<td>${reparatie.getReparatie().getConstatering()}</td>
						<td>${reparatie.getReparatie().getOpmerking()}</td>
					</tr>
				</c:if>
			</c:if>
		</c:forEach>
	</table>

</c:if>
<%@include file='footer.jsp'%>