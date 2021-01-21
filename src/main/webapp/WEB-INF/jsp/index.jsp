<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RDVmedecin - Accueil</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    

    <div class="container">
        <h1 class="navbar-brand text-center text-white bigb2"><span class="bigb">RDV</span>medecin.fr</h1>
    </div>    
          

      <section>
        <div class="container px-4 py-5 mx-auto">
          <div class="card card0">
              <div class="d-flex flex-lg-row flex-column-reverse">
                  <div class="card card1">
                      <div class="row justify-content-center my-auto">
                          <div class="col-md-8 col-10 my-5">
                              <h2 class="mb-5 text-center heading">Prendre un rendez-vous <br> maintenant</h2>
                              <h6 class="msg-info">Se connecter</h6>
                              <div class="form-group"> <label class="form-control-label text-muted">Adresse email</label> <input type="text" id="email" name="email" placeholder="Adresse email" class="form-control"> </div>
                              <div class="form-group"> <label class="form-control-label text-muted">Mot de passe</label> <input type="password" id="psw" name="psw" placeholder="Mot de passe" class="form-control"> </div>
                              <div class="row justify-content-center my-3 px-3"> <button name="submit" value="submit" class="w-100 btn btn-lg btn-outline-success">Connexion</button> </div>
                              <div class="row justify-content-center my-2"> <a href="#"><small class="text-muted">Mot de passe oublié?</small></a> </div>
                          </div>
                      </div>
                  </div>
                  <div class="card card2">
                      <div class="my-auto mx-md-5 px-md-5 right text-center">
                          <h2 class="text-white mb-3">Vous n'avez pas encore de compte?</h2> <h4 class="text-white mt-2">Pas de soucis, c'est rapide et gratuit !</h4>
                          <div class="row justify-content-center my-3 px-3"> <button name="submit" value="submit" class="w-100 btn btn-lg btn-outline-light mt-3">Créer un compte</button> </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
      </section>

</body>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>