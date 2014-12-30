<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>

<c:if test="${not empty region}">
    <h2>Temps de la comarca: ${region}</h2>

    <a href="/">Torna a la llista de comarques.</a>
</c:if>

</body>
</html>
