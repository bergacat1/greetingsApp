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
<div align="center">
<h1 >Prediccio del temps</h1>
<h2>Llista de comarques</h2>

<p>Podeu consultar el temps del següent dia en la comarca que desitjeu.</p>
    <c:if test="${not empty regions}">
        <c:forEach var="region" items="${regions}">
            <p></p><a href="/regions/${region}">${region}</a></p>
        </c:forEach>
    </c:if>
<a href="/">Torna a l'inici</a>
</div>
</body>
</html>