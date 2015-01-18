<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
<div align="center">

<c:if test="${not region.isEmpty()}">
    <h1>Temps de la comarca: ${region.get(0)}</h1>
    <p> El temps es: ${region.get(1)}</p>
    <img src=${region.get(2)} width="420" height="420" />
    <p><a href="/regions">Torna a la llista de comarques.</a></p>
    <p><a href="/">Torna a l'inici</a></p>
</c:if>
</div>
</body>
</html>
