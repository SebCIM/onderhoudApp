<%@include file='default.jsp'%>
<div class="login">
	<h1>Identificatie Code</h1>
	<form action="checkLogin" method="post">
		<input type="text" name="txtToken" /><input
			type="submit" class="login-btn" value="Login" />
	</form>
	</div>
<%@include file='footer.jsp'%>