<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur" %>
<c:set var="page" value="${requestScope['javax.servlet.forward.request_uri']}" />

<nav class="navbar navbar-expand-md navbar-dark">
	<div class="container-fluid">
		<a class="navbar-brand"><span class="brand">RDV</span>medecin.fr</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				
				<% 
				String role = "";
				Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
				
				if(utilisateur != null){
					role = utilisateur.getRole();
				}
				%>
				
				<li class="nav-item"><a class="nav-link ${page.endsWith('/espace') ? 'active' : ''}"
					aria-current="page" href="<c:url value='/espace' />">Accueil</a></li>
				<% if(role.equals("PATIENT")){ %>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle ${page.endsWith('/reservation') ? 'active' : ''}" href="#" data-bs-toggle="dropdown"
					aria-expanded="false">Rendez-vous</a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="<c:url value='/reservation' />">Prendre un
								rendez-vous</a></li>
						<li><a class="dropdown-item" href="#">Gérer mes
								rendez-vous</a></li>
					</ul></li>
				<%}
				if(!role.equals("ADMIN")){%>
				<li class="nav-item"><a class="nav-link ${page.endsWith('/profil') ? 'active' : ''} }" 
					href="<c:url value='/profil' />">Mon profil</a></li>
				<% }
				if(role.equals("MEDECIN")){ %>
				<li class="nav-item"><a class="nav-link" href="#">Gérer mon
						agenda</a></li>
				<%}
				if(role.equals("ADMIN")){%>
				<li class="nav-item"><a class="nav-link ${page.endsWith('/ajout') ? 'active' : ''}"
					href="<c:url value='/ajout' />">Inscription médecin</a></li>
				<%} %>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/deconnexion' />">Déconnexion</a></li>

			</ul>
		</div>
	</div>
</nav>