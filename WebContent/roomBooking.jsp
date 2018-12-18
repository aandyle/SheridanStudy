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

	<c:set var="comment" scope="session" value="${null}" />
	<c:set var="dError" scope ="session" value="${null}"/>
	<c:set var="tError" scope ="session" value="${null}"/>
		
	<%
		//String comment = " ";
		//session.setAttribute("comment", comment);
	%>
	
	
	<div class="container pad-top">
		<h1 class="display-1 text-center">Room Bookings</h1>
	</div>
	
	<div class="container pb-5 mb-5">
		<p class="text-center">Room are available only between 12:00pm and 05:00pm.</p>
		
		
		<form action="availableRooms.jsp">
			<div class="row d-flex justify-content-center pad-top">
				<div class="form-group">
					<input type="submit" class="form-control btn btn-primary" value="Book Room" name="insert">
				</div>
			</div>
		</form>
		
		<form action="BookingController" method="post">
			<div class="row d-flex justify-content-center">
				<div class="form-group">
					<input type="submit" class="btn btn-outline-primary form-control" value="View Bookings" name="select">
				</div>
			</div>
		</form>
	</div>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>