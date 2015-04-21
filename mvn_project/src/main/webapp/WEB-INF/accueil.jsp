<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      <h1>accueil</h1>
    </jsp:attribute>
    <jsp:body>
<table>
	<tr>
		<td> <button type="button">S'inscrire</button></td>
		<td> <button type="button">Se connecter</button></td>
	</tr>
</table>
<table>
	<caption>Produits disponibles</caption>
	<tr> 
		<td>Produit</td>
		<td><button type="button">Demander un contrat</button></td>
	</tr>
</table>
    </jsp:body>
</tag:base>