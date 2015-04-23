<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      Demande de Contrat
    </jsp:attribute>
    <jsp:body>
<form action="newContract" method="post">
<table>
	<caption>PRODUIT : nomproduit FOURNISSEUR : nomfournisseur</caption>
	
	<tr>
		<td> Quantité : </td> <td> <input type="text" name="quantite"/></td>
	</tr>
	<tr>
		<td> Date : </td> <td> <input type="date" name="date"/></td>
	</tr>
	<tr>
		<td> Durée : </td> <td> <input type="text" name="duree"/></td>
	</tr>
</table>
<input type="submit" value="Valider">
</form>
    </jsp:body>
</tag:base>