<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.dao.*"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RDVmedecin - Visualisation</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@include file="header.jsp"%>

	<div class="container">
		<div class="col-md-12 col-15">
			<h2 class="mb-4 mt-5 text-center heading">Mes rendez-vous</h2>
			<form method="post" action="<c:url value='/visualisationrdv' />">
				<div class="row register-form justify-content-center">

					<%
						UtilisateurDao utilisateurDao = new UtilisateurDao();
					%>
					<%
						CentreDao centreDao = new CentreDao();
					%>
					<%
						SpecialiteDao specialiteDao = new SpecialiteDao();

					List<Rdv> rdvs = (List<Rdv>) request.getAttribute("rdvs");
					if (rdvs != null) {
						if (rdvs.size() == 0) {
					%>
					<h4 class="mb-5 mt-4 text-center heading">Vous n'avez pas de
						rendez-vous.</h4>
					<%
						} else {
					%>

					<div class="row justify-content-center mb-5">
						<div class="col-md-10">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th scope="col">Medecin</th>
										<th scope="col">Specialite</th>
										<th scope="col">Centre</th>
										<th scope="col">Adresse</th>
										<th scope="col">Téléphone</th>
										<th scope="col">Date</th>
										<th scope="col">Créneau</th>
										<th scope="col">Visualiser</th>
									</tr>
								</thead>
								<tbody>

									<%
										for (Rdv rdv : rdvs) {
									%>
									<%
										Utilisateur medecin = utilisateurDao.getUtilisateurByID(rdv.getMedecin_id());
									Centre centre = centreDao.getCentreByID(rdv.getCentre_id());
									Specialite specialite = specialiteDao.getSpecialiteByID(rdv.getSpecialite_id());
									Creneau c = Creneau.valeurIdCreneau(rdv.getCreneau());
									%>

									<tr>
										<td class="text-nowrap">Dr. <%=medecin.getNom() + " " + medecin.getPrenom()%>
										</td>
										<td><%=specialite.getSpecialite()%></td>
										<td><%=centre.getNom()%></td>
										<td><%=centre.getAdresse() + " " + centre.getVille() + " " + centre.getCode_postal()%></td>
										<td><%=centre.getTelephone()%></td>
										<td class="text-nowrap"><%=rdv.convertToLocalDateViaSqlDate()%></td>
										<td><%=c.getName()%></td>
										<td><a class="btn btn-success" href="<c:url value='<%= "/annulationrdv?idrdv=" + rdv.getId() %>' />">Annuler</a></td>
										</div>
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