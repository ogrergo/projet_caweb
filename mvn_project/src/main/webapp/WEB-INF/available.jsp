<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:base>
    <jsp:attribute name="header">
        Disponibilités
    </jsp:attribute>
    <jsp:body>
        <p>Cochez les semaines pendant lesquelles vous êtes disponibles pour les livraisons</p>

        <form action="available" method="post" class="container-fluid">
            <div class="row">

                <c:forEach items="${listKey}" var="semaine">
                    <div class="col-md-2 panel panel-week"> 
                        Semaine ${semaine}
                        <input type="checkbox" class="switch-input" name="semaine${semaine}"
                               <c:if test="${ mapDispo[semaine] == 'true'}" >
                                   checked="checked"
                               </c:if>
                               > 
                    </div>
                </c:forEach>
                <hr class="col-md-12">
            </div>
            <input class="btn btn-default center-block " type="submit" value="Valider">

        </form>

    </jsp:body>
</tag:base>
