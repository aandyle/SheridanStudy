<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
		<title>Search | Discussions</title>
	</head>
	<body>
		
		
		<!-- menu bar / banner -->
		<jsp:include page="header.jsp"></jsp:include>
		
		<div class="container pad-top pb-5 mb-5">
			<h1 class="display-1 text-center">Discussions</h1>
			<h1 class="text-center">Search Result for: "${title}"</h1>

		    <!-- beginning of discussion search/create btn -->
		    <div class="row d-flex justify-content-between">
		      	<a href="CreateDiscussion.html" class="btn btn-primary ml-3">Create Discussion</a>
		      	
		      	<form class="form-inline" action="DiscussionController" method="post">
		        	<input type="text" class="form-control" placeholder="Search discussions..." name="search">
		        	<input type="hidden" name="action" value="Search">
		        	<input type="submit" class="ml-1 mr-3 text-center btn btn-outline-primary" name="" value="Search">
		      	</form>
		    </div>
			
			<!-- beginning of discussion listing -->
			<div class="list-group pad-top">
				<c:forEach var="discussionsList" items="${DiscussionsList}" >
					
					<a href="DiscussionController?action=getDiscussion&discussionId=${discussionsList.discussionId}" 
					class="list-group-item list-group-item-action flex-column align-items-start">
						
						<div class="row">	
							<c:choose>
								<c:when test="${user.admin}">
									<div class="col-lg-11">
								</c:when>
								<c:otherwise>
									<div class="col-lg-12">
								</c:otherwise>
							</c:choose>
								<div class="d-flex justify-content-between">
									<h4 class="mb-1">${discussionsList.title}</h4>
									<small>By ${discussionsList.ownerName}</small>
								</div>
								<div class="d-flex justify-content-between align-items-center">
									<p>${discussionsList.comment}</p>
									<span class="badge badge-primary badge-pill">${discussionsList.numberOfComments}</span>
								</div>
								<div class="d-flex align-items-start">
              						<small>${discussionsList.postDate}</small>
            					</div>
							</div>
							<!-- delete option for ADMINs -->
							<c:if test="${user.admin}">
								<div class="col-lg-1 d-flex align-items-center">
									<form action="DiscussionController" method="POST">
										<input type="hidden" name="action" value="DeleteDiscussion">
										<input type="hidden" name="discussionId" value="${discussionsList.discussionId}"> 
										<input type="submit" class="btn btn-danger" value="Delete">
									</form>
								</div>
							</c:if>
						</div>	
					</a>
				</c:forEach>
			</div>
			
			<!-- pagination -->
			<nav class="d-flex justify-content-center pad-top">
				<ul class="pagination">
					<c:if test="${pageNumber > 1}">
						<li class="page-item">
							<a href="DiscussionController?action=ListDiscussion&pageNumber=${pageNumber-1}" class="page-link">Previous</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="1" end="${numberOfPages}">
						<li class="page-item">
							<a href="DiscussionController?action=ListDiscussion&pageNumber=${i}" class="page-link">${i}</a>
						</li>
					</c:forEach>
					<c:if test="${pageNumber < numberOfPages}">
						<li class="page-item">
							<a href="DiscussionController?action=ListDiscussion&pageNumber=${pageNumber+1}" class="page-link">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>