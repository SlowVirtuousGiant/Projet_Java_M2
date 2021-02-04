<%@page import="fr.dauphine.sj.monrocqxu.appMedecin.model.Creneau"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page
	import="fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RDVmedecin - Profil</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="page-container">
		<%@include file="header.jsp"%>

		<div class="container">
			<div class="col-md-12 col-10">

				<h2 class="mt-5 text-center heading">Votre agenda</h2>
				<div class="row justify-content-center">
					<div class="col-md-8">

						<c:choose>
							<c:when test="${fn:length(centres) gt 50}">
								<label class="form-control-label text-muted mt-3">Vos
									centres : </label>
								<select name="centre_id" class="form-select">
									<c:forEach items="${centres}" var="centre">
										<option value="${centre.id}"
											${centre.id == 1 ? 'selected' : ''}>${centre.nom}</option>
									</c:forEach>
								</select>
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
					<c:if test="${!empty sessionScope.selectedCentre}">
						<h4 class="text-center">${selectedCentre.nom}
							<br> pour la semaine ${selectedWeek}
						</h4>
						<%
							HashMap<String, ArrayList<String>> weeks = TimeMedecinUtil.getDatesByWeekNumber(4);

						ArrayList<String> jours = weeks.get(session.getAttribute("selectedWeek"));
						%>

						<div class="col-md-8 mt-3 pb-5">
							<table class="table table-bordered border-success table-hover">
								<thead>
									<tr>
										<th>Horaires</th>
										<%
											for (String j : jours) {
										%>
										<th scope="col"><%=j%></th>
										<%
											}
										%>
									</tr>
								</thead>
								<tbody>
									<%
										for (int i = 1; i < 24; i++) {
									%>
									<tr>
										<%
											for (int j = 0; j < 8; j++) {
											if (j == 0) {
										%>
										<td class=<%=i % 2 == 0 ? "horaire" : "horaire-alt"%>>
											<%
												Creneau c = Creneau.valeurIdCreneau(i);
											out.println(c.name);
											%>
										</td>
										<%
											} else {
										%>
										<td></td>
										<%
											}
										}
										%>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<div class="col-md-8 mt-5">
							<input
								type="checkbox" id="reveal"> Agenda actif
							<select name="centre_id" class="form-select mt-5">

							</select> <a href="#" type="submit" class="btn btn-lg btn-warning mt-5">Editer votre agenda</a>
						</div>

					</c:if>


				</div>
			</div>
		</div>

	</div>
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>