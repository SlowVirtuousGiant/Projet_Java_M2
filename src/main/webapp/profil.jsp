<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil</title>
</head>
 <body>
 <form method="post" action="<%=request.getContextPath()%>/profil">
    <h3>Profil de ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.prenom}</h3>
 
    Adresse mail : <b>${sessionScope.utilisateur.mail}</b>
    <br />
    Téléphone : <b>${sessionScope.utilisateur.telephone}</b>
    <br />
 	Année de naissance : <b>${sessionScope.utilisateur.naissance}</b>
    <br />
    Sexe : <b>${sessionScope.utilisateur.sexe}</b>
    <br />
    Adresse : <b>${sessionScope.utilisateur.adresse}</b>
    <br />
 	Code postal : <b>${sessionScope.utilisateur.code_postal}</b>
    <br />
 	Ville : <b>${sessionScope.utilisateur.ville}</b>
    <br />
    Role : <b>${sessionScope.utilisateur.role}</b>
    <br />
<a href="<%=request.getContextPath()%>/modification" class="w-100 btn btn-lg btn-outline-light mt-3" >Modifier mon profil</a>
<a href="<%=request.getContextPath()%>/suppression" class="w-100 btn btn-lg btn-outline-light mt-3" >Supprimer mon profil</a>
 </body>
</html>