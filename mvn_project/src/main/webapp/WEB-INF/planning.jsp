<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
      Planning
    </jsp:attribute>
    <jsp:body>
     	<h2>Planning</h2>
    	<table BORDER="1">
     	<caption><c:forEach items ="${mois}" var="mois"> ${mois}</c:forEach></caption>
    		<c:forEach items="${weeks}" var="week">
        		<tr> 
        			<td>Semaine ${week}</td>
        			<td> ${livreurs1[week].nom} </td>
        			<td> ${livreurs2[week].nom} </td>
        		</tr>
        	</c:forEach>
		</table>
    </jsp:body>
</tag:base>
