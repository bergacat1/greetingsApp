<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>


<h1>Alertes meteorologiques</h1>
<p>Aqui podreu gestionar les vostres alertes</p>
<form method="POST" action="/users">
    <label>Username:</label>
    <input type="text" username="username"/>
    <label>Email:</label>
    <input type="text" username="email"/>
    <input type="submit" value="Entra"/>
</form>

<a href="/">Torna a l'inici </a>
</body>
</html>