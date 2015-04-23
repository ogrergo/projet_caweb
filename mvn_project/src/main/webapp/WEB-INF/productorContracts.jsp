<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<tag:base>
    <jsp:attribute name="header">
        Contrats Producteurs
    </jsp:attribute>
    <jsp:body>
        <h4>Contrats en cours</h4>
        <form action="productorContracts" method="post">
            <table class="table-hover table">
                <tr>
                    <td>Contrats en cours</td>
                    <c:forEach items="${contratsValide}" var="contrat">
                        ${contrat.quantite}
                    </c:forEach>
                </tr>
            </table>
            
            <hr>
            
            <h4>Contrats en attente de validation</h4   >
            <table class="table-hover table">
                <tr> 
                    <c:forEach items="${contratsInvalide}" var="contrat2">
                        <td>${contrat2.quantite}</td> 
                        <td><a href="/caweb/productorContracts?contract=${contrat2.idContrat}&validate=true">Valider</a></td>
                        <td><a href="/caweb/productorContracts?contract=${contrat2.idContrat}&validate=false">Refuser</a></td>
                    </c:forEach>

                </tr>
            </table>
        </form>
    </jsp:body>
</tag:base>