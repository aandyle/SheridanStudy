<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	<div class="container d-flex justify-content-center pad-top pb-5 mb-5">
		<form action="AdminController" method="post">
			<div class="form-group">
				<label>First name</label>
				<input type="text" name="fname" class="form-control" placeholder="First name" required>
			</div>
			
			<div class="form-group">
				<label>Last name</label>
				<input type="text" name="lname" class="form-control" placeholder="Last name" required>
			</div>
			<div class="form-group">
				<label>Password</label>
				<input type="text" name="password" class="form-control" placeholder="Password" required>
			</div>

			<input type="submit" name="action" value="Add" class="btn btn-primary">
		</form>
	</div>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>