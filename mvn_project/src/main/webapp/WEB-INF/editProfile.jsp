<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@page import="controleur.AuthorisationManager.Credential, controleur.AuthorisationManager.Permission"%>

<tag:base>
    <jsp:attribute name="header">
      Editer son Profil
    </jsp:attribute>
    <jsp:body>
        <form action="editProfile" method="post">
        <table class="table">
                <tr>
                        <td> Email : </td> <td> <input type="email" name="email"/></td>
                </tr>
                <tr>
                        <td> Mot de passe : </td> <td> <input type="password" name="mdp"/> </td>
                </tr>
                <tr>
                        <td> Adresse : </td> <td> <input type="text" name="adresse"/> </td>
                </tr>
                <tr>
                        <td> Nom : </td> <td> <input type="text" name="nom"/> </td>
                </tr>
                <tr>
                        <td> Prénom : </td> <td> <input type="text" name="prenom"/> </td>
                </tr>

        </table>
        <input class="btn btn-default center-block" type="submit" value="Valider">
        </form>
    </jsp:body>
</tag:base>