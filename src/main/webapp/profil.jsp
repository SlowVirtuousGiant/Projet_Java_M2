<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
 <form method="post" action="<%=request.getContextPath()%>/profil">
    <h3>Profil de ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.xu}</h3>
 
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
<a href="<c:url value='/modification' />" class="w-100 btn btn-lg btn-outline-light mt-3" >Modifier mon profil</a>
<a href="<c:url value='/suppression' />" class="w-100 btn btn-lg btn-outline-light mt-3" >Supprimer mon profil</a>
 </body>
</html>