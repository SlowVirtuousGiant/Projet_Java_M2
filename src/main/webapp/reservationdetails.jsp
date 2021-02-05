<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.dao.*"%>
<%@ page
	import="fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil"%>
<%@ page import="java.util.ArrayList"%>
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
			<h2 class="mb-4 mt-5 text-center heading">Prendre un rendez-vous</h2>

			<div class="row justify-content-center mt-5">
				<div class="col-3">
					<form id="dateForm" method="post"
						action="<c:url value='/reservationdetails'/>">
						<label class="form-control-label text-muted">Date de votre
							consultation : </label> <select name="date_id" class="form-select">
							<%
								ArrayList<String> datesPossibles = TimeMedecinUtil.getNext20Days();
							String sDate = (String) request.getAttribute("selectedDate");

							for (String date : datesPossibles) {
							%>
							<option <%=(date.equals(sDate) ? "selected" : "")%>
								value="<%=date%>">
								<%=date%></option>
							<%
								}
							String aff = (String) session.getAttribute("affectation");

							Affectation affectation = AffectationDao.getAffectationByID(Integer.parseInt(aff));

							UtilisateurDao utilisateurDao = new UtilisateurDao();
							Utilisateur medecin = utilisateurDao.getUtilisateurByID(affectation.getMedecin_id());

							Centre centre = CentreDao.getCentreByID(affectation.getCentre_id());

							Specialite specialite = SpecialiteDao.getSpecialiteByID(affectation.getSpecialite_id());
							
							List<Integer> rdvreserves = RdvDao.getNonPossibleRdvByDate(sDate, medecin);
							
							Utilisateur patient = (Utilisateur) session.getAttribute("utilisateur");
							List<Integer> rdvpatient = RdvDao.getCreneauxRdvPatient(patient, sDate);
							%>
						</select>
					</form>
					<div class="list-group mt-4" id="list-tab" role="tablist">
						<%
							for (Creneau c : Creneau.values()) {
							if (!rdvreserves.contains(c.id) && !rdvpatient.contains(c.id)) {
						%>
						<a class="list-group-item list-group-item-action"
							href=<%=c.id%> data-toggle="tab"><%=c.name%></a>
						<%
							}
						}
						%>
					</div>
				</div>
				<div class="col-6 mt-3">

					<h5>
						Médecin : <strong class="text-value">Dr.<%=medecin.getPrenom()%>
							<%=medecin.getNom()%>, <%= specialite.getSpecialite()%>
						</strong>
					</h5>
					<h5>
						Centre : <strong class="text-value"> <%=centre.getNom()%>
						</strong>
					</h5>
					<h5>
						Adresse : <strong class="text-value"><%=centre.getAdresse()%>
						</strong>
					</h5>
					<h5>
						Téléphone : <strong class="text-value"><%=centre.getTelephone()%>
						</strong>
					</h5>
					<% for (Creneau c : Creneau.values()) {
						%>
					
					<div class="tab-content mt-4">
						<div class="tab-pane fade show" id=<%=c.id%>>
							<div class="card">
								<h5 class="card-header">Résumé de votre rendez-vous</h5>
								<div class="card-body">
									<h5 class="card-title">Le <%= sDate %> entre <%=c.name %></h5>
									<p class="card-text">
										Après votre validation, le rendez-vous avec le Dr. <%=medecin.getNom()%>
										sera pris en compte.
									</p>
									<p class="card-text">Vous allez reçevoir un mail de
										confirmation et un mail de rappel un jour avant le
										rendez-vous.</p>
									<form method="post" action="<c:url value='/reservationdetails'/>">
									<button type="submit" class="btn btn-primary">Valider</button>
									<input type="hidden" name="rdvDate" value=<%=sDate%>>
									<input type="hidden" name="rdvCreneau" value=<%=c.id%>>
									<input type="hidden" name="rdvSpecialite" value=<%=specialite.getId()%>>
									
									</form>
									
								</div>
							</div>
						</div>
					</div>
					<%} %>
				</div>
			</div>
		</div>

	</div>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script>
	$(".list-group-item").click(function() {
		var x = $(this).attr("href");
		x = x.replace("#", "");
		$(".tab-content .tab-pane").each(function() {
			var y = $(this).attr("id");
			if (x == y)
				$(this).addClass("active show");
			else
				$(this).removeClass("active show");
		});
	});
	$('select').on('change', function() {
		$('#dateForm').submit();
	});
</script>

</html>