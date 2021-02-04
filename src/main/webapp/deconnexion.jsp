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
		<h1 class="navbar-brand text-center text-white bigb2">
			<span class="bigb">RDV</span>medecin.fr
		</h1>
	</div>
	<div class="container">
		<div class="row h-100">
			<div class="col-sm-12 my-auto">
				<h1 class="text-white text-center">Vous êtes bien déconnecté,
					à bientôt !</h1>
			</div>
			<div class="row justify-content-center mt-5">
				<div class="col-md-4">
					<a href="<%=request.getContextPath()%>/"
						class="w-100 btn btn-lg btn-light">Retour à la page
						d'accueil</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>