<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<form method="post" action="<%=request.getContextPath()%>/inscription">
Nom:<br>
<input type="text" name="nom" >
<br>
Prénom:<br>
<input type="text" name="prenom" >
<br>
Téléphone:<br>
<input type="text" name="telephone" >
<br>
Adresse mail:<br>
<input type="text" name="mail" >
<br>
Année de naissance:<br>
<input type="text" name="naissance" >
<br>
Adresse:<br>
<input type="text" name="adresse" >
<br>
Code postal:<br>
<input type="text" name="code_postal" >
<br>
Ville:<br>
<input type="text" name="ville" >
<br>
Mot de passe:<br>
<input type="text" name="motdepasse" >
<br><br>
<input type="submit" value="S'inscrire">
</form>
</body>
</html>