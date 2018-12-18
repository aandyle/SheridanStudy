<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<p class="text-center">Select a user to message below.</p>
	</div>
	
	<div class="container pad-top pb-5 mb-5">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">UserID</th>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th class="text-center"></th>
					<!-- leave empty for buttons -->
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${users}" var="user">
					<c:if test="${user.userId != sessionScope.user.userId }">
						<tr>
							<td>${user.userId}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td class="text-center">
								<a href="MessagingController?action=createmessage&userid=${user.userId}&usern=${user.firstName}" class="btn btn-outline-primary">
									Message
								</a>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>