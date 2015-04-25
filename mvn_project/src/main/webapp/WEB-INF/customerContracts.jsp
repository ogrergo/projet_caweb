<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
        Contrats Client</h1
    </jsp:attribute>
    <jsp:body>

    <h4>Contrats en cours</h4>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Producteur</th>
                <th>Produit</th>
                <th>Quantité</th>
                <th>Date de début</th>
                <th>Durée</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${unites}" var="unite">
            <form>
                <tr> 
                    <td>producteur</td>
                    <td>prod</td>
                    <td>qté</td>
                    <td>start</td>
                    <td>durée</td>
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
            <th>Producteur</th>
            <th>Produit</th>
            <th>Quantité</th>
            <th>Date de début</th>
            <th>Durée</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${unites}" var="unite">
        <form>
            <tr> 
                <td>producteur</td>
                <td>prod</td>
                <td>qté</td>
                <td>start</td>
                <td>durée</td>
                <td>
                	<a href="/caweb/customerContracts?contrat=${1 }">Prolonger</a></td>
            </tr>
        </form>
    </c:forEach>
    </tbody>

</table>
</jsp:body>
</tag:base>
