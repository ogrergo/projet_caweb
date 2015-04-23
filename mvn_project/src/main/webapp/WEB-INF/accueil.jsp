<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      Accueil
    </jsp:attribute>
    <jsp:body>
        <table>


        <c:choose>
              <c:when test="${credential == null}">
                <tr>
                                <td> <form action="/caweb/authentification"><input type="submit" value="Se connecter"></form></td>
                        </tr>
              </c:when>

              <c:otherwise>
                <form action="/caweb/"><input type="submit" value="Modifier mon compte">  </form>

                Vous êtes loggé en tant que : ${credential.authorisation }

              </c:otherwise>
        </c:choose>
        </table>
        <table>
                <caption>Produits disponibles</caption>
                <c:forEach items="${production}" var="production">
                        <tr>
                            <td>${production.produit}</td>

                            <td>${production.prenomProducteur} ${production.nomProducteur}</td>

                            <td> <form action="/caweb/authentification"><input type="submit" value="Passer un contrat"></form></td>
                        </tr>
            </c:forEach>

        </table>
    </jsp:body>
</tag:base>