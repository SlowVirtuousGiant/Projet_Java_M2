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
		<%@include file="header.jsp"%>

		<section>
			<div class="container">
				<h2>
					Bienvenue <%=utilisateur.getPrenom()%> dans votre espace !</h2>

				<%
					if (utilisateur.getRole().equals("PATIENT")) {
				%>

				<div class="col-md-10 mt-5">
					<h4 class="mt-5 text-value">Prendre un rendez-vous</h4>
					<p class="text-justify">Pour prendre un rendez-vous avec un médecin, c'est très simple vous pouvez consulter l'onglet « Rendez-vous » puis <a href="<c:url value='/reservation' />">« Prendre un rendez-vous »</a>.
					A partir de la recherche il vous suffit de rentrer un nom de médecin, où le nom d'une spécialité, ainsi qu'un des centres disponible.
					Une fois le medecin selectionné, vous n'avez ensuite plus qu'à choisir votre date et votre horaire!</p>
					
					<h4 class="mt-5 text-value">Consulter ou annuler un rendez-vous</h4>
					<p class="text-justify">Dans l'onglet « Rendez-vous » puis <a href="<c:url value='/visualisationrdv' />">« Gérer mes rendez-vous »</a> vous pouvez voir les rendez vous que vous avez pris.
			         Vous avez également la possibilité d'annuler un rendez vous en cours à partir de cette page.</p>
					
					<h4 class="mt-5 text-value">Votre profil</h4>
					<p class="text-justify">Vous pouvez consulter et mettre à jours vos informations dans votre profil. Si vous voulez désactiver votre compte vous pouvez également le faire via cette page.</p>
				<%
					}
					if(utilisateur.getRole().equals("MEDECIN")){%>
						<h4 class="mt-5 text-value">Votre Agenda</h4>
						<p class="text-justify">Votre agenda vous permet d'indiquer vos disponibilités entre vos différents centres, de consulter vos rendez-vous et d'accéder aux informations des patients. Vous pouvez également
						annuler un rendez-vous en précisant un motif.</p>
				
						<h4 class="mt-5 text-value">Votre profil</h4>
						<p class="text-justify">Vous pouvez consulter et mettre à jours vos informations dans votre profil. Si vous voulez désactiver votre compte vous pouvez également le faire via cette page après annulation de vos rendez-vous.</p>
				<%}if (utilisateur.getRole().equals("ADMIN")){%>
					<h4 class="mt-5">Vous êtes administrateur, ne faites pas n'importe quoi! 😉</h4>
				<%} %>
					<p class="mt-5"><small>Travail réalisé par Pierre MONROCQ et Nicolas XU</small></p>
				</div>
				
				
			
			</div>
		</section>
</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>