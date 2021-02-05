<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RDVmedecin - Accueil</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="page-container">
		<%@include file="header.jsp"%>

		<section>
			<div class="container">
				<h2>Bienvenue <%= utilisateur.getPrenom() %></h2>
				
				<% if(utilisateur.getRole().equals("PATIENT") || utilisateur.getRole().equals("MEDECIN")){ %>
				<h4 class="mt-5">Vos rendez-vous en cours</h4>

				<div class="col-md-8">
					<div class="bg-light p-5 rounded mt-3">
						<h1>Vous n'avez pas de rendez-vous prévus</h1>
						<p class="lead">Vos prochains rendez-vous apparaitrons ici</p>
						<a class="btn btn-lg btn-primary"
							href="<c:url value='/inscription' />" role="button">Prendre
							un rendez-vous »</a>
					</div>
				</div>
				<% }%>
				<h4 class="mt-5">Vos anciens rendez-vous</h4>
				<div class="col-md-8">
					<ul class="list-group mt-3">
						<li
							class="list-group-item d-flex justify-content-between align-items-center">
							Avec le Dr. Joe Généraliste au centre x <span
							class="badge bg-success">Terminé</span>
						</li>
						<li
							class="list-group-item d-flex justify-content-between align-items-center">
							Avec le Dr. Michel Généraliste au centre x <span
							class="badge bg-danger">Annulé</span>
						</li>
					</ul>
				</div>

			</div>
		</section>
	<%@include file="footer.jsp"%>
	</div>
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>