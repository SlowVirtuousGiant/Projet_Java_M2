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
<link rel="stylesheet" href="css/forms.css">
</head>
<body>

	<div class="container">
		<h1 class="navbar-brand text-center text-white bigb2">
			<span class="bigb">RDV</span>medecin.fr
		</h1>
	</div>
	<section>
		<div class="container mx-auto">
			<div class="d-flex flex-lg-row flex-column-reverse">
				<div class="card card1">
					<div class="row justify-content-center my-auto">
						<div class="col-md-12 col-10">
							<form method="post" action="<c:url value='/inscription' />">
								<h2 class="mb-1 text-center heading">Il est temps de
									s'inscrire</h2>
								<div class="alert alert-danger" role="alert">
									${erreur}<br>
								</div>

								<div class="row register-form">
									<div class="col-md-6">

										<div class="form-group">
											<label class="form-control-label text-muted">Nom</label> <input
												id="inputNom" name="nom" type="text" size="20"
												maxlength="60" class="form-control mb-3" placeholder="Nom"
												required autofocus />
										</div>
										<div class="form-group">
											<label class="form-control-label text-muted">Prénom</label> <input
												id="inputPrenom" name="prenom" type="text" size="20"
												maxlength="60" class="form-control mb-3"
												placeholder="Prénom" required />
										</div>
										<div class="form-group">
											<label class="form-control-label text-muted">Téléphone</label>
											<input id="inputTel" name="telephone" type="tel" size="20"
												maxlength="60" class="form-control mb-3"
												placeholder="Téléphone" required />
										</div>
										<div class="form-group">
											<label class="form-control-label text-muted">Adresse
												mail</label> <input id="inputEmail" name="mail" type="email"
												size="20" maxlength="60" class="form-control mb-3"
												placeholder="Adresse mail" required />
										</div>
										<div class="form-group">
											<label class="form-control-label text-muted">Année de
												naissance</label> <input id="inputAnnee" name="naissance"
												type="number" size="20" maxlength="60"
												class="form-control mb-3" placeholder="Année de naissance"
												required />
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="form-control-label text-muted">Adresse</label>
											<input id="inputAdresse" name="adresse" type="text" size="20"
												maxlength="60" class="form-control mb-3"
												placeholder="Adresse" required />
										</div>
										<div class="form-group">
											<label class="form-control-label text-muted">Code
												postal</label> <input id="inputCodepostal" name="code_postal"
												type="number" size="20" maxlength="60"
												class="form-control mb-3" placeholder="Code postal" required />
										</div>
										<div class="form-group">
											<label class="form-control-label text-muted">Ville</label> <input
												id="inputVille" name="ville" type="text" size="20"
												maxlength="60" class="form-control mb-3" placeholder="Ville"
												required />
										</div>
										<div class="form-group">
											<label class="form-control-label text-muted">Mot de
												passe</label> <input id="inputMdp" name="motdepasse" type="password"
												size="20" maxlength="60" class="form-control mb-3"
												placeholder="Mot de passe" required />
										</div>
										<div class="form-group">
											<div class="mt-5">
												<label class="radio inline"> <input type="radio"
													name="sexe" value="femme" class="ml-5 pl-5" required>
													<span> Femme </span>
												</label> <label class="radio inline"> <input type="radio"
													name="sexe" value="homme" required> <span>
														Homme </span>
												</label>
											</div>
										</div>
									</div>
								</div>
								<div class="row justify-content-center my-3 px-3">
									<button type="submit" name="submit" value="submit"
										class="w-100 btn btn-lg btn-outline-success">Inscription</button>
								</div>
								<div class="row justify-content-center my-2">
									<a href="<c:url value='/connexion' />"><small
										class="text-muted">Déjà un compte? Se connecter</small></a>
								</div>
							</form>
						</div>
					</div>

				</div>

			</div>
		</div>
	</section>
</body>
</html>