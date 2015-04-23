<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<tag:base>
    <jsp:attribute name="header">
      <h1>Contrats Producteurs</h1>
    </jsp:attribute>
    <jsp:body>
	<form action="productorContracts" method="post">
	<table BORDER="1">
	<caption>Contrats en cours</caption>
	<tr>
		<td>Contrats en cours</td>
		<c:forEach items="${contratsValide}" var="contrat">
		 	${contrat.quantite}
 		</c:forEach>
	</tr>
	</table>
	<table BORDER="1">
	<caption> Contrats en attente de validation</caption>
	<tr> 
		<c:forEach items="${contratsInvalide}" var="contrat2">
		 	<td>${contrat2.quantite}</td> 
			<td><a href="/caweb/productorContracts?contract=${contrat2.idContrat}&validate=true">Valider</a></td>
			<td><a href="/caweb/productorContracts?contract=${contrat2.idContrat}&validate=false">Refuser</a></td>
		</c:forEach>

	</tr>
</table>
</form>
    </jsp:body>
</tag:base>