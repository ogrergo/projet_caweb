<%-- 
    Document   : menu
    Created on : 22 avr. 2015, 16:03:26
    Author     : hadware
--%>
<%@page session="true" %>
<%@page import="controleur.AuthorisationManager.Credential"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="panel panel-primary menu-userstatus">

    <div class="panel-heading">
        <h3 id="panel-title" class="panel-title">
            <%-- Displaying a "connect" button, or the user state --%>
            <c:choose>
                <c:when test="${credential == null}">
                    <form action="/caweb/authentification">
                        <input class="btn btn-default center-block" type="submit" value="Se connecter">
                    </form>
                </c:when>

                <c:otherwise>
                    Vous êtes loggé en tant que : ${credential.authorisation}
                </c:otherwise>
            </c:choose>
        </h3>
    </div>      
</div>
<ul class="nav nav-stacked">
    
    
    <li><a href=accueil >Accueil</a></li>
    <c:if test="${credential.authorisation != null}">
    <c:if test="${credential.authorisation == Permission.CONSOMATEUR}">
        <li><a href=editProfile >Modifier son Profil</a></li>
        <li><a href=newContract >Demander un Contract</a></li>
        <li><a href=customerContracts >Mes Contracts</a></li>
    </c:if>
    
    <c:if test="${credential.authorisation == Permission.PRODUCTEUR}">
        <li><a href=productorContracts >Mes Contracts</a></li>
        <li><a href=editProfile >Modifier son Profil</a></li>
    </c:if>
    
    <c:if test="${credential.authorisation == Permission.RESPONSABLE_PLANNING}">
        <li><a href=admin>Gestion du planning</a></li>
    </c:if>
    
    	<li><a href=logout>Deconnexion</a>
    </c:if>
</ul>
