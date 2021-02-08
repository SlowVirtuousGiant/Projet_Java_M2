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
			<div class="row">
				<c:if test="${!empty sessionScope.selectedCentre}">
					<h4 class="text-center mt-3">${selectedCentre.nom} pour la
						semaine ${selectedWeek}</h4>

					<%
						HashMap<String, ArrayList<String>> weeks = TimeMedecinUtil.getDatesByWeekNumber(4);

					String currentWeek = (String) session.getAttribute("selectedWeek");

					ArrayList<String> jours = weeks.get(currentWeek);

					List<Rdv> rdvSemaine = new ArrayList<Rdv>();

					List<Rdv> affectes = new ArrayList<Rdv>();
					
					rdvSemaine = RdvDao.getRdvActifMedecinByWeek(utilisateur.getId(), Integer.valueOf(currentWeek));
					%>
					<div class="container">
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
													idRdv = rdv.getId();
													affectes.add(rdv);
														} else { //le rdv est pris par qqun
													status = "rdv-pris";
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
													<div id="<%=j%>-<%=i%>"class="case <%=status%>">
													</div></td>
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
							</div>
							<div class="row mt-3 mb-5">
								<div class="col">
									<a href="<c:url value='/agenda' />" class="w-100 btn btn-secondary">Retour</a>
								</div>
								<div class="col">
									   <button id="dispo" class="w-100 btn btn-success">Disponible</button>
									
								</div>
								<div class="col">
									   <button id="indispo" class="w-100 btn btn-danger">Indisponible</button>
								</div>
							</div>
						</div>
					</div>
				</c:if>


			</div>
		</div>
	</div>
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
var idArray = [];

$("#dispo").click(function(e){
	$.ajax({
		type: "POST",
		url:"/appMedecin/modificationagenda",
		dataType:'json',
		data: {json:JSON.stringify(idArray), dispo:"dispo"},
		cache: false,
		success:function(response){
            location.reload();
        },
		 error: function(xhr, status, error) {
			 location.reload();
			}
		
		});
});

$("#indispo").click(function(e){
	$.ajax({
		type: "POST",
		url:"/appMedecin/modificationagenda",
		dataType:'json',
		data: {json:JSON.stringify(idArray), indispo:"indispo"},
		cache: false,
		success:function(response){
            location.reload();
        },
		 error: function(xhr, status, error) {
			  location.reload();
		}
		
		});
});

//inspiré de https://stackoverflow.com/questions/4930255/multiple-cells-selection-in-table-in-html
var active = false;

$(".case").mousedown(function(ev) {
	active = true;
	$(".highlight").removeClass("highlight"); // clear previous selection
	ev.preventDefault(); // this prevents text selection from happening
	$(this).addClass("highlight");
});

$(".case").mousemove(function(ev) {
	if (active) {
		$(this).addClass("highlight");
	}
});

$(document).mouseup(function(ev) {
	active = false;
	idArray = [];
	
	$('.highlight').each(function () {
	    idArray.push(this.id);
	});
});


</script>
</html>