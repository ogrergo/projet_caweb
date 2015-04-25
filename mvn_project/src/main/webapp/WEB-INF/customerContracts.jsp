<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
        Contrats Client
    </jsp:attribute>
    <jsp:body>

    <h4>Contrats en cours</h4>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Nom Producteur</th>
                <th>Adresse</th>
                <th>Mail</th>
                <th>Produit</th>
                <th>Durée</th>
                <th>Quantité</th>
                <th>Unité</th>
                <th>Date de début</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${contratsValides}" var="contratValide">
            <form>
                <tr> 
                    
                   	<c:forEach items="${valide[contratValide]}" var="champ">
                   		<td>${champ}</td>
                   	</c:forEach>
                    <td>${contratValide.quantite}</td>
                    <td>${contratValide.nomUnite}</td>
                    <td>${contratValide.dateDebut}</td>
                   <td><button type="button">Prolonger</button></td>
                </tr>
            </form>
        </c:forEach>
    </tbody>

</table>
<hr/>
<h4> Contrats en attente de validation</h4>

<table class="table table-hover">
    <thead>
        <tr>
            <th>Nom Producteur</th>
            <th>Adresse</th>
            <th>Mail</th>
            <th>Produit</th>
            <th>Durée</th>
            <th>Quantité</th>
            <th>Unité</th>
            <th>Date de début</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${contratsInvalides}" var="contratInvalide">
        <form>
            <tr> 
                
               	<c:forEach items="${invalide[contratInvalide]}" var="champ">
               		<td>${champ}</td>
               	</c:forEach>
                <td>${contratInvalide.quantite}</td>
                <td>${contratInvalide.nomUnite}</td>
                <td>${contratInvalide.dateDebut}</td>
            </tr>
        </form>
    </c:forEach>
    
    </tbody>

</table>
</jsp:body>
</tag:base>
