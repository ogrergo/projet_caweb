<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Available</title>
</head>
<body>
<form action="available" method="post">
<table BORDER="1">
	<caption>Disponibilités</caption>
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
		<td ALIGN="CENTER">  <input type="checkbox" class="switch-input"> </td>
    <%}%>
	</tr>
</table>
</form>
</body>
</html>