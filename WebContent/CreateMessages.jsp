<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!-- Popper JS -->
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  	<!-- Latest compiled and minified CSS -->
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  	<!-- jQuery library -->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<!-- Latest compiled JavaScript -->
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

  	<link href="css/style.css" rel="stylesheet" type="text/css">
	<title>Messages | Sheridan Study</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="container pad-top">
		<h1 class="display-1 text-center">Messaging</h1>
	</div>

	<div class="container d-flex justify-content-center pad-top">
		<form action="MessagingController" method="POST">
	
			<table border="1">
				<thead>
					<tr>
						<th>From:</th>
						<th>Messages:</th>
						<th>Date</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${messages}" var="message">
						<tr>
							<td>${usern}</td>
							<td>${message.messages}</td>
							<td>${message.date}</td>
							<td>
								<form method="post" action="MessagingController">
									<input type="hidden" name="uid" value="${user.userId}">
									<input type="hidden" name="uName" value="${usern}"> 
									<input type="hidden" name="resid" value="${resId}"> 
									<input type="hidden" name="msID" value="${message.messageID}">
									<input type="submit" name="submit" value="Delete">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
			<!-- <h3>Subject</h3>
			<input type="text" name="subject" required> <br><br>  -->
			Message:
			<textarea name="emailmessage" required> </textarea>
			<input type="hidden" name="resID" value="${resId}"> 
			<input type="submit" value="Send" name="submit">
			
		</form>
	</div>

	
</body>
</html>