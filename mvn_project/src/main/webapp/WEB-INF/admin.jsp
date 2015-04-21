<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>
<table>
	<caption>Demandes d'inscription</caption>
	<tr>
		<td>email demandeur</td>
		<td><button type="button">Valider</button></td>
	</tr>
</table>
<form action="planning" method="post">
<table>
	<caption>Planning</caption>
	<tr>
		<th> Lundi </th>
		<th> Mardi </th>
		<th> Mercrdi </th>
		<th> Jeudi </th>
		<th> Vendredi </th>
		<th> Samedi </th>
		<th> Dimanche </th>
	</tr>
	<tr>
	<% for(int i=0; i<7; i++) {%>
		<td><button type="button">Voir Disponibilités</button></td>
	<%} %>
	</tr>
	<tr>
	<% for(int i=0; i<7; i++) {%>
		<td><input type="text" value=Livreur1></td>
	<%} %>
	</tr>
	<tr>
	<% for(int i=0; i<7; i++) {%>
		<td><input type="text" value=Livreur2></td>
	<%} %>
	</tr>
</table>
<input type="submit" value="Valider">
</form>
</body>
</html>