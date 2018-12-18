<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		
		<link href="css/style.css" rel="stylesheet" type="text/css">
		
		<title>${discussion.title} | Discussions</title>
	</head>
	<body>
	
	
		<jsp:include page="header.jsp"></jsp:include>
		
		<div class="container pad-top">
			<!-- discussion title -->
			<div class="row pt-5 pb-5">
      			<div class="col">		
        			<h1>${discussion.title}</h1>
        			<p>By ${comment[0].username} <small>${comment[0].postTime }</small></p>
      			</div>
    		</div>
    		
    		<!-- comments -->
    		<c:forEach var="comment" items="${comment}">
    			<div class="row jumbotron">
	      			<div class="col-md-2 text-center">
	        			<h4>${comment.username }</h4>
	        			
	        			<c:choose>
	        				<c:when test="${comment.check }">
	        					<h6 class="text-danger">Admin</h6>
	        				</c:when>
	        				<c:otherwise>
	        					<h6>Student</h6>
	        				</c:otherwise>
	        			</c:choose>
	        			
	        			
	        			<small>${comment.postTime }</small>
	        			<!-- ADMIN delete comments -->
	        			<c:if test="${user.admin}">
	        				<form action="DiscussionController" method="post">
	        					<input type = "hidden" name = "action" value = "DeleteComment">
								<input type = "hidden" name = "commentId" value = "${comment.commentId}">
								<input type = "hidden" name = "discussionId" value = "${discussion.discussionId}">
								<input type = "submit" class="btn btn-danger mt-1" value = "Delete Comment">
	        				</form>	        			
	        			</c:if>
	        			
	        			
	      			</div>
	      			<div class="col-md-10">
	        			<p>${comment.comment}</p>
	      			</div>
    			</div>
    		</c:forEach>
    		
    		<!-- pagination -->
    		<nav class="d-flex justify-content-center pad-top">
				<ul class="pagination">
					<c:if test="${pageNumber > 1}">
						<li class="page-item">
							<a href="DiscussionController?action=getDiscussion&discussionId=${discussion.discussionId}&pageNumber=${pageNumber-1}" class="page-link">Previous</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="1" end="${numberOfPages}">
						<li class="page-item">
							<a href="DiscussionController?action=getDiscussion&discussionId=${discussion.discussionId}&pageNumber=${i}" class="page-link">${i}</a>
						</li>
					</c:forEach>
					<c:if test="${pageNumber < numberOfPages}">
						<li class="page-item">
							<a href="DiscussionController?action=getDiscussion&discussionId=${discussion.discussionId}&pageNumber=${pageNumber+1}" class="page-link">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
		
		<!-- post a reply -->
		<div class="container pb-5 mb-5">
			<form action="DiscussionController" method="post">
				<div class="row d-flex justify-content-center">
					<div class="col-sm-10">
						<div class="form-group">
							<label for="reply">Post a reply</label>
							<textarea class="form-control" name="comment" id="reply" rows="4"required"></textarea>
						</div>
					</div>
	
					<div class="col-sm-2 d-flex align-items-center justify-content-center">
						<input type = "hidden" name = "action" value = "PostReply">
						<input type = "hidden" name = "discussionId" value = "${discussion.discussionId}">
						<input type="submit" value="Post Reply" class="btn btn-primary">
					</div>
				</div>
			</form>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>