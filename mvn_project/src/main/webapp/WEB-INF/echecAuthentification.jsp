<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
      Echec de l'autentification
    </jsp:attribute>
    <jsp:body>
        <form action="/caweb">
            <input class="btn btn-default center-block" type="submit">
        </form>
    </jsp:body>
</tag:base>