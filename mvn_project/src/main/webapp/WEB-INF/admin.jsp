<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
    <h1>Administrateur</h1>
    </jsp:attribute>
    <jsp:body>
    	<form action="admin" method="post">
    	<h2>Planning</h2>
    	<table BORDER="1">
     	<caption><c:forEach items ="${mois}" var="mois"> ${mois}</c:forEach></caption>
    		<c:forEach items="${semaines}" var="semaine">
        		<tr> 
        			<td>Semaine ${semaine}</td>
        			<td> Livreurs </td>
        			<td><select name="Livreur1">
        				<c:forEach items="${liste_dispos}" var="dispo">
							<option value="${dispo}">${dispo}</option>
						</c:forEach>
						</select>
					</td>
					<td><select name="Livreur2">
        				<c:forEach items="${liste_dispos}" var="dispo">
							<option value="${dispo}">${dispo}</option>
						</c:forEach>
						</select>
					</td>
        		</tr>
        	</c:forEach>
		</table>
		<input type="submit" value="Valider">
		</form>
    </jsp:body>
</tag:base>