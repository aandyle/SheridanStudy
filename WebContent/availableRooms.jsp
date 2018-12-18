<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
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
		<p class="text-center">Please select a date and time slot.</p>
		<p class="text-center">Bookings are limited to one hour each.</p>
	</div>
	
	<div class="container">
		<c:if test="${comment != null || dError != null || tError != null }">
			<div class="alert alert-danger">
				${comment }
				${dError}
				${tError }
			</div>
		</c:if>
	</div>
	
	<div class="container pb-5 mb-5">
		<form action="BookingController" method="post">
			<div class="form-group">
				<label>Date</label>
				<input type="text" class="form-control" name="date" placeholder="DD/MM/YYYY"> <span>${dError}</span>
			</div>
			<div class="form-group">
				<label>Start Time</label>
				<select class="form-control" name="startat" required>
					<option value="12">12:00:00</option>
					<option value="13">13:00:00</option>
					<option value="14">14:00:00</option>
					<option value="15">15:00:00</option>
					<option value="16">16:00:00</option>
				</select>
			</div>
			<div class="form-group">
				<label>End Time</label>
				<select class="form-control" name="endat" required>
					<option value="13">13:00:00</option>
					<option value="14">14:00:00</option>
					<option value="15">15:00:00</option>
					<option value="16">16:00:00</option>
					<option value="17">17:00:00</option>
				</select>
			</div>
			<div class="d-flex justify-content-center">
				<a href="roomBooking.jsp" class="btn btn-outline-secondary">Cancel</a>
				<input type="submit" class="btn btn-primary ml-2" value="Verify Availability">
			</div>				
		</form>
	</div>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>