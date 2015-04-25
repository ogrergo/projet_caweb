<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
      Planning
    </jsp:attribute>
    <jsp:body>
        <h4>Mois courant : <span class="badge">${mois}</span></h4>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>N° Semaine</th>
                    <th>Livreur 1</th>
                    <th>Livreur 2</th>
                </tr>
            </thead>
            <tbody>
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
