<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RDVmedecin - Modification</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@include file="header.jsp"%>


	<div class="container">
		<div class="col-md-12 col-10">
			<form method="post"
				action="<%=request.getContextPath()%>/modification">
				<h2 class="mb-5 mt-5 text-center heading">Profil de
					${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom}</h2>
				<div class="row register-form justify-content-center">
					<div class="col-md-8">
						<div class="form-group">
							<label class="form-control-label text-muted">Téléphone :
							</label> <input id="inputTelephone" name="telephone" type="tel" size="20"
								maxlength="60" class="form-control mb-3"
								value="${utilisateur.telephone}" required autofocus />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Adresse : </label> <input
								id="inputTelephone" name="adresse" type="text" size="20"
								maxlength="60" class="form-control mb-3"
								value="${utilisateur.adresse}" required autofocus />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Code postal
								: </label> <input id="inputTelephone" name="code_postal" type="number"
								size="20" maxlength="60" class="form-control mb-3"
								value="${utilisateur.code_postal}" required autofocus />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Ville : </label> <input
								id="inputTelephone" name="ville" type="text" size="20"
								maxlength="60" class="form-control mb-3"
								value="${utilisateur.ville}" required autofocus />
						</div>
						<div class="form-group">
							<label class="form-control-label text-muted">Changer de
								mot de passe : </label> <input id="inputTelephone" name="motdepasse"
								type="password" size="20" maxlength="60" value = null
								class="form-control mb-3"  autofocus />
						</div>
					</div>
					<div class="row justify-content-center mt-5">
						<div class="col-md-4">
							<a href="<%=request.getContextPath()%>/profil"
								class="w-100 btn btn-lg btn-secondary">Annuler</a>
						</div>
						<div class="col-md-4">
							<button type="submit" name="submit" value="submit"
										class="w-100 btn btn-lg btn-outline-success">Confirmer</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>


</body>
</html> 