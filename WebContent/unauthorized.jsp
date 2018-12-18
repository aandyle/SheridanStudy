<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<head>
			<meta charset="ISO-8859-1">
			<!-- Latest compiled and minified CSS -->
			<link rel="stylesheet"
				href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
			<!-- jQuery library -->
			<script
				src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
			<!-- Latest compiled JavaScript -->
			<script
				src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
			
			<link href="css/style.css" rel="stylesheet" type="text/css">
			<title>Unauthorized | Sheridan Study</title>
		</head>
	</head>
	<body class="bg-sheridan-pattern">
				
		<div class="container d-flex align-items-center justify-content-center mt-5">
			<div class="jumbotron">
				<h1 class="display-4">401 Unauthorized!</h1>
				<p class="lead sheridan-orange">Please login.</p>
				<hr>
				<form action="LoginController" method = "POST">
					<div class="form-group">
						<label>User ID</label>
						<input type = "text" name = "username" class="form-control" required/> 
					</div>
					<div class="form-group">
						<label>Password</label>
						<input type = "password" name = "password" class="form-control" required/> 
					</div>
					
					<input type="submit" name="submit" value="Login" class="btn btn-primary">
				</form>
			</div>
		</div>
		
	</body>
</html>