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

	<div class="container mb-5">
		<div class="col-md-12 col-10">
			<h2 class="mb-4 mt-5 text-center heading">Recherche</h2>
			<form method="post" action="<c:url value='/reservation' />">
				<div class="row register-form justify-content-center">
					<div class="col-md-5">
						<div class="form-group">
							<label class="form-control-label text-muted">Nom : </label> <input
								id="inputTelephone" name="rechercheNom" type="text" size="20"
								maxlength="60" class="form-control "
								placeholder="Nom du médecin" value="${rechercherValue}"
								autofocus />
						</div>

						<label class="form-control-label text-muted mt-3">Spécialité
							du médecin : </label> <select name="sp_id" class="form-select">
							<option value="0">Selectionnez une specialite</option>
							<c:forEach items="${sessionScope.specialites}" var="specia">
								<option value="${specia.id}"
									${selectedSpecia eq specia.id ? 'selected' : ''}>${specia.specialite}</option>
							</c:forEach>

						</select> <label class="form-control-label text-muted mt-3">Centres
							disponibles : </label>
						<%
							List<Centre> centres = (List<Centre>) session.getAttribute("centres");
						for (Centre c : centres) {
						%>
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								value=<%=c.getId()%> name="selectedCentre"
								<%String[] sel = (String[]) request.getAttribute("selectedCentre");
									if (sel != null) {
										for (String s : sel) {
											if (s.equals(String.valueOf(c.getId()))) {
												out.print("checked");
												break;
											}
										}
									}%>>
							<label class="form-check-label" for="flexCheckDefault"> <%=c.getNom()%></label>
						</div>
						<%
							}
						%>
						<div class="row justify-content-center my-3 px-3 mt-5">
							<button type="submit" name="rechercher" value="submit"
								class="w-100 btn btn-lg btn-outline-success">Rechercher</button>
						</div>
					</div>
					<%
						List<Affectation> affectations = (List<Affectation>) request.getAttribute("affectations");
					if (affectations != null) {
						if (affectations.size() == 0) {
					%>
					<h4 class="mb-5 mt-4 text-center heading">Désolé nous n'avons
						trouvé aucun résultat pour votre recherche.</h4>
					<%
						} else {
					%>
					<h2 class="mb-5 mt-4 text-center heading">Résultat de la
						recherche</h2>
					<div class="row justify-content-center mb-5">
						<div class="col-md-10">
							<table class="table table-striped table-hover results">
								<thead>
									<tr>
										<th scope="col">Medecin</th>
										<th scope="col">Specialite</th>
										<th scope="col">Centre</th>
										<th scope="col">Adresse</th>
										<th scope="col">Téléphone</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody>

									<%
										for (Affectation as : affectations) {
										Utilisateur medecin = UtilisateurDao.getUtilisateurByID(as.getMedecin_id());
										Centre centre = CentreDao.getCentreByID(as.getCentre_id());
										Specialite specialite = SpecialiteDao.getSpecialiteByID(as.getSpecialite_id());
										if (medecin.isActif()) {
									%>

									<tr>
										<td class="text-nowrap">Dr. <%=medecin.getNom() + " " + medecin.getPrenom()%>
										</td>
										<td><%=specialite.getSpecialite()%></td>
										<td><%=centre.getNom()%></td>
										<td><%=centre.getAdresse() + " " + centre.getVille() + " " + centre.getCode_postal()%></td>
										<td><%=centre.getTelephone()%></td>
										<td><a
											href="<c:url value='<%="/reservationdetails?a=" + as.getId()%>' />"
											class="btn btn-success">Consulter</a></td>
									</tr>
									<%
										}
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