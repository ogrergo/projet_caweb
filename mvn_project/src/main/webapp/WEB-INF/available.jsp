<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
      <h1>Available</h1>
    </jsp:attribute>
    <jsp:body>
		<form action="available" method="post">
		<table BORDER="1">
			<caption>Disponibilités</caption>
			<tr> <c:forEach items="${semaines}" var="semaine">
        			<th>Semaine ${semaine}</th>
        		</c:forEach>
			</tr>
			<tr>
			<c:forEach items="${semaines}" var="semaine">
				<td ALIGN="CENTER">  <input type="checkbox" class="switch-input"> </td>
			</c:forEach>
			</tr>
		</table>
		</form>
    </jsp:body>
</tag:base>