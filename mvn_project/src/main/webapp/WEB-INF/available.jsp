<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      <h1>Available</h1>
    </jsp:attribute>
    <jsp:body>
		<form action="available" method="post">
		<table BORDER="1">
			<caption>Disponibilités</caption>
			<tr>
				<th> Lundi </th>
				<th> Mardi </th>
				<th> Mercredi </th>
				<th> Jeudi </th>
				<th> Vendredi </th>
				<th> Samedi </th>
				<th> Dimanche </th>
			</tr>
			<tr>
			<% for(int i=0; i<7; i++) {%>
				<td ALIGN="CENTER">  <input type="checkbox" class="switch-input"> </td>
    		<%}%>
			</tr>
		</table>
		</form>
    </jsp:body>
</tag:base>