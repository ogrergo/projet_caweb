<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
        <h1>Ajouter un contrat</h1>
    </jsp:attribute>
    <jsp:body>
        <form action="addContract" method="post">
            <table>
                <tr>
                    <td>Produit</td>
                    <td>
                        <form>
                           
                                <c:forEach items="${produits}" var="produit">
                                   ${produit.nomProduit}
                                </c:forEach>
                           
                        </form>
                    <td>
                    <td>
                        <form action="addProduit" method="post">
                            <input type="submit" value="Ajouter un produit">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>Unité(s)</td>
                    <td>
                        <form>
                            <select name="listunite" size="1" multiple>
                                <c:forEach items="${unites}" var="unite">
                                    <option>${unite.nomUnite}
                                </c:forEach>
                            </select>
                        </form>
                    <td>
                    <td>
                        <form action="addUnite" method="post">
                            <input type="submit" value="Ajouter une unité">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td> Durée : </td> <td> <input type="text" name="duree"/></td>
                </tr>
            </table>
            <input type="submit" value="Valider">
        </form>
    </jsp:body>
</tag:base>