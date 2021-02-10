<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.dao.*"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RDVmedecin - Confirmation RDV</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<%
		Rdv rdv = (Rdv) request.getAttribute("rdv");
	Utilisateur medecin = new Utilisateur();

	medecin = UtilisateurDao.getUtilisateurByID(rdv.getMedecin_id());
	Creneau c = Creneau.valeurIdCreneau(rdv.getCreneau());
	Centre centre = new Centre();

	centre = CentreDao.getCentreByID(rdv.getCentre_id());
	Specialite specialite = new Specialite();

	specialite = SpecialiteDao.getSpecialiteByID(rdv.getSpecialite_id());

	System.out.println(medecin.getNom());
	System.out.println(medecin.getPrenom());
	System.out.println(centre.getNom());
	System.out.println(centre.getAdresse());
	System.out.println(specialite.getSpecialite());
	%>
	<div class="container">
		<div class="col-md-12 col-10">
			<h2 class="mb-5 mt-5 text-center heading">Confirmation de la
				prise de votre rendez-vous</h2>
			<div class="row register-form justify-content-center">
				<div class="col-md-6">
					<h5>
						Médecin : <strong class="text-value">Dr. <%=medecin.getPrenom()%>
							<%=medecin.getNom()%>, <%=specialite.getSpecialite()%></strong>
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
					<h5>
						Date : <strong class="text-value"><%=rdv.getDate()%> </strong>
					</h5>
					<h5>
						Créneau : <strong class="text-value"><%=c.getName()%> </strong>
					</h5>

					Un mail de confirmation vous a été envoyé, vous recevrez un rappel
					un jour avant votre RDV.
				</div>
				<div class="row justify-content-center mt-5">
					<div class="col-md-4 text-center">
						<a href="<c:url value='/espace' />" type="submit"
							class="w-75 btn btn-warning">Retour</a>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>