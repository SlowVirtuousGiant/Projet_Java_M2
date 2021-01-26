<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    	
      <%@include file="header.jsp" %>	
    	
      <section>
        <div class="container">
        
        <h2>Bienvenue ${sessionScope.utilisateur.prenom}, vous êtes ${sessionScope.utilisateur.role}</h2>
        
          <div class="bg-light p-5 rounded">
            <h1>Rendez-vous</h1>
            <p class="lead">Vous n'avez pas de rendez vous pour le moment</p>
            <a class="btn btn-lg btn-primary" href="/docs/5.0/components/navbar/" role="button">Prendre un rendez-vous »</a>
          </div>
        </div>
      </section>

</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>