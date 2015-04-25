1<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ page session="true" %>
<%@page import="controleur.AuthorisationManager.Credential"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <script type="text/javascript">

        function verifNomUnite(champ)
        {
            if (champ.value.length < 2 || champ.value.length > 20)
            {
                alert("le nom du unite doit faire entre 2 et 50 caractères");
                return false;
            }
            return true;
        }

        function verifForm(f)
        {
            return verifNomUnite(f.nomUnite);
        }

    </script>
</head>

<tag:base>
    <jsp:attribute name="header">
        <h1>Ajouter un unite</h1>

    </jsp:attribute>
    <jsp:body>
        <form action="addUnite" method="post" onsubmit="return verifForm(this)">
            <table>
            <c:if test="${ uniteErreur == 'true'}" >                               
            	<tr>
            		<td> Erreur : cette unite existe déjà </td>
            	</tr>
            </c:if>
                <tr>
                    <td> Nom du unite : </td> <td> <input type="text" name="nomUnite" onblur="verifNomUnite(this)"/></td>
                </tr>
            </table>
            <input type="submit" value="Valider">
        </form>
    </jsp:body>
</tag:base>
