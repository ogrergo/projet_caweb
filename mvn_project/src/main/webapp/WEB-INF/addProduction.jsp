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
                return false;
            }
            else
            {
                return true;
            }
        }

        function verifUnite(sel)
        {
            for (var i=0; i < sel.options.length; i++) {
                if (sel.options[i].selected) {
               		return true;
                }
            }
        	return false;
        }
        
        function verifForm(f)
        {
        	if (!verifUnite(f.unitesSelect)) {
        		alert("Erreur dans le formulaire : \nVeuillez saisir au moins une unité");
        		return false;
        	}
        	if (!verifDuree(f.duree)) {
        		alert("Erreur dans le formulaire : \nVeuillez saisir une durée valide");
        		return false;
        	}
            return true;
        }

    </script>
</head>

<tag:base>
    <jsp:attribute name="header">
    Ajouter une production
    </jsp:attribute>
    <jsp:body>
        <form action="addProduction" method="post" onsubmit="return verifForm(this)">
            <table class="table">
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
                            <button type="button" class="btn btn-default">Ajouter un produit</button>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>Unité(s)</td>
                    <td>
                        Selectionnez plusieurs unitées avec la touche ctrl<br/>
                        <select multiple name="unitesSelect" size="10">
                            <c:forEach items="${unites}" var="unite">
                                <option>${unite.nomUnite}
                                </c:forEach>
                        </select>

                    <td>
                    <td>
                        <a href="addUnite"> 
                            <button type="button" class="btn btn-default">Ajouter une unité</button>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td> Durée : </td> <td> <input type="int" name="duree"/></td>
                </tr>
            </table>
            <input class="btn btn-default center-block" type="submit" value="Valider">
        </form>
    </jsp:body>
</tag:base>
