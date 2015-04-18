<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>listProducteur.jsp</title>
    </head>
    <body>
        <h1>Liste des producteurs dans la base de données</h1>
    <center>
        <table border="1">
            <caption>Liste des producteurs</caption>
            <tr>
                <th>idProducteur</th>
                <th>email</th>
            </tr>
            <c:forEach items="${producteurs}" var="producteur">
                <tr>
                    <td>>${producteur.idProducteur}</td>
                    <td>${producteur.email}</td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>

