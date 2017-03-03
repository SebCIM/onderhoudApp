<%@include file='default.jsp'%>
	<h1>Reparatie Doorgeven</h1>
	<c:url var="addAction" value="/repair/add"></c:url>

	<form:form action="${addAction}" commandName="repair">
		<table>
			<tr>
				<td>Voorselectie:</td>
				<td><select>
					  <option value="rafeling">Rafeling</option>
					  <option value="gaten">Gaten</option>
					  <option value="opennaden">Open naden</option>
					</select>
					</td>
			</tr>
			<tr>
				<td>District Code</td>
				<td><select>
					  <c:forEach items="${list}" var="item">
					  <option value="${item}">${item}</option>
					</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Rijkswegen:</td>
				<td><select>
					  <c:forEach items="${list}" var="item">
					  <option value="${item}">${item}</option>
					</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Hectometerbord:</td>
				<td><select>
					<c:forEach items="${list}" var="item">
					  <option value="${item}">${item}</option>
					</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Baan:</td>
				<td><select>
					<c:forEach items="${list}" var="item">
					  <option value="${item}">${item}</option>
					</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td>Strook:</td>
				<td><select>
					<c:forEach items="${list}" var="item">
					  <option value="${item}">${item}</option>
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