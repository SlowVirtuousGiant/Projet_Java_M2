<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RDVmedecin - Inscription</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@include file="header.jsp"%>

	<div class="container">
		<div class="col-md-12 col-10">
			<form method="post" action="<%=request.getContextPath()%>/ajout">
				<h2 class="mb-4 mt-5 text-center heading">Ajouter un médecin</h2>
				<c:forEach items="${erreur}" var="item">
					<div class="alert alert-danger" role="alert">
						${item}<br>
					</div>
				</c:forEach>


				<div class="row register-form justify-content-center">
					<div class="col-md-3">

						<div class="form-group">
							<label class="form-control-label text-muted">Nom</label> <input
								id="inputNom" name="nom" type="text" size="20" maxlength="60"
								class="form-control mb-3" placeholder="Nom" required autofocus />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Prénom</label> <input
								id="inputPrenom" name="prenom" type="text" size="20"
								maxlength="60" class="form-control mb-3" placeholder="Prénom"
								required />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Téléphone</label> <input
								id="inputTel" name="telephone" type="tel" size="20"
								maxlength="60" class="form-control mb-3" placeholder="Téléphone"
								required />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Adresse mail</label>
							<input id="inputEmail" name="email" type="email" size="20"
								maxlength="60" class="form-control mb-3"
								placeholder="Adresse mail" required />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Année de
								naissance</label> <input id="inputAnnee" name="naissance" type="number"
								size="20" maxlength="60" class="form-control mb-3"
								placeholder="Année de naissance" required />
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label class="form-control-label text-muted">Adresse</label> <input
								id="inputAdresse" name="adresse" type="text" size="20"
								maxlength="60" class="form-control mb-3" placeholder="Adresse"
								required />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Code postal</label>
							<input id="inputCodepostal" name="code_postal" type="number"
								size="20" maxlength="60" class="form-control mb-3"
								placeholder="Code postal" required />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Ville</label> <input
								id="inputVille" name="ville" type="text" size="20"
								maxlength="60" class="form-control mb-3" placeholder="Ville"
								required />
						</div>

						<div class="form-group">
							<label class="form-control-label text-muted">Mot de passe</label>
								<input id="inputMdp" name="motdepasse" type="password" size="20"
								maxlength="60" class="form-control mb-1"
								placeholder="Mot de passe" required>
								<input type="checkbox" id="reveal"> Afficher mot de passe
						</div>
						
						<div class="form-group">
							<div class="mt-4">
								<label class="radio inline"> <input type="radio"
									name="gender" value="female" required>
									<span> Femme </span>
								</label> <label class="radio inline"> <input type="radio"
									name="gender" value="male" required> <span>
										Homme </span>
								</label>
							</div>
						</div>
					</div>
				</div>

				<div class="row justify-content-center mt-4">
					<div class="col-md-6">
						<button type="submit" name="submit" value="submit"
							class="w-100 btn btn-lg btn-outline-success">Nouveau
							medecin</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
$("#reveal").on('click',function() {
    var $pw = $("#inputMdp");
    if ($pw.attr('type') === 'password') {
        $pw.attr('type', 'text');
    } else {
        $pw.attr('type', 'password');
    }
});
</script>
</html>