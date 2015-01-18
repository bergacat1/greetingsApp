<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<div align="center">


<h1>Alertes meteorologiques</h1>
<p>Aqui podreu gestionar les vostres alertes</p>
<form method="POST" action="/users">
    <label>Username:</label>
    <input type="text" name="username"/>
    <label>Email:</label>
    <input type="text" name="email"/>
    <input type="submit" value="Entra"/>
</form>

<p><a href="/">Torna a l'inici </a></p>
</div>
</body>
</html>