<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tag:base>
    <jsp:attribute name="header">
        Ma liste de production
    </jsp:attribute>
    <jsp:body>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Produit</th>
                        <th>Unité(s)</th>
                        <th>durée</th>
                    </tr>
                </thead>
                <tbody>
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
                </tbody>
            </table>
    </jsp:body>
</tag:base>

