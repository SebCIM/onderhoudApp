<%@include file='default.jsp'%>
<h1>Reparatie Doorgeven</h1>
<c:url var="addAction" value="/vorstschade/toevoegen"></c:url>

<form:form action="${addAction}" commandName="addrepair">
	<table>
		<tr>
			<td>Voorselectie:</td>
			<td><select name="soort">
					<c:forEach items="${soortList}" var="soortItem">
						<option value="${soortItem}">${soortItem}</option>
					</c:forEach>
			</select></td>
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
		<tr>
			<td>Hectometerbord:</td>
			<td><input name="hectometerbord" type="text" value="" size="10" /></td>
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
							format :"YYYY-MM-DD HH:mm:SS"
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