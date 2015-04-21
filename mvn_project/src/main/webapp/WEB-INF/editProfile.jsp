<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition du profile</title>
</head>
<body>
<form action="editProfile" method="post">
<table>
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
<input type="submit" value="Valider">
</form>
</body>
</html>