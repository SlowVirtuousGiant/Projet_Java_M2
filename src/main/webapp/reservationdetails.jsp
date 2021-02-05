<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.dao.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil"%>
<%@ page import="java.util.ArrayList"%>

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
				<div class="col-2">
					<form id="dateForm" method="post" action="<c:url value='/reservationdetails'/>">
					<label class="form-control-label text-muted">Date de votre
						consultation : </label> <select name="date_id" class="form-select">
						<%
						ArrayList<String> datesPossibles = TimeMedecinUtil.getNext20Days();
						String sDate = (String) request.getAttribute("selectedDate");
						
						for(String date: datesPossibles){
						%>
							<option <%=(date.equals(sDate) ? "selected":"")%> value="<%=date%>"> <%=date%></option>
						<%}
						%>
					</select>
					</form>
					<div class="list-group mt-4" id="list-tab" role="tablist">
						<a class="list-group-item list-group-item-action active"
							href="#red" data-toggle="tab">Date 1</a>

					</div>
				</div>
				<div class="col-5 mt-3">
							
					<h5>
						Médecin : <strong class="text-value">Dr.${medecin.nom} ${medecin.prenom}, ${specialite.specialite}</strong>
					</h5>
					<h5>
						Centre : <strong class="text-value"> ${centre.nom} </strong>
					</h5>
					<h5>
						Adresse : <strong class="text-value">${centre.adresse} </strong>
					</h5>
					<h5>
						Téléphone : <strong class="text-value">${centre.telephone} </strong>
					</h5>
					<div class="tab-content mt-4">
						<div class="tab-pane fade show active" id="red">
							<div class="card">
								<h5 class="card-header">Résumé de votre rendez-vous</h5>
								<div class="card-body">
									<h5 class="card-title">Le Date à h</h5>
									<p class="card-text">Après votre validation, le rendez-vous
										avec le Dr.${medecin.nom} sera pris en compte.</p>
									<p class="card-text">Vous allez reçevoir un mail de
										confirmation et un mail de rappel un jour avant le
										rendez-vous.</p>
									<a href="#" class="btn btn-primary">Valider</a>
								</div>
							</div>
						</div>
					</div>
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