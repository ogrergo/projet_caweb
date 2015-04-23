1<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ page session="true" %>
<%@page import="controleur.AuthorisationManager.Credential"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <script type="text/javascript">

        function verifNomProduit(champ)
        {
            if (champ.value.length < 2 || champ.value.length > 50)
            {
                alert("le nom du produit doit faire entre 2 et 50 caractères");
                return false;
            }
            return true;
        }

        function verifForm(f)
        {
            return verifNomProduit(f.nomProduit);
        }

    </script>
</head>

<tag:base>
    <jsp:attribute name="header">
        <h1>Ajouter un produit</h1>

    </jsp:attribute>
    <jsp:body>
        <form action="addProduit" method="post" onsubmit="return verifForm(this)">
            <table>
                <tr>
                    <td> Nom du produit : </td> <td> <input type="text" name="nomProduit" onblur="verifNomProduit(this)"/></td>
                </tr>
            </table>
            <input type="submit" value="Valider">
        </form>
    </jsp:body>
</tag:base>
