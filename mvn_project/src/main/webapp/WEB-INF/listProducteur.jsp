<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tag:base>
    <jsp:attribute name="header">
      Liste Producteurs
    </jsp:attribute>
    <jsp:body>
        <h1>Liste des producteurs dans la base de données</h1>
    <center>
        <table border="1">
            <caption>Liste des producteurs</caption>
            <tr>
                <th>email</th>
            </tr>
            <c:forEach items="${producteurs}" var="producteur">
                <tr>
                    <td>${producteur.email} ${producteur.mdp}</td>
                </tr>
            </c:forEach>
        </table>
    </center>
    </jsp:body>
</tag:base>

