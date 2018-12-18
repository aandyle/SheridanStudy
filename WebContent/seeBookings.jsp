<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	<div class="container pad-top">
		<h1 class="display-1 text-center">Room Bookings</h1>
	</div>
	
	<div class="container pad-top pb-5 mb-5">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">Booking ID</th>
					<th scope="col">User ID</th>
					<th scope="col">Start Time</th>
					<th scope="col">End Time</th>
					<th scope="col">Date</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="bookings" items="${bookings}">
					<tr>
						<td>${bookings.bookingid}</td>
						<td>${bookings.userid}</td>
						<td>${bookings.startat}</td>
						<td>${bookings.endat}</td>
						<td>${bookings.bookingDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>