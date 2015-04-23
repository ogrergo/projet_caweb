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
            return verifDuree(f.duree);
        }

    </script>
</head>

<tag:base>
    <jsp:attribute name="header">
        <h1>Ajouter une production</h1>

    </jsp:attribute>
    <jsp:body>
        <form action="addProduction" method="post" onsubmit="return verifForm(this)">
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
                        <a href="addProduit"> 
                            <input type="button" value="Ajouter un produit"> </a>
                    </td>
                </tr>
                <tr>
                    <td>Unité(s)</td>
                    <td>

                        <select multiple name="unitesSelect" size="10">
                            <c:forEach items="${unites}" var="unite">
                                <option>${unite.nomUnite}
                                </c:forEach>
                        </select>

                    <td>
                    <td>
                        <a href="addUnite"> 
                            <input type="button" value="Ajouter une unite"> </a>
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