<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      Se connecter
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <form method="post" class="col-md-offset-3 col-md-6">
                <div class="form-group">
                    <label for="emailinput">Email</label>
                    <input name="email" type="email" class="form-control" id="emailinput" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="passwordinput">Mot de Passe</label>
                    <input type="password" class="form-control" name="password" id="passwordinput" placeholder="Password">
                </div>
                <input class="btn btn-default center-block" type="submit" />
            </form>
        </div>
    </jsp:body>
</tag:base>