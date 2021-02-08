<%@page import="fr.dauphine.sj.monrocqxu.appMedecin.model.Creneau"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page
	import="fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RDVmedecin - Agenda</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="page-container">
		<%@include file="header.jsp"%>

		<div class="container">
			<h2 class="mt-5 text-center heading">Votre agenda</h2>
			<div class="row justify-content-center">
				<div class="col-md-4">
					<c:choose>
						<c:when test="${fn:length(centres) gt 1}">
							<label class="form-control-label text-muted mt-3">Vos
								centres : </label>
							<form id="selectCentreForm" method="post" action="<c:url value='/agenda'/>">
							<select id="centreSelect" name="centreSelect" class="form-select">
									<option value="placeholder">Sélectionnez un centre</option>
								<c:forEach items="${centres_utilisateur}" var="centre">
									<option value="${centre.id}"
										${centre.id == selectedCentre.id ? 'selected' : ''}>${centre.nom}</option>
								</c:forEach>
							</select>
							</form>
						</c:when>
						<c:otherwise>
							<c:if test="${empty sessionScope.selectedCentre}">
								<c:set var="selectedCentre" value="${centres[0]}"
									scope="session" />
								<%
									session.setAttribute("selectedWeek", TimeMedecinUtil.getCurrentWeek());
								%>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
				<% Centre selectedCentre = (Centre) session.getAttribute("selectedCentre");
				if(selectedCentre != null){
					
					HashMap<String, ArrayList<String>> weeks = TimeMedecinUtil.getDatesByWeekNumber(4);

					String currentWeek = (String) session.getAttribute("selectedWeek");

					ArrayList<String> jours = weeks.get(currentWeek);

					List<Rdv> rdvSemaine = new ArrayList<Rdv>();

					List<Rdv> affectes = new ArrayList<Rdv>();
					int init = 1;
					//regarder dans la bdd
					
					boolean agendaDispo = AffectationDao.agendaActif(selectedCentre.getId(), utilisateur.getId());
					if (init == 1) {
						rdvSemaine = RdvDao.getRdvActifMedecinByWeek(utilisateur.getId(), Integer.valueOf(currentWeek));
					}
					%>
					
					<% if(agendaDispo){ %>
					<h4 class="text-center mt-3">${selectedCentre.nom} pour la
						semaine ${selectedWeek}</h4>
					<div class="container <%=init == 1 ? "" : "no-init"%>">
						<div class="col-md-12 mt-3">
							<div class="container-actif">
								<div class="table-responsive mt-2 mb-3">

									<table class="table table-bordered border-success table-fixed">
										<thead>
											<tr>
												<th>Horaires</th>
												<%
													for (String j : jours) {
												%>
												<th class="th-sm"><%=j%></th>
												<%
													}
												jours.add(0, "horaire");
												%>
											</tr>
										</thead>
										<tbody>
											<%
												for (int i = 1; i < 24; i++) {
											%>
											<tr>
												<%
													int j_id = 0;
												for (String j : jours) {

													if (j_id == 0) {
												%>
												<td class=<%=i % 2 == 0 ? "horaire" : "horaire-alt"%>>
													<%
														Creneau c = Creneau.valeurIdCreneau(i);
													out.println(c.name);
													%>
												</td>
												<%
													} else {

												String status = "";
												int idRdv = 0;
												for (Rdv rdv : rdvSemaine) {
													if (!affectes.contains(rdv) && rdv.getDate().equals(j) && rdv.getCreneau() == i) {//le rdv est pris
														if (rdv.getPatient_id() == utilisateur.getId()) {//le medecin est occupe
													status = "no-dispo";
													affectes.add(rdv);
														} else { //le rdv est pris par qqun
													status = "rdv-pris clic";
													affectes.add(rdv);
													idRdv = rdv.getId();
														}
													}
												}
												if (status.equals("")) {
													status = "libre";
												}
												%>
												<td style="position: relative;">
													<% String u = "data-bs-toggle='modal' data-bs-target='#modal"+ idRdv +"'"; %>
													<a <%= idRdv != 0 ? u : "" %> class="case <%=status%>">
													</a></td>
												<%
													}
												j_id++;
												}
												%>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
								<%
									if (init != 1) {
								%>
								<form method="post" action="<c:url value='/agenda'/>">
								<button type="submit" class="btn btn-lg btn-primary">Initialiser
									cette semaine</button>
									<input type="hidden" name="init" value="1">
								</form>
								<%
									}
								%>
							</div>
							<%
						for (Rdv rdv : rdvSemaine) {
							
							Utilisateur patient = UtilisateurDao.getUtilisateurByID(rdv.getPatient_id());
							Creneau cr = Creneau.valeurIdCreneau(rdv.getCreneau());
							if(patient.getId() != rdv.getMedecin_id()){
						%>
						<div class="modal fade" id="modal<%=rdv.getId() %>"
						tabindex="-1" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Rendez vous du <%=rdv.getDate() %> à <%=cr.getName() %></h5>
									</div>
									<div class="modal-body">
										<p><strong>Patient : </strong><%= patient.getPrenom() + " "+ patient.getNom() + ", "+ (patient.getSexe().equals("homme") ? "né" : "née") + " en " + patient.getNaissance()%></p>
										<p><strong>Adresse : </strong><%= patient.getAdresse() + " " + patient.getVille() + " " + patient.getCode_postal() %></p>
										<p><strong>Téléphone : </strong><%=patient.getTelephone()%></p>
										
									</div>
									<div class="modal-footer">
									<button type="submit" name="submit" value="submit"
										class=" btn btn-danger">Annuler ce rendez-vous</button>
								</div>
							</div>
						</div>
						</div>
							
						<%
							}
							}
							}else{%>
								<h4 class="text-center mt-3">${selectedCentre.nom}</h4>
							<%}%>
							<div class="row mt-3 mb-5">
								<% if(agendaDispo){ %>
								<div class="col">
								<form id="dateForm" method="post" action="<c:url value='/agenda'/>">
									<select id="weekSelect" class="form-select" name="selectedWeek">
										<% ArrayList<String> nextweeks = TimeMedecinUtil.getNext4weeks();
										for(String w : nextweeks){%>
											<option value="<%=w%>" <%= w.equals(currentWeek) ? "selected" : "" %>>Semaine <%=w%></option>
										<%} %>
									</select>
								</form>
								</div>
								<div class="col">
									<a href="<c:url value='/modificationagenda'/>" class="w-100 btn btn-secondary">Editer
										l'agenda</a>
								</div>
								<%} %>
								<div class="col">
									<form class="text-center" id="agendaActifForm" method="post" action="<c:url value='/agenda'/>">
									<input name="checkboxAgenda" class="form-check-input" type="checkbox"
										id="checkboxAgenda" <%= agendaDispo ? "checked" : ""  %>> Agenda Actif
										<input type="hidden" name="majAgenda" value="maj">
									</form>
								</div>
							</div>
						</div>


					</div>
					
						<%
							
				}
						%>


			</div>
		</div>
	</div>
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
$('#weekSelect').on('change', function() {
	$('#dateForm').submit();
});
$('#centreSelect').on('change', function() {
	if($(this).val() != "placeholder"){
		$('#selectCentreForm').submit();
	}
	
});
$('#checkboxAgenda').on('change', function() {
	$('#agendaActifForm').submit();
});


</script>
</html>