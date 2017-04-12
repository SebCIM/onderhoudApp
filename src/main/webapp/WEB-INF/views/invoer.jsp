<%@include file='default.jsp'%>
<h1>Reparatie Doorgeven</h1>
<c:url var="addAction" value="/vorstschade/toevoegen"></c:url>

<form:form action="${addAction}" commandName="addrepair">
	<table>
		<tr>
			<td>Voorselectie:</td>
			<td><c:forEach items="${soortList}" var="soortItem">
					<c:choose>
						<c:when test="${soortItem == 'Open naden'}">
							<div class="distance">
								<input type="checkbox" id="Naden" name="soort"
									value="${soortItem}" /> ${soortItem}
							</div>
						</c:when>
						<c:otherwise>
							<div class="distance">
								<input type="checkbox" id="${soortItem}" name="soort"
									value="${soortItem}" /> ${soortItem}
							</div>
						</c:otherwise>
					</c:choose>

				</c:forEach></td>
			<td>
		</tr>
		<tr>
			<td>District Code</td>
			<td><select name="districtId">
					<c:forEach items="${ListDistrict}" var="district">
						<option value="${district.getId()}">${district.getDistrictGebiedAfkorting()}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Rijkswegen:</td>
			<td><select name="WegenlijstId">
					<c:forEach items="${ListWegen}" var="weg">
						<option value="${weg.getId()}">${weg.getWegnummer()},
							${weg.getAanduiding()}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr class="begin">
			<td>Hectometerbord Begin:</td>
			<td><input name="hectometerbordBegin" type="text" value="" size="10" /></td>
		</tr>
		<tr class="eind">
			<td>Hectometerbord Eind:</td>
			<td><input name="hectometerbordEind" type="text" value="" size="10" /></td>
		</tr>
		<tr class="gaten">
			<td>Aantal Gaten:</td>
			<td><input name="aantalgaten" type="text" value="" size="10" /></td>
		</tr>
		<tr class="mrafeling">
			<td>Aantal Meters Rafeling:</td>
			<td><input name="metersrafeling" type="text" value="" size="10" /></td>
		</tr>
		<tr class="mnaden">
			<td>Aantal Meters Naden:</td>
			<td><input name="metersnaden" type="text" value="" size="10" /></td>
		</tr>
		<tr>
			<td>Baan:</td>
			<td><select name="baanId">
					<c:forEach items="${ListBaan}" var="baan">
						<option value="${baan.getId()}">${baan.getBaanNaam()}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Strook:</td>
			<td><select name="strookId">
					<c:forEach items="${ListStrook}" var="strook">
						<option value="${strook.getId()}">${strook.getStrook()}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Datum Constatering:</td>
			<td>
				<div class='input-group date' id='datetimepicker9'>
					<input type='text' name="constatering" class="form-control" /> <span
						class="input-group-addon"> <span
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
			<td>Opmerking:</td>
			<td><textarea name="opmerking" rows="4" cols="50"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message text="Doorgeven"/>" /></td>
		</tr>
	</table>
</form:form>
<%@include file='footer.jsp'%>