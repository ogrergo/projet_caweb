<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tag:base>
    <jsp:attribute name="header">
        <h1>Ma liste de production</h1>
    </jsp:attribute>
    <jsp:body>
        <h1>Ma liste de production</h1>
        <center>
            <table border="1">
                <tr>
                    <th>Produit</th>
                    <th>Unité(s)</th>
                    <th>durée</th>
                </tr>
                <c:forEach items="${productions}" var="production">
                    <tr>
                        <td>${production.produit}</td>
                        <td>
                            <c:forEach items="${production.listUnites}" var="unite"> 
                                ${unite.nomUnite} <br>
                            </c:forEach>
                        </td>
                        <td>${production.duree}</td>
                    </tr>
                </c:forEach>
            </table>
        </center>
    </jsp:body>
</tag:base>

