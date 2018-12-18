<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Popper JS -->
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  	<!-- Latest compiled and minified CSS -->
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  	<!-- jQuery library -->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<!-- Latest compiled JavaScript -->
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

  	<link href="css/style.css" rel="stylesheet" type="text/css">
	<title>Room Bookings | Sheridan Study</title>
</head>
<body>
		
	<jsp:include page="header.jsp"></jsp:include>

	<%
		String comment = (String) session.getAttribute("comment");
		Roombooking booking = (Roombooking) session.getAttribute("booking");
	%>
	
	<div class="container pad-top pb-5">
		<h1 class="display-1 text-center">Room Bookings</h1>
		
		<h4 class="text-center">Booking Successful!</h4>
		<p class="text-center">${comment }</p>
		<p class="text-center pt-3">Date: ${booking.bookingDate}</p>
		<p class="text-center">Start time: ${booking.startat}</p>
		<p class="text-center">End time: ${booking.endat}</p>
		
	</div>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>