<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      <h1>Contrats Producteurs</h1>
    </jsp:attribute>
    <jsp:body>
	<form action="productorContracts" method="post">
	<table BORDER="1">
	<caption>Contrats en cours</caption>
	<tr><td>Contrats en cours</td></tr>
	</table>
	<table BORDER="1">
	<caption> Contrats en attente de validation</caption>
	<tr> 
		<td>Contrats non valides (mettre un descriptif du contrat)</td> 
		<td><button type="button">Valider</button></td>
		<td><button type="button">Refuser</button></td>
	</tr>
</table>
</form>
    </jsp:body>
</tag:base>