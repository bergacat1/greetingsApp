<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>

<p>Podeu consultar el temps del següent dia en la comarca que desitjeu.</p>
<c:if test="${user == null}">
    <p>Benvingut al sistema d'alertes de WeatherCat.
        S'ha creat el vostre usuari, a partir d'ara podreu crear alertes per rebre notificacions per correu
        quan es doni una determinada situació meteorologica en la comarca que seleccioneu.</p>
</c:if>
<c:if test="${user != null}">
    <h1>Usuari: ${user.getUsername()}</h1>
    <h2>Llista d'alertes</h2>
    <ul>
        <c:if test="${!user.alerts.isEmpty()}">
            <c:forEach var="alert" items="${user.alerts}">
                <li>Comarca: ${alert.getRegion()} Temps: ${alert.getWeather()} Estat: ${alert.getEnabledState()}
                    <form method="POST" action="/users/${user.getUsername()}/${alert.getId()}">
                        <input type="hidden" name="id" value=${alert.getId()}>
                        <input type="submit" name="enable_disable" value="Activar/Desactivar">
                        <input type="submit" name="delete" value="Eliminar">
                    </form></li>
            </c:forEach>
        </c:if>
    </ul>
</c:if>
<form method="POST" action="/users/${user.getUsername()}" >
    <table>
        <tr>
            <td><label>Email:</label></td>
            <td><input name="email" value="${user.getEmail()}" readonly="true"/> </td>

            <td><select name="region">
                <option value="Alt Camp">Alt Camp</option>
                <option value="Alt Emporda">Alt Emporda</option>
                <option value="Alt Penedes">Alt Penedes</option>
                <option value="Alt Urgell"/>Alt Urgell</option>
                <option value="Alta Ribagorça">Alta Ribagorça</option>
                <option value="Anoia">Anoia</option>
                <option value="Bages">Bages</option>
                <option value="Baix Camp">Baix Camp</option>
                <option value="Baix Ebre">Baix Ebre</option>
                <option value="Baix Emporda">Baix Emporda</option>
                <option value="Baix Llobregat">Baix Llobregat</option>
                <option value="Baix Penedes">Baix Penedes</option>
                <option value="Barcelones">Barcelones</option>
                <option value="Bergueda">Bergueda</option>
                <option value="Cerdanya">Cerdanya</option>
                <option value="Conca Barbera">Conca de Barbera</option>
                <option value="Garraf">Garraf</option>
                <option value="Garrigues">Garrigues</option>
                <option value="Garrotxa">Garrotxa</option>
                <option value="Girones">Girones</option>
                <option value="Maresme">Maresme</option>
                <option value="Montsia">Montsia</option>
                <option value="Noguera">Noguera</option>
                <option value="Osona">Osona</option>
                <option value="Pallars Jussa">Pallars Jussa</option>
                <option value="Pallars Sobira">Pallars Sobira</option>
                <option value="Pla Estany">Pla Estany</option>
                <option value="Pla Urgell">Pla Urgell</option>
                <option value="Priorat">Priorat</option>
                <option value="Ribera Ebre">Ribera d'Ebre</option>
                <option value="Ripolles">Ripolles</option>
                <option value="Segarra">Segarra</option>
                <option value="Segria">Segria</option>
                <option value="Selva">Selva</option>
                <option value="Solsones">Solsones</option>
                <option value="Tarragones">Tarragones</option>
                <option value="Terra Alta">Terra Alta</option>
                <option value="Urgell">Urgell</option>
                <option value="Vall Aran">Val Aran</option>
                <option value="Valles Occidental">Valles Occidental</option>
                <option value="Valles Oriental">Valles Oriental</option>
            </select></td>
            <td><select name="weather">
                <option value="Nubol">Nubol</option>
                <option value="Boira">Boira</option>
                <option value="Pluja">Pluja</option>
                <option value="Neu">Neu</option>
                <option value="Tempesta de neu">Tempesta de neu</option>
                <option value="Sol">Sol</option>
                <option value="Sol i nubol">Sol i Nubol</option>
                <option value="Tempesta">Tempesta</option>
            </select></td>
        </tr>
        <tr>
            <td><input type="submit" value="Afegeix l'alerta" /></td>
        </tr>
    </table>
</form>

<a href="/">Torna a l'inici </a>
</body>
</html>