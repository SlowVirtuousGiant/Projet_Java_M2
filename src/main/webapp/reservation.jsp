<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.dao.*"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RDVmedecin - Reservation</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@include file="header.jsp"%>

	<div class="container">
		<div class="col-md-12 col-10">
			<h2 class="mb-4 mt-5 text-center heading">Recherche</h2>
			<form method="post" action="<c:url value='/reservation' />">
				<div class="row register-form justify-content-center">
					<div class="col-md-5">
						<div class="form-group">

							<label class="form-control-label text-muted">Nom : </label> <input
								id="inputTelephone" name="rechercheNom" type="text" size="20"
								maxlength="60" class="form-control "
								placeholder="Nom du médecin" autofocus />
						</div>

						<label class="form-control-label text-muted mt-3">Spécialité
							du médecin : </label> <select name="sp_id" class="form-select">
							<c:forEach items="${specialites}" var="specia">
								<option value="${specia.id}" ${specia.id == 1 ? 'selected' : ''}>${specia.specialite}</option>
							</c:forEach>

						</select><label class="form-control-label text-muted mt-3">Nom du
							centre : </label> <select name="ct_id" class="form-select">
							<c:forEach items="${centres}" var="centre">
								<option value="${centre.id}" ${centre.id == 1 ? 'selected' : ''}>${centre.nom}</option>
							</c:forEach>
						</select>
						<div class="row justify-content-center my-3 px-3 mt-5">
							<button type="submit" name="rechercher" value="submit"
								class="w-100 btn btn-lg btn-outline-success">Rechercher</button>
						</div>
					</div>
					<%
						UtilisateurDao utilisateurDao = new UtilisateurDao();
					%>
					<%
						CentreDao centreDao = new CentreDao();
					%>
					<%
						SpecialiteDao specialiteDao = new SpecialiteDao();

					List<Assignement> assignements = (List<Assignement>) request.getAttribute("assignements");
					if (assignements != null) {
						if (assignements.size() == 0) { %>
							<h4 class="mb-5 mt-4 text-center heading">Désolé nous n'avons trouvé aucun résultat pour votre recherche.</h4>
						<%} else {
					%>
					<h2 class="mb-5 mt-4 text-center heading">Résultat de la
						recherche</h2>
					<div class="row justify-content-center">
						<div class="col-md-10">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th scope="col">Medecin</th>
										<th scope="col">Specialite</th>
										<th scope="col">Centre</th>
										<th scope="col">Adresse</th>
										<th scope="col">Téléphone</th>
										<th scope="col">Action</th>
									</tr>
								</thead>
								<tbody>

									<%
										for (Assignement as : assignements) {
									%>
									<%
										Utilisateur medecin = utilisateurDao.getUtilisateurByID(as.getMedecin_id());
									Centre centre = centreDao.getCentreByID(as.getCentre_id());
									Specialite specialite = specialiteDao.getSpecialiteByID(as.getSpecialite_id());
									%>

									<tr>
										<td class="text-nowrap">Dr. <%=medecin.getNom() + " " + medecin.getPrenom()%>
										</td>
										<td><%=specialite.getSpecialite()%></td>
										<td><%=centre.getNom()%></td>
										<td><%=centre.getAdresse() + " " + centre.getVille() + " " + centre.getCode_postal()%></td>
										<td><%=centre.getTelephone()%></td>
										<td><button name="submit" value="submit"
												class="btn btn-success">Consulter</button></td>
									</tr>
									<%
										}
									}
									}
									%>

								</tbody>
							</table>
						</div>
					</div>

				</div>
			</form>
		</div>
	</div>
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>