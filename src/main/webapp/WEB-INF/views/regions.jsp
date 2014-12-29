<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<body>
<h2>Greetings List</h2>
    <ul>
    <c:if test="${not empty regions}">
        <c:forEach var="region" items="${regions}">
        <li>${region}</li>
        </c:forEach>
    </c:if>
    </ul>
    <p><a href="greetings/form">Add</a></p>
</body>
</html>