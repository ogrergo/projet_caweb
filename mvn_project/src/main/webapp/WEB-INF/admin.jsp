<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:base>
    <jsp:attribute name="header">
    <h1>Administrateur</h1>
    </jsp:attribute>
    <jsp:body>
		<table>
			<caption>Demandes d'inscription</caption>
			<tr>
				<td>email demandeur</td>
				<td><button type="button">Valider</button></td>
			</tr>
		</table>
		<form action="planningAdmin" method="post">
		<table>
			<caption>Planning</caption>
			<tr>
				<th> Lundi </th>
				<th> Mardi </th>
				<th> Mercredi </th>
				<th> Jeudi </th>
				<th> Vendredi </th>
				<th> Samedi </th>
				<th> Dimanche </th>
			</tr>
			<tr>
				<td><button type="button">Voir Disponibilités</button></td>
				<td><button type="button">Voir Disponibilités</button></td>
				<td><button type="button">Voir Disponibilités</button></td>
				<td><button type="button">Voir Disponibilités</button></td>
				<td><button type="button">Voir Disponibilités</button></td>
				<td><button type="button">Voir Disponibilités</button></td>
				<td><button type="button">Voir Disponibilités</button></td>
			
			</tr>
			<tr>
				<td><input type="text" value=Livreur1></td>
				<td><input type="text" value=Livreur1></td>
				<td><input type="text" value=Livreur1></td>
				<td><input type="text" value=Livreur1></td>
				<td><input type="text" value=Livreur1></td>
				<td><input type="text" value=Livreur1></td>
				<td><input type="text" value=Livreur1></td>
			</tr>
			<tr>
				<td><input type="text" value=Livreur2></td>
				<td><input type="text" value=Livreur2></td>
				<td><input type="text" value=Livreur2></td>
				<td><input type="text" value=Livreur2></td>
				<td><input type="text" value=Livreur2></td>
				<td><input type="text" value=Livreur2></td>
				<td><input type="text" value=Livreur2></td>
			</tr>
		</table>
		<input type="submit" value="Valider">
		</form>
    </jsp:body>
</tag:base>