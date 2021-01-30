<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RDVmedecin - Profil</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@include file="header.jsp"%>

	<div class="container">
		<div class="col-md-12 col-10">
			<form method="post" action="<%=request.getContextPath()%>/profil">
				<h2 class="mb-5 mt-5 text-center heading">Profil de
					${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h2>
				<div class="row register-form justify-content-center">
					<div class="col-md-8">
						<h5>Adresse mail : <strong class="text-value">${sessionScope.utilisateur.mail}</strong></h5>
						<h5>Téléphone : <strong class="text-value">${sessionScope.utilisateur.mail}</strong></h5>
						<h5>Année de naissance : <strong class="text-value">${sessionScope.utilisateur.naissance}</strong></h5>
						<h5>Téléphone : <strong class="text-value">${sessionScope.utilisateur.telephone}</strong></h5>
						<h5>Sexe : <strong class="text-value">${sessionScope.utilisateur.sexe}</strong></h5>
						<h5>Adresse : <strong class="text-value">${sessionScope.utilisateur.adresse}</strong></h5>
						<h5>Code postal : <strong class="text-value">${sessionScope.utilisateur.code_postal}</strong></h5>
						<h5>Ville : <strong class="text-value">${sessionScope.utilisateur.ville}</strong></h5>
						
						<h5 class="mt-5">Vous êtes <strong class="text-value">${sessionScope.utilisateur.role}</strong></h5>
					</div>
				</div>
				<div class="row justify-content-center mt-5">
						<div class="col-md-4">
							<a href="<c:url value='/modification' />" type="submit"
								class="w-100 btn btn-lg btn-warning">Modifier mon profil</a>
						</div>
						<div class="col-md-4">
							<a href="<c:url value='/suppression' />" type="submit"
								class="w-100 btn btn-lg btn-danger">Supprimer mon compte</a>
						</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>