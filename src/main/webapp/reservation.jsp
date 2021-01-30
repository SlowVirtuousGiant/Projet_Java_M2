<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="form-group">
		<form method="post" action="<c:url value='/reservation' />">
			<label class="form-control-label text-muted">Nom : </label> <input
				id="inputTelephone" name="rechercherNom" type="text" size="20"
				maxlength="60" class="form-control mb-3" autofocus />
	</div>

	<select name="sp_id" class="form-select">
		<c:forEach items="${specialites}" var="specia">
			<option value="${specia.id}" ${specia.id == 1 ? 'selected' : ''}>${specia.specialite}</option>
		</c:forEach>
	</select>

	<select name="ct_id" class="form-select">
		<c:forEach items="${centres}" var="centre">
			<option value="${centre.id}" ${centre.id == 1 ? 'selected' : ''}>${centre.nom}</option>
		</c:forEach>
	</select>
	<div class="row justify-content-center my-3 px-3">
		<button type="submit" name="rechercher" value="submit"
			class="w-100 btn btn-lg btn-outline-success">Rechercher</button>
	</div>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Specialite</th>
			<th>Téléphone</th>
			<th>Adresse</th>
			<th>Jour</th>
			<th>Créneau</th>
		</tr>
	</table>
</body>
</html>