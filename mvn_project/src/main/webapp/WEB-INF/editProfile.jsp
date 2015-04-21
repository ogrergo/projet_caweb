<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition du profile</title>

<script type="text/javascript">

function isEmailAdress(champ)
{
	var regex = /^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/;
	if(!regex.test(champ.value))
	{
	   surligne(champ, true);
	   return false;
	}
	else
	{
	   surligne(champ, false);
	   return true;
	} 
}

function surligne(champ, erreur)
{
   if(erreur)
      champ.style.backgroundColor = "#fba";
   else
      champ.style.backgroundColor = "";
}

function verifyMail(mail)
{
	if (isEmailAdress(mail.email))
		return true;
	else {
		alert("Adresse email invalide");
		return false;
	}
}
</script>

</head>
<body>
<form action="editProfile" method="post" onsubmit="return verifyMail(this)">
<table>
	<tr>
		<td> Email : </td> <td> <input type="text" name="email" onblur="isEmailAdress(this)"/></td>
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