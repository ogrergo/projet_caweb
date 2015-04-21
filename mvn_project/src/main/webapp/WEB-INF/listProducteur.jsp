<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      <h1>Liste Producteurs</h1>
    </jsp:attribute>
    <jsp:body>
        <h1>Liste des producteurs dans la base de données</h1>
    <center>
        <table border="1">
            <caption>Liste des producteurs</caption>
            <tr>
                <th>idProducteur</th>
                <th>email</th>
            </tr>
            <c:forEach items="${producteurs}" var="producteur">
                <tr>
                    <td>>${producteur.idProducteur}</td>
                    <td>${producteur.email}</td>
                </tr>
            </c:forEach>
        </table>
    </center>
    </jsp:body>
</tag:base>

