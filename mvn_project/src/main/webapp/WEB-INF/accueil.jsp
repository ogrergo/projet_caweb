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
	<table>
		<c:choose>
      	<c:when test="${credential == null}">
      		<tr>
				<td> 
					<form action="/caweb/authentification">
					<input type="submit" value="Se connecter">
					</form>
				</td>
			</tr>
     	</c:when>

      	<c:otherwise>
      		<form action="/caweb/">
				<input type="submit" value="Modifier mon compte">  
			</form>
		
      		Vous êtes loggé en tant que : ${credential.authorisation}
      		<c:if test="${credential.authorisation == 'CONSOMATEUR'}">
      			<form action="/dfghjk" method="get">
      				<input type="submit" value="Mon calendrier" />
      			</form>
			</c:if>
      	</c:otherwise>
		</c:choose>
	</table>
	<table>
		<caption>Produits disponibles</caption>

        <c:forEach items="${production}" var="prod">
        	<tr>
        		<td>${prod.produit}</td>
            	<td>${prod.prenomProducteur} ${prod.nomProducteur}</td>
                <td> 
                	
                    	<select>
                    		<c:forEach items="${unites[prod]}" var="unite">
                    			<option> ${unite.nomUnite }</option>
                    		</c:forEach>
                    	</select>
                    	<a href="/caweb/newContract?production=${prod.idProduction}"> passer un contrat </a>
					</form>
				</td>
        	</tr>
    	</c:forEach>
 
	</table>
    </jsp:body>
</tag:base>
