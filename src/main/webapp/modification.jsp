<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RDVmedecin - Modification</title>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/modification">
		<h3>Profil de ${sessionScope.utilisateur.prenom}
			${sessionScope.utilisateur.nom}</h3>


		Adresse mail : <b>${sessionScope.utilisateur.mail}</b> <br />
		<div class="form-group">
			<label class="form-control-label text-muted">Téléphone : </label> <input
				id="inputTelephone" name="telephone" type="tel" size="20"
				maxlength="60" class="form-control mb-3"
				value="${utilisateur.telephone}" required autofocus /><br /> Année
			de naissance : <b>${sessionScope.utilisateur.naissance}</b> <br />
			Sexe : <b>${sessionScope.utilisateur.sexe}</b> <br />
			<div class="form-group">
				<label class="form-control-label text-muted">Adresse : </label> <input
					id="inputTelephone" name="adresse" type="text" size="20"
					maxlength="60" class="form-control mb-3"
					value="${utilisateur.adresse}" required autofocus /> <br />
				<div class="form-group">
					<label class="form-control-label text-muted">Code postal :
					</label> <input id="inputTelephone" name="code_postal" type="number"
						size="20" maxlength="60" class="form-control mb-3"
						value="${utilisateur.code_postal}" required autofocus /> <br />
					<div class="form-group">
						<label class="form-control-label text-muted">Ville : </label> <input
							id="inputTelephone" name="code_postal" type="text" size="20"
							maxlength="60" class="form-control mb-3"
							value="${utilisateur.ville}" required autofocus /> <br /> Role
						: <b>${sessionScope.utilisateur.role}</b><br />
						<div class="form-group">
							<label class="form-control-label text-muted">Mot de passe
								: </label> <input id="inputTelephone" name="code_postal" type="password"
								size="20" maxlength="60" class="form-control mb-3"
								value="${utilisateur.motdepasse}" required autofocus /> <br />
							<input type="submit" value="Confirmer" /> <a
								href="<%=request.getContextPath()%>/profil"">Annuler</a>
	</form>
</body>
</html>