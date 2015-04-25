<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@page import="controleur.AuthorisationManager.Credential"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
        Accueil
    </jsp:attribute>
    <jsp:body>
        <h3 class="text-center">Produits disponibles</h3>
        <table class="table table-hover table-condensed">

            <c:forEach items="${production}" var="prod">
                <tr>
                    <td>${prod.produit}</td>
                    <td>${prod.prenomProducteur} ${prod.nomProducteur}</td>
                    <td>${prod.duree} semaines</td>
                <form method="GET" action="/caweb/newContract">
                    <td> 
                        <select name="unittype">
                            <c:forEach items="${unites[prod]}" var="unite">
                                <option value="${unite.nomUnite }"> ${unite.nomUnite }</option>
                            </c:forEach>
                        </select>

                    </td>
                    <td>
                        <input type="hidden" name="production" value="${prod.idProduction}">
                        <%-- < href="/caweb/newContract?production=${prod.idProduction}"> passer un contrat </a> --%>
                        <input class="btn btn-default center-block" type="submit" value="Passer la commande">
                    </td>
                </form>
            </tr>
        </c:forEach>
        
       
    </table>
     <c:if test="${credential.authorisation == 'CONSOMMATEUR'}">
     <h3 class="text-center">Mes Permanences</h3>
     <table class="table">
     <tr>
     	<th> Semaine </th>
     	<th> Binôme </th>
     </tr>
     <c:forEach items="${listPermanence}" var="permanence">
     <tr>
     	<td>
        	${permanence.idSemaine}
       	</td>
       	<td>
        	${binomes[permanence].prenom} ${binomes[permanence].nom} (${binomes[permanence].email})
       	</td>
     </tr>
     </c:forEach>
     </table>
     </c:if>
     
</jsp:body>
</tag:base>
