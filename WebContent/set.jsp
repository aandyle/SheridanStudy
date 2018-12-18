<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>Flashcards</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<link href="css/style.css" rel="stylesheet" type="text/css">

</head>

<body>
	
	<!-- menu bar / banner -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="container pad-top">
		<h1 class="display-1 text-center">Flashcards</h1>
	</div>
	
	<div class="container pad-top">
		<div class="row">
			<div class="col-sm-6">
				<h2>${setname }</h2>
			</div>
			<div class="col-sm-6 text-right">
				<c:if test="${fn:length(cards) > 0 }">
					<a href="FlashcardController?action=startquiz&setid=${setid}" class="btn btn-primary">Start Quiz</a> 
				</c:if>
				<a href="FlashcardController?action=addcard&setid=${setid}" class="btn btn-primary">Add Card</a>
				<a href="FlashcardController?action=listsets" class="btn btn-secondary">Back</a>
			</div>
		</div>
	</div>

	<div class="container pad-top pb-5 mb-5">
		<div class="card-columns">

			<c:forEach var="card" items="${cards}">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">${card.frontText }</h5>
						<p class="card-text">${card.backText }</p>
						<a href="FlashcardController?action=editcard&cardid=${card.cardId }" class="btn btn-outline-primary">Edit</a> 
						<a href="FlashcardController?action=deletecard&cardid=${card.cardId}"	class="btn btn-outline-danger">Delete</a>
					</div>
				</div>
			</c:forEach>
			
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>