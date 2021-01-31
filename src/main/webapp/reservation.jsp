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
			<form method="post" action="<c:url value='/reservation' />">
				<div class="row register-form justify-content-center">
					<div class="col-md-8">
						<div class="form-group">

							<label class="form-control-label text-muted">Nom : </label> <input
								id="inputTelephone" name="rechercheNom" type="text" size="20"
								maxlength="60" class="form-control mb-3" autofocus />
						</div>

						<select name="sp_id" class="form-select">
							<c:forEach items="${specialites}" var="specia">
								<option value="${specia.id}" ${specia.id == 1 ? 'selected' : ''}>${specia.specialite}</option>
							</c:forEach>
						</select> <select name="ct_id" class="form-select">
							<c:forEach items="${centres}" var="centre">
								<option value="${centre.id}" ${centre.id == 1 ? 'selected' : ''}>${centre.nom}</option>
							</c:forEach>
						</select>
						<div class="row justify-content-center my-3 px-3">
							<button type="submit" name="rechercher" value="submit"
								class="w-100 btn btn-lg btn-outline-success">Rechercher</button>
						</div>
						<%
							UtilisateurDao utilisateurDao = new UtilisateurDao();
						%>
						<%
							CentreDao centreDao = new CentreDao();
						%>
						<%
							SpecialiteDao specialiteDao = new SpecialiteDao();
						%>
						<h4 class="mb-5 mt-3 text-center heading">Résultat de la recherche</h4>
						<div class="row justify-content-center">
							<div class="col-md-8">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th scope="col">Medecin</th>
											<th scope="col">Specialite</th>
											<th scope="col">Centre</th>
											<th scope="col">Adresse</th>
											<th scope="col">Téléphone</th>
										</tr>
									</thead>
									<tbody>
										
										<%
											List<Assignement> assignements = (List<Assignement>) request.getAttribute("assignements");
											if(assignements != null){
												System.out.println("on rentre");
											for (Assignement as : assignements) {
										%>
										<%
											Utilisateur medecin = utilisateurDao.getUtilisateurByID(as.getMedecin_id());
										Centre centre = centreDao.getCentreByID(as.getCentre_id());
										Specialite specialite = specialiteDao.getSpecialiteByID(as.getSpecialite_id());
										%>

										<tr>
											<td>Dr. <%=medecin.getNom() + " " + medecin.getPrenom()%>
											</td>
											<td><%=specialite.getSpecialite()%></td>
											<td><%=centre.getNom()%></td>
											<td><%=centre.getAdresse() %></td>
											<td><%=centre.getTelephone()%></td>
										</tr>
										<%
											}
										}
										%>

									</tbody>
								</table>
							</div>
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