<%@include file='default.jsp'%>
<div class="login">
	<h4>${error}</h4>
	<h3>Identificatie Code</h3>
	<form action="checkLogin" method="post">
		<input type="text" name="txtToken" /><input
			type="submit" class="login-btn" value="Login" />
	</form>
	</div>
<%@include file='footer.jsp'%>