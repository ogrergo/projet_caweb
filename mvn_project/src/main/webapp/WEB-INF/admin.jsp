<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
        Administration du Planning
    </jsp:attribute>
    <jsp:body>
        <form action="admin" method="post">
            <p> Mois courant: <c:forEach items ="${mois}" var="mois"> ${mois} </c:forEach></p>
            <table class="table">
                <tr>
                    <th>N° Semaine</th>
                    <th>Livreur 1</th>
                    <th>Livreur 2</th>
                </tr>
                <c:forEach items="${semaines}" var="semaine">
                    <tr> 
                        <td>Semaine ${semaine}</td>
                        <td><select name="livreur1_${semaine}">
                                <c:forEach items="${liste_dispos[semaine]}" var="dispo">
                                    <option value="${dispo.id}">${dispo.nom} ${dispo.prenom}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><select name="livreur2_${semaine}">
                                <c:forEach items="${liste_dispos[semaine]}" var="dispo">
                                    <option value="${dispo.id}">${dispo.nom} ${dispo.prenom}</option>
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
