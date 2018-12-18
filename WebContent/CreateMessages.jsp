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
		<p class="text-center">Refresh page to view any new messages.</p>
	</div>

	<div class="container pad-top d-flex justify-content-center">
		<div class="col-sm-12 d-flex justify-content-center">
			<h4 class="text-primary">
				<c:forEach items="${messages}" var="message">
					<c:if test="${message.senderID != sessionScope.user.userId }">
						<c:set var="messaging" value="${message.recipient }"></c:set>
					</c:if>
				</c:forEach>
				${pageScope.messaging}
			</h4>
		</div>
	</div>

	<div class="container pad-top">
		<c:forEach items="${messages}" var="message">
			<div class="row d-flex justify-content-start ">
				<div class="col-sm-6 text-left">
					<c:if test="${message.senderID != sessionScope.user.userId}">
						<div class="alert alert-primary">${message.messages}</div>
					</c:if>
				</div>
			</div>

			<div class="row d-flex justify-content-end ">
				<div class="col-sm-6 text-right">
					<c:if test="${message.senderID == sessionScope.user.userId}">
						<div class="alert alert-secondary">${message.messages}</div>
					</c:if>
				</div>
			</div>

		</c:forEach>
	</div>
	<hr>
	<div class="container pb-5 mb-5">
		<form action="MessagingController" method="post">
			<div class="row d-flex justify-content-end">
				<div class="col-sm-11">
					<div class="form-group">
						<textarea class="form-control" name="emailmessage" id="reply" rows="4"required"></textarea>
					</div>
				</div>

				<div class="col-sm-1 d-flex align-items-center justify-content-center">
					<input type="hidden" name="resID" value="${resId}"> 
					<input type="submit" value="Send" name="submit" class="btn btn-primary">
				</div>
			</div>
		</form>
	</div>

	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>