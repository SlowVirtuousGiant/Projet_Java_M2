<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>RDVmedecin - Login</title>
    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/css/style.css" rel="stylesheet">
</head>
<body class="text-center">
	<main class="form-signin">
    <div class="container">
        <form action="${contextPath}/login" method="POST">
            <h1 class="h3 mb-3 fw-normal">RDVmedecin</h1>
            
            <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <label for="inputEmail" class="visually-hidden">Adresse email</label>
            <input id="inputEmail" name="email" type="email" class="form-control mb-3" placeholder="Adresse email" required autofocus/>
            <label for="inputPassword" class="visually-hidden">Mot de passe</label>
            <input type="password" id="inputPassword" name = "password" class="form-control mb-4" placeholder="Mot de passe" required>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <!-- <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label> -->
             <button name="submit" value="submit" class="w-100 btn btn-lg btn-primary" type="submit">Connexion</button>
            <a href="<c:url value='/inscriptionPatient' />" class="mt-5 mb-3">Pas de compte ? s'inscrire</a>
            </div>
        </form>
   	</div>
   </main>
</body>
<script src="${contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>
</html>