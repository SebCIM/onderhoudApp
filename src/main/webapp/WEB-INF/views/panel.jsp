<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<title>user Page</title>
</head>
<body>
<h1>
	Welcome, ${user.username}
</h1>
<p>Reparatie verzoeken indienen.</p>
<ul>
	<li>
		Home
	</li>
	<li>
		Invoeren
	</li>
	<c:if test="${user.isAdmin != true}">
		<li>
			<a href="users">Gebruikers</a>
		</li>
	</c:if>
	<li>
		Printen
	</li>
	<li>
		<a href="logout">Loguit</a>
	</li>
</ul>
</body>
</html>