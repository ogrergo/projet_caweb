<%-- 
    Document   : menu
    Created on : 22 avr. 2015, 16:03:26
    Author     : hadware
--%>
<%@page session="true" %>
<%@page import="controleur.AuthorisationManager.Credential"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<ul class="nav nav-stacked">
    <li><a href=accueil >Accueil</a></li>
    
    <c:if test="${credential.authorisation == Permission.CONSOMATEUR}">
        <li><a href=editProfile >Modifier son Profil</a></li>
        <li><a href=newContract >Demander un Contract</a></li>
        <li><a href=customerContracts >Mes Contracts</a></li>
    </c:if>
    
    <c:if test="${credential.authorisation == Permission.PRODUCTEUR}">
        <li><a href=editProfile >Modifier son Profil</a></li>
        <li><a href=productorContracts >Mes Contracts</a></li>
    </c:if>
    
    <c:if test="${credential.authorisation == Permission.RESPONSABLE_PLANNING}">
        <li><a href=admin>Gestion du planning</a></li>
    </c:if>
    
    	<li><a href=logout>Deconnexion</a>
    
</ul>
