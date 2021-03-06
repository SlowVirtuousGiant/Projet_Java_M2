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
					<c:forEach items="${erreur}" var="item">
						<div class="alert alert-danger" role="alert">
							${item}<br>
						</div>
					</c:forEach>
					<div class="col-md-6">
						<h5>
							Adresse mail : <strong class="text-value">${sessionScope.utilisateur.mail}</strong>
						</h5>
						<h5>
							Année de naissance : <strong class="text-value">${sessionScope.utilisateur.naissance}</strong>
						</h5>
						<h5>
							Téléphone : <strong class="text-value">${sessionScope.utilisateur.telephone}</strong>
						</h5>
						<h5>
							Sexe : <strong class="text-value">${sessionScope.utilisateur.sexe}</strong>
						</h5>
						<h5>
							Adresse : <strong class="text-value">${sessionScope.utilisateur.adresse}</strong>
						</h5>
						<h5>
							Code postal : <strong class="text-value">${sessionScope.utilisateur.code_postal}</strong>
						</h5>
						<h5>
							Ville : <strong class="text-value">${sessionScope.utilisateur.ville}</strong>
						</h5>

						<h5 class="mt-5">
							Vous êtes <strong class="text-value">${sessionScope.utilisateur.role}</strong>
						</h5>
					</div>
				</div>
				<div class="row justify-content-center mt-5">
					<div class="col-md-4 text-center">
						<a href="<c:url value='/modification' />" type="submit"
							class="w-75 btn btn-warning">Modifier mon profil</a>
					</div>
					<div class="col-md-4 text-center">
						<a class="w-75 btn btn-danger" data-bs-toggle="modal"
							data-bs-target="#supprModal">Désactiver mon compte</a>
					</div>

				</div>


				<!-- Modal -->
				<div class="modal fade" id="supprModal" data-bs-backdrop="static"
					tabindex="-1" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<form method="post" action="<%=request.getContextPath()%>/profil">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Désactivation
										de votre compte</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">

									<p>Attention la désactivation de votre compte est
										irréversible.</p>
									<p>Après vérification de votre mot de passe vous recevrez
										un lien de confirmation de la désactivation sur votre adresse
										mail.</p>
									<%
										if (role.equals("PATIENT")) {
									%>
									<p>Votre compte sera désactivé et vos rendez-vous annulés.</p>
									<%
										}
									%>

									<%
										if (role.equals("MEDECIN")) {
									%>
									<p>Veuillez annuler vos RDV avant de désactiver votre
										compte.</p>
									<%
										}
									%>


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
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>