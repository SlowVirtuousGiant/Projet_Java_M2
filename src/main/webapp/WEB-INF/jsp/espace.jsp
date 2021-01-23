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
    <nav class="navbar navbar-expand-md navbar-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="#"><span class="brand">RDV</span>medecin.fr</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">Accueil</a>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">Rendez-vous</a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="#">Prendre un rendez-vous</a></li>
                  <li><a class="dropdown-item" href="#">Gérer mes rendez-vous</a></li>
                </ul>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Gérer mon compte</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Gérer mon agenda</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value='/inscriptionmedecin' />">Inscription médecin</a>
              </li>
              <li class="nav-item">
              <a class="nav-link" href="<c:url value='/logout' />">Déconnexion</a>
              </li>

            </ul>
          </div>
        </div>
      </nav>

      <section>
        <div class="container">
        
        <h2>Bienvenue ${user}</h2>
        
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