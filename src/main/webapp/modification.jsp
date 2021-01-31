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

			<h2 class="mb-5 mt-5 text-center heading">Profil de
				${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h2>
			<div class="row register-form justify-content-center">
				<div class="col-md-8">
					<div class="form-group">
						<label class="form-control-label text-muted">Téléphone : </label>
						<input id="inputTelephone" name="telephone" type="tel" size="20"
							maxlength="60" class="form-control mb-3"
							value="${utilisateur.telephone}" required autofocus />
					</div>
					<div class="form-group">
						<label class="form-control-label text-muted">Adresse : </label> <input
							id="inputAdresse" name="adresse" type="text" size="20"
							maxlength="60" class="form-control mb-3"
							value="${utilisateur.adresse}" required autofocus />
					</div>
					<div class="form-group">
						<label class="form-control-label text-muted">Code postal :
						</label> <input id="inputCodepostal" name="code_postal" type="number"
							size="20" maxlength="60" class="form-control mb-3"
							value="${utilisateur.code_postal}" required autofocus />
					</div>
					<div class="form-group">
						<label class="form-control-label text-muted">Ville : </label> <input
							id="inputVille" name="ville" type="text" size="20" maxlength="60"
							class="form-control mb-3" value="${utilisateur.ville}" required
							autofocus />
					</div>
					<div class="form-group">
						<label class="form-control-label text-muted">Changer de
							mot de passe : </label> <input id="inputMotdepasse" name="newmotdepasse"
							type="password" size="20" maxlength="60" value=""
							class="form-control mb-3" autofocus />
					</div>
				</div>
				<div class="row justify-content-center mt-5">
					<div class="col-md-4">
						<a href="<%=request.getContextPath()%>/modification"
							class=" btn  btn-secondary">Annuler</a>
					</div>
					<div class="col-md-4">
						<a class=" btn  btn-danger" data-bs-toggle="modal"
							data-bs-target="#modifModal">Confirmer les modifications</a>
					</div>
				</div>
				
				<div class="modal fade" id="modifModal" data-bs-backdrop="static"
					tabindex="-1" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<form method="post"
						action="<%=request.getContextPath()%>/modification">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Désactivation
										de votre compte</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">

									<p>Modification.</p>
									<p>Veuillez rentrer votre ancien mot de passe.</p>



									<div class="form-group">
										<label class="form-control-label text-muted">Mot de
											passe</label> <input id="inputMdp" name="motdepasse" type="password"
											size="20" maxlength="60" class="form-control mb-1"
											placeholder="Mot de passe" required>
									</div>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Annuler</button>
									<button type="submit" name="submit" value="submit"
										class=" btn btn-outline-success">Confirmer</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>


</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>
