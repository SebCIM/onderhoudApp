<%@include file='default.jsp'%>
<h1>Reparatie Doorgeven</h1>
<c:url var="addAction" value="/repair/add"></c:url>

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
			<td><select>
					<c:forEach items="${ListDistrict}" var="district">
						<option value="${district.getDistrictGebiedAfkorting()}">${district.getDistrictGebiedAfkorting()}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Rijkswegen:</td>
			<td><select>
					<c:forEach items="${ListWegen}" var="weg">
						<option value="${weg.getAanduiding()}">${weg.getWegnummer()}, ${weg.getAanduiding()}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Hectometerbord:</td>
			<td><input type="text" value="" size="10"/></td>
		</tr>
		<tr>
			<td>Baan:</td>
			<td><select>
					<c:forEach items="${ListBaan}" var="baan">
						<option value="${baan.getBaanNaam()}">${baan.getBaanNaam()}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Strook:</td>
			<td><select>
					<c:forEach items="${ListStrook}" var="strook">
						<option value="${strook.getStrook()}">${strook.getStrook()}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message text="Doorgeven"/>" /></td>
		</tr>
	</table>
</form:form>
<%@include file='footer.jsp'%>