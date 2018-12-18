<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<title>Admin | Sheridan Study</title>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="container pad-top">
		<h1 class="display-1 text-center">Admin Control Panel</h1>
	</div>
	
	<div class="container pad-top">
		<c:if test="${newuser.userId != 0 && newuser.userId != null}">
			<div class="alert alert-success" role="alert">
				<h4 class="alert-heading">User successfully created</h4>
				User ID: ${newuser.userId } <br>
				Name: ${newuser.firstName }  ${newuser.lastName }
				<c:remove var="newuser"/>
			</div>
		</c:if>
	</div>

	<div class="container d-flex justify-content-center pad-top">
		<form action="AdminController" method="post">
			<input type="submit" name="action" value="Add Student" class="btn btn-primary">
			<input type="submit" name="action" value="Add Admin" class="btn btn-warning">
		</form>
	</div>
	
	<div class="container pad-top pb-5 mb-5">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">User ID</th>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Role</th>
					<th scope="col"></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.userId}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<c:choose>
							<c:when test="${user.admin }">
								<td class="text-danger">Admin</td>
							</c:when>
							<c:otherwise>
								<td>Student</td>
							</c:otherwise>
						</c:choose>
						<td>
							<c:if test="${user.userId != sessionScope.user.userId }">
								<form action="AdminController" method="post">
									<input type="hidden" name="id" value="${user.userId }">
									<input type="hidden" name="isAdmin" value="${user.admin }">
									<input type="submit" name="action" value="Delete" class="btn btn-danger">
								</form>
							</c:if>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>