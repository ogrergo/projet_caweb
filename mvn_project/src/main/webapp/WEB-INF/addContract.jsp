<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ page session="true" %>
<%@page import="controleur.AuthorisationManager.Credential"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <script type="text/javascript">

        function verifDuree(champ)
        {
            var duree = parseInt(champ.value);
            if (isNaN(duree))
            {
                alert("durée doit etre un nombre !!");
                return false;
            }
            else
            {
                return true;
            }
        }

        function verifForm(f)
        {
            f.action.value = "addContract";
            return verifDuree(f.duree);
        }
    </script>
</head>

<tag:base>
    <jsp:attribute name="header">
        <h1>Ajouter un contrat</h1>

    </jsp:attribute>
    <jsp:body>
        <form action="addContract" method="get" onsubmit="return verifForm(this)">
            <input type="hidden" name="action" value="">
            <table>
                <tr>
                    <td>Produit</td>
                    <td>
                        <select name="produitSelect" size="1">
                            <c:forEach items="${produits}" var="produit">
                                <option>${produit.nomProduit}
                                </c:forEach>
                        </select>
                    <td>
                    <td>
                        <form action="addProduit" method="get">
                            <input type="submit" value="Ajouter un produit">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>Unité(s)</td>
                    <td>

                        <select multiple name="unitesSelect" size="${unites.size()}">
                            <c:forEach items="${unites}" var="unite">
                                <option>${unite.nomUnite}
                                </c:forEach>
                        </select>

                    <td>
                    <td>
                        <form action="addUnite" method="get">
                            <input type="submit" value="Ajouter une unité">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td> Durée : </td> <td> <input type="int" name="duree" onblur="verifDuree(this)"/></td>
                </tr>
            </table>
            <input type="submit" value="Valider">
        </form>
    </jsp:body>
</tag:base>