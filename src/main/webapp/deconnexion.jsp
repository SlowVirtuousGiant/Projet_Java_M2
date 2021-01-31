<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/index.css">
<meta charset="UTF-8">
<title>RDVmedecin - Déconnexion</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10">
				<h1 style="text-align: center;">Vous êtes bien déconnecté,
					aurevoir ${sessionScope.utilisateur.prenom} !</h1>
			</div>
			<div class="row justify-content-center mt-5">
				<div class="col-md-4">
					<a href="<%=request.getContextPath()%>/"
						class="w-100 btn btn-lg btn-secondary">Retour à la page d'accueil</a>
				</div>
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>
</body>
</html>