<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>

<c:if test="${not region.isEmpty()}">
    <h2>Temps de la comarca: ${region.get(0)}</h2>
    <p> El temps es: ${region.get(1)}</p>
    <img src=${region.get(2)} width="420" height="420" />
    <a href="/">Torna a la llista de comarques.</a>
</c:if>

</body>
</html>
