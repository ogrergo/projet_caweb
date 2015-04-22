<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      <h1>Contrats Client</h1>
    </jsp:attribute>
    <jsp:body>

<form action="customerContracts" method="post">
<table>
	<caption>Contrats en cours</caption>
	<tr> 
	<td>Contrats en cours</td>
	<td><button type="button">Prolonger</button></td>
	</tr>
</table>
<button type="button">Saisir Disponibilités</button>

<table>
<caption> Contrats en attente de validation</caption>
	<tr> <td> Contrats non validés </td></tr>
</table>
</form>
    </jsp:body>
</tag:base>