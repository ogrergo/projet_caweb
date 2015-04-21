<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      <h1>Contrats Producteurs</h1>
    </jsp:attribute>
    <jsp:body>
<form action="productorContracts" method="post">
<table>
	<caption>Contrats en cours</caption>
	<tr>Contrats en cours</tr>
</table>
<table>
<caption> Contrats en attente de validation</caption>
	<tr> <td>Contrats non valides (mettre un descriptif du contrat)</td> 
		<td><button type="button">Valider</button></td>
	</tr>
</table>
</form>
    </jsp:body>
</tag:base>