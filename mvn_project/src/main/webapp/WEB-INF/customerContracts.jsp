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
</form>
<form action="available" mehtod="post">
<input type="submit" value="Saisir Disponibilités">
</form>
<table BORDER="1">
<caption> Contrats en attente de validation</caption>
	<tr> <td> Contrats non validés </td></tr>
</table>
    </jsp:body>
</tag:base>