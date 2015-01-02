<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<script type="text/javascript">
    function submitForm(action) {
        document.getElementById('form1').action = action;
        document.getElementById('form1').submit();
    }
</script>
<body>
<h2>Llista de comarques</h2>

<p>Podeu consultar el temps del següent dia en la comarca que desitjeu.</p>
<ul>
    <c:if test="${not empty regions}">
        <c:forEach var="region" items="${regions}">
            <li><a href="/${region}">${region}</a></li>
        </c:forEach>
    </c:if>
</ul>
<h2>Alertes</h2>

<p>Aqui podreu gestionar les vostres alertes</p>
<form method="GET" action="/user">
    <label>Email:</label>
    <input type="text" name="email"/>
    <input type="submit" value="Entra"/>
</form>
</body>
</html>