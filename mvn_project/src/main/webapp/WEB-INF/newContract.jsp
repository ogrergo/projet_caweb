<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demande de contrat</title>
</head>
<body>
<form action="newContract" method="post">
<table>
	<caption>PRODUIT : nomproduit FOURNISSEUR : nomfournisseur</caption>
	
	<tr>
		<td> Quantité : </td> <td> <input type="text" name="quantite"/></td>
	</tr>
	<tr>
		<td> Date : </td> <td> <input type="date" name="date"/></td>
	</tr>
	<tr>
		<td> Durée : </td> <td> <input type="text" name="duree"/></td>
	</tr>
</table>
<input type="submit" value="Valider">
</form>
</body>
</html>