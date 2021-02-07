<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RDVmedecin - Ajout</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="page-container">
		<%@include file="header.jsp"%>

		<div class="container">
			<div class="col-md-12 col-10">
				<form method="post" action="<c:url value='/ajout' />">
					<h2 class="mb-4 mt-5 text-center heading">Ajouter un médecin</h2>
					<h4 class="mb-4 mt-5 text-center heading">Informations</h4>
					<c:forEach items="${erreur}" var="item">
						<div class="alert alert-danger" role="alert">
							${item}<br>
						</div>
					</c:forEach>


					<div class="row register-form justify-content-center">
						<div class="col-md-4">

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
									maxlength="60" class="form-control mb-3"
									placeholder="Téléphone" required />
							</div>
							<div class="form-group">
								<label class="form-control-label text-muted">Adresse
									mail</label> <input id="inputEmail" name="mail" type="email" size="20"
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
						<div class="col-md-4">
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
								<label class="form-control-label text-muted">Mot de
									passe</label> <input id="inputMdp" name="motdepasse" type="password"
									size="20" maxlength="60" class="form-control mb-1"
									placeholder="Mot de passe" required> <input
									type="checkbox" id="reveal"> Afficher mot de passe
							</div>

							<div class="form-group">
								<div class="mt-4">
									<label class="radio inline"> <input type="radio"
										name="sexe" value="femme" required> <span>
											Femme </span>
									</label> <label class="radio inline"> <input type="radio"
										name="sexe" value="homme" required> <span>
											Homme </span>
									</label>
								</div>
							</div>

						</div>
						<h4 class="mb-5 mt-3 text-center heading">Affectation</h4>
						<div class="row justify-content-center">
							<div class="col-md-8">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th scope="col">Nom du centre</th>
											<th scope="col">Spécialité du medecin dans ce centre</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach items="${centres}" var="item">
											<tr>
												<td>
													<div class="custom-control custom-checkbox">
														<input type="checkbox" class="custom-control-input"
															id="customCheck${item.id}" name="centre_${item.id}">
														<label class="custom-control-label"
															for="customCheck${item.id}">${item.nom}</label>
													</div>
												</td>
												<td><select name="sp_${item.id}" class="form-select">
														<c:forEach items="${specialites}" var="specia">
															<option value="${specia.id}"
																${specia.id == 1 ? 'selected' : ''}>${specia.specialite}</option>
														</c:forEach>
												</select></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>

					</div>

					<div class="row justify-content-center mt-4">
						<div class="col-md-8 mb-5">
							<button type="submit" name="submit" value="submit"
								class="w-100 btn btn-lg btn-outline-success">Nouveau
								medecin</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$("#reveal").on('click', function() {
		var $pw = $("#inputMdp");
		if ($pw.attr('type') === 'password') {
			$pw.attr('type', 'text');
		} else {
			$pw.attr('type', 'password');
		}
	});
</script>
</html>