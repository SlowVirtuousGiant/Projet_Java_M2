
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html>
<html>
<body>
<form method="post" action="<%=request.getContextPath()%>/ajout">
Nom:<br>
<input type="text" name="nom" value="fraise">
<br>
Prénom:<br>
<input type="text" name="prenom" value = "charlotte">
<br>
Téléphone:<br>
<input type="text" name="telephone" value = "0622334455">
<br>
Adresse mail:<br>
<input type="text" name="mail" value = "charlotte@gmail.com">
<br>
Année de naissance:<br>
<input type="text" name="naissance" value = 1985>
<br>
Adresse:<br>
<input type="text" name="adresse" value ="5 rue de la paix">
<br>
Code postal:<br>
<input type="text" name="code_postal" value = "75017">
<br>
Ville:<br>
<input type="text" name="ville" value = "Paris">
<br>
Mot de passe:<br>
<input type="text" name="motdepasse" value = "charlotte">
<br><br>
<input type="submit" value="Inscrire">
</form>
</body>
</html>