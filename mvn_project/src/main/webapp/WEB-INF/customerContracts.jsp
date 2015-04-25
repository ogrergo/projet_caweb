<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
        Contrats Client
    </jsp:attribute>
    <jsp:body>

    <h4>Contrats non commencés</h4>
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
        <c:forEach items="${contratsNonCommences}" var="contratNonCommence">
            <form>
                <tr> 
                    
                   	<c:forEach items="${nonCommences[contratNonCommence]}" var="champ">
                   		<td>${champ}</td>
                   	</c:forEach>
                    <td>${contratNonCommence.quantite}</td>
                    <td>${contratNonCommence.nomUnite}</td>
                    <td>${contratNonCommence.dateDebut}</td>
                   	<td>
                   		<form method="GET" action="/caweb/customerContract">
                   			<input type="hidden" name="contrat" value="${contratNonCommence.idContrat}"/>
                   			<input class="btn btn-default center-block" type="submit" value="Prolonger">
                   		</form>
                   	</td>
                </tr>
            </form>
        </c:forEach>
    </tbody>
</table>

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
        <c:forEach items="${contratsEnCours}" var="contratEnCour">
            <form>
                <tr> 
                    
                   	<c:forEach items="${enCours[contratEnCour]}" var="champ">
                   		<td>${champ}</td>
                   	</c:forEach>
                    <td>${contratEnCour.quantite}</td>
                    <td>${contratEnCour.nomUnite}</td>
                    <td>${contratEnCour.dateDebut}</td>
                   	<td>
                   		<form method="GET" action="/caweb/customerContract">
                   			<input type="hidden" name="contrat" value="${contratEnCour.idContrat}"/>
                   			<input class="btn btn-default center-block" type="submit" value="Prolonger">
                   		</form>
                   	</td>
                </tr>
            </form>
        </c:forEach>
    </tbody>
</table>

  <h4>Contrats terminés</h4>
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
        <c:forEach items="${contratsTermines}" var="contratTermine">
            <form>
                <tr> 
                    
                   	<c:forEach items="${termines[contratTermine]}" var="champ">
                   		<td>${champ}</td>
                   	</c:forEach>
                    <td>${contratTermine.quantite}</td>
                    <td>${contratTermine.nomUnite}</td>
                    <td>${contratTermine.dateDebut}</td>
                   	<td>
                   		<form method="GET" action="/caweb/customerContract">
                   			<input type="hidden" name="contrat" value="${contratTermine.idContrat}"/>
                   			<input class="btn btn-default center-block" type="submit" value="Prolonger">
                   		</form>
                   	</td>
                </tr>
            </form>
        </c:forEach>
    </tbody>
</table>
 <h4>Contrats en attente de validation</h4>
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
            <tr> 
               	<c:forEach items="${invalides[contratInvalide]}" var="champ">
               		<td>${champ}</td>
               	</c:forEach>
                <td>${contratInvalide.quantite}</td>
                <td>${contratInvalide.nomUnite}</td>
                <td>${contratInvalide.dateDebut}</td>
            </tr>
    </c:forEach>
  </tbody>
</table>
</jsp:body>
</tag:base>
