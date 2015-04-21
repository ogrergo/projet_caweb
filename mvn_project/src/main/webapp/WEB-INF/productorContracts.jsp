<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contrats Producteurs</title>
</head>
<body>
<form action="customerContracts" method="post">
<table>
	<caption>Contrats en cours</caption>
	<tr>Contrats en cours</tr>
</table>
<table>
<caption> Contrats en attente de validation</caption>
	<tr> <td>Contrats non valides (mettre un descriptif du contrat)</td> 
		<td><button type="button">Valider</button></td>
	</tr>
</table>
</form>
</body>
</html>