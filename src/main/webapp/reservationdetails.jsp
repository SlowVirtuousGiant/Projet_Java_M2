<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.dao.*"%>
<%@ page
	import="fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
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
			<%
							
				String sDate = (String) request.getAttribute("selectedDate");
				
				String aff = (String) session.getAttribute("affectation");
	
				Affectation affectation = AffectationDao.getAffectationByID(Integer.parseInt(aff));
				
				List<String> foundDates = (List<String>) request.getAttribute("foundDates");
	
				if(!foundDates.isEmpty()){%>
				
				<div class="col-3">
					<form id="dateForm" method="post"
						action="<c:url value='/reservationdetails'/>">
							<label class="form-control-label text-muted">Date de votre consultation : </label> <select name="date_id" class="form-select">
							<%
							
							for (String date : foundDates) {%>
									<option <%=(date.equals(sDate) ? "selected" : "")%>
									value="<%=date%>">
									<%=date%></option>
							<%		
								}
							
							Utilisateur medecin = UtilisateurDao.getUtilisateurByID(affectation.getMedecin_id());

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
						<a class="list-group-item list-group-item-action" href=#<%=c.id%>
							data-toggle="tab"><%=c.name%></a>
						<%
							}
						}
						%>
					</div>
				</div>
				<div class="col-6 mt-3">

					<h5>
						M??decin : <strong class="text-value">Dr. <%=medecin.getPrenom()%>
							<%=medecin.getNom()%>, <%=specialite.getSpecialite()%>
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
						T??l??phone : <strong class="text-value"><%=centre.getTelephone()%>
						</strong>
					</h5>
					<%
						for (Creneau c : Creneau.values()) {
					%>

					<div class="tab-content mt-4">
						<div class="tab-pane fade show" id=<%=c.id%>>
							<div class="card">
								<h5 class="card-header">R??sum?? de votre rendez-vous</h5>
								<div class="card-body">
									<h5 class="card-title">
										Le
										<%=sDate%>
										entre
										<%=c.name%></h5>
									<p class="card-text">
										Apr??s votre validation, le rendez-vous avec le Dr.
										<%=medecin.getNom()%>
										sera pris en compte.
									</p>
									<p class="card-text">Vous allez re??evoir un mail de
										confirmation et un mail de rappel un jour avant le
										rendez-vous.</p>
									<form method="post"
										action="<c:url value='/reservationdetails'/>">
										<button type="submit" class="btn btn-primary" >Valider</button>
										<input type="hidden" name="rdvDate" value=<%=sDate%>>
										<input type="hidden" name="rdvCreneau" value=<%=c.id%>>
										<input type="hidden" name="rdvSpecialite"
											value=<%=specialite.getId()%>>
									</form>

								</div>
							</div>
						</div>
					</div>
					<%
						}
					%>
				</div>
				<%}else{%>
					<h3 class="text-center">D??sol??, nous n'avons trouv?? aucun rendez-vous possible pour ce m??decin.</h3>
					<div class="col-6 mt-3 text-center">
						<a href="<c:url value='/reservation' />" class="btn btn-secondary">Retour ?? la recherche</a>
					</div>
				<% }%>
				
			</div>
		</div>

	</div>
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
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