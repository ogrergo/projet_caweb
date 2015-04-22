<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
    <h1>planning</h1>
    </jsp:attribute>
    <jsp:body>
     	<h2>Planning</h2>
    	<table BORDER="1">
     	<caption><c:forEach items ="${mois}" var="mois"> ${mois}</c:forEach></caption>
    		<c:forEach items="${semaines}" var="semaine">
        		<tr> 
        			<td>Semaine ${semaine}</td>
        			<td> Livreur1 </td>
        			<td> Livreur2 </td>
        		</tr>
        	</c:forEach>
		</table>
    </jsp:body>
</tag:base>