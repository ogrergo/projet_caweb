<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<tag:base>
    <jsp:attribute name="header">
      <h1>accueil</h1>
    </jsp:attribute>
    <jsp:body>
<table>
	<tr>
		<td> <form action="/caweb/"><input type="submit" value="S'inscrire">  </form></td>
		<td> <form action="/caweb/authentification"><input type="submit" value="Se connecter"></form></td>
	</tr>
</table>
<table>
	<caption>Produits disponibles</caption>
	<c:forEach items="${produits}" var="produit">
                <tr>
                    <td>${produit.nomProduit}</td>
                    <td> <form action="/caweb/authentification"><input type="submit" value="Passer un contrat"></form></td>
                </tr>
    </c:forEach>
 
</table>
    </jsp:body>
</tag:base>