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
                	<c:choose>
                		<c:when test="${is_inactif[semaine]}">
                			<tr class="warning"> 
                		</c:when>
                		<c:otherwise>
                			<tr class="success"> 
                		</c:otherwise>
                	</c:choose>
                        <td>Semaine ${semaine}</td>
                        <td>
                        	<select name="livreur1_${semaine}">
                                <c:forEach items="${liste_dispos[semaine]}" var="dispo">
	                                <c:choose>
		                                <c:when test="${dispo.id == livreurs1[semaine].id}">
	                                		<option value="${dispo.id}" selected>${dispo.nom} ${dispo.prenom}</option>
	                                	</c:when>
									    <c:otherwise>
	                                		<option value="${dispo.id}">${dispo.nom} ${dispo.prenom}</option>
									    </c:otherwise>
									</c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                        	<select name="livreur2_${semaine}">
                        		<c:forEach items="${liste_dispos[semaine]}" var="dispo">
	                                <c:choose>
		                                <c:when test="${dispo.id == livreurs2[semaine].id}">
	                                		<option value="${dispo.id}" selected>${dispo.nom} ${dispo.prenom}</option>
	                                	</c:when>
									    <c:otherwise>
	                                		<option value="${dispo.id}">${dispo.nom} ${dispo.prenom}</option>
									    </c:otherwise>
									</c:choose>
								</c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Valider">
        </form>
        <br>
        <table class="table">
                <tr>
                    <th>Consommateur</th>
                    <th>Nombre de Livraisons</th>
                </tr>
                <c:forEach items="${liste_consommateur}" var="consommateur">
                <tr>
                	<td>
		        		${consommateur.nom}  ${consommateur.prenom}
		        	</td>
		        	<td>
		        		<div class="progress">
						  <div class="progress-bar" role="progressbar" aria-valuenow="${nb_permanences[consommateur]}" aria-valuemin="0" aria-valuemax="100" style="width: ${percentages[consommateur]}%;">
						    ${nb_permanences[consommateur]}
						  </div>
						</div>
		        	</td>	
		        </tr>
		        </c:forEach>
        </table>       
    </jsp:body>
</tag:base>
