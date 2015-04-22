<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      <h1>Demande de Contrat</h1>
    </jsp:attribute>
    <jsp:body>
<form action="newContract" method="post">
<table>
	<caption>PRODUIT : ${production.produit } FOURNISSEUR : ${production.nomProducteur } ${production.prenomProducteur }</caption>
	
	<tr>
		<td> Quantité : </td> <td> <input type="number" name="quantite"/></td>
	</tr>
	<tr>
		<td> Date : </td> <td> <input type="number" name="date"/></td>
	</tr>
</table>
<input type="submit" value="Valider">
</form>
    </jsp:body>
</tag:base>