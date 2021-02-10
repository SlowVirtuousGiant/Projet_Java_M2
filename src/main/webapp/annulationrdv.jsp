<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.*"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.dao.*"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RDVmedecin - Annnulation RDV</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<%
		String[] idrdv = request.getQueryString().split("=");
	Rdv rdv = RdvDao.getRdvByID(Integer.parseInt(idrdv[1]));

	Utilisateur medecin = new Utilisateur();

	medecin = UtilisateurDao.getUtilisateurByID(rdv.getMedecin_id());
	Creneau c = Creneau.valeurIdCreneau(rdv.getCreneau());
	Centre centre = new Centre();

	centre = CentreDao.getCentreByID(rdv.getCentre_id());
	Specialite specialite = new Specialite();

	specialite = SpecialiteDao.getSpecialiteByID(rdv.getSpecialite_id());
	%>
	<div class="container">
		<div class="col-md-12 col-10">
			<h2 class="mb-5 mt-5 text-center heading">Annulation de votre
				rendez-vous</h2>
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
			</div>
			<div class="row justify-content-center mt-5">
				<div class="col-md-4 text-center">
					<a href="<c:url value='/visualisationrdv' />" type="submit"
						class="w-75 btn btn-warning">Retour</a>
				</div>
				<div class="col-md-4 text-center">
					<a class="w-75 btn  btn-danger" data-bs-toggle="modal"
						data-bs-target="#supprModal">Annuler ce RDV</a>
				</div>
				</div>
			</div>


			<!-- Modal -->
			<div class="modal fade" id="supprModal" data-bs-backdrop="static"
				tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<form method="post"
					action="<%=request.getContextPath()%>/annulationrdv">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Annuler ce
									RDV</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">

								<p>Attention l'annulation de votre RDV est irréversible.</p>
								<p>Ce créneau horaire sera libéré. Vous recevrez un mail de
									confirmation de l'annulation du RDV</p>



								<div class="form-group">
									<label class="form-control-label text-muted">Raison </label> <input
										id="inputWhy" name="raison" type="text" size="20"
										maxlength="60" class="form-control mb-1" value=""
										placeholder="Veuillez expliquer votre raison" required>
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Annuler</button>
								<button type="submit" name="submit" value="submit"
									class=" btn btn-outline-success">Confirmer</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>




</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>