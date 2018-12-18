<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
  <title>Delete Set</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">


  <!-- Popper JS -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <!-- jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <!-- Latest compiled JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

  <link href="css/style.css" rel="stylesheet" type="text/css">

</head>

<body>

  <!-- menu bar / banner -->
  <jsp:include page="header.jsp"></jsp:include>
  
  <div class="container pad-top">
    <h1 class="display-1 text-center">Flashcards</h1>
  </div>

  <div class="container pad-top text-center">
    <h1>Are you sure you want to delete this set?</h1>
    <h4 class="text-danger">${setname}</h4>
  </div>

  <div class="container pad-top pb-5 mb-5">
    <div class="row justify-content-center">
      <a href="FlashcardController?action=listsets" class="btn btn-outline-secondary">Cancel</a> &nbsp;
      <a href="FlashcardController?action=confirmdeleteset" class="btn btn-danger">Delete</a>
    </div>
  </div>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>