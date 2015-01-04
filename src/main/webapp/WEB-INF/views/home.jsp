<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<body>
<h1>WeatherCat</h1>
<h2>La web meteorologgica de Catalunya</h2>

<h3>Prediccio del temps</h3>
<p>Podeu consultar el temps del següent dia en la comarca que desitjeu.</p>
    <a href="/regions">Consultar</a>

<h3>Alertes meteorologiques</h3>
<p>Aqui podreu gestionar les vostres alertes</p>
<a href="/users">Gestionar</a>
</body>
</html>