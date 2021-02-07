<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.dao.*"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>


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
						<div class="col-md-12 mt-3">
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
										<th scope="col">Statut</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody>

									<%
										for (Rdv rdv : rdvs) {
										Utilisateur medecin = UtilisateurDao.getUtilisateurByID(rdv.getMedecin_id());
										Centre centre = CentreDao.getCentreByID(rdv.getCentre_id());
										Specialite specialite = SpecialiteDao.getSpecialiteByID(rdv.getSpecialite_id());
										Creneau c = Creneau.valeurIdCreneau(rdv.getCreneau());
									%>
									<%
										String statutmsg = "";
									LocalDate currentDate = LocalDate.now();
									LocalDate rdvDate = LocalDate.parse(rdv.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
									System.out.println("le rdv num " + rdv.getId() + " est " + rdv.isActif());

									if (rdv.isActif() && rdvDate.isAfter(currentDate)) {
										statutmsg = "RDV à venir";
										//if actif = 1 && rdv.get Date >= current date {A venir}
									} else if (!rdv.isActif() && !rdvDate.isAfter(currentDate)) {
										statutmsg = "RDV passé";
										//if actif = 0 && rdv.get Date <= current date && auteur = null {passée}	
									} else if (!rdv.isActif()) {
										if (rdv.getAuteur().equals("PATIENT")) {
											statutmsg = "RDV annulé par PATIENT : " + rdv.getCommentaire();
											//if actif = 0 && rdv.get Date >= current date && auteur = patient {annulé par patient}
										} else {
											statutmsg = "RDV annulé par DOCTEUR : " + rdv.getCommentaire();
											//if actif = 0 && rdv.get Date >= current date && auteur = docteur {annulé par docteur}
										}
									}
									%>

									<tr>
										<td class="text-nowrap">Dr. <%=medecin.getNom() + " " + medecin.getPrenom()%>
										</td>
										<td><%=specialite.getSpecialite()%></td>
										<td><%=centre.getNom()%></td>
										<td><%=centre.getAdresse() + " " + centre.getVille() + " " + centre.getCode_postal()%></td>
										<td><%=centre.getTelephone()%></td>
										<td class="text-nowrap"><%=rdv.getDate()%></td>
										<td><%=c.getName()%></td>
										<td><%=statutmsg%></td>

										<td>
											<%
												if (rdv.isActif()) {
											%><a class="btn btn-danger"
											href="<c:url value='<%="/annulationrdv?idrdv=" + rdv.getId()%>' />">Annuler</a>
											<%
												}
											%>
										</td>

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