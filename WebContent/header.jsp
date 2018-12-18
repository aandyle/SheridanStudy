<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid pad-top bg-sheridan-pattern">
	<nav class="navbar navbar-expand-lg navbar-dark">
		<a href="home.jsp" class="navbar-brand sheridan-lblue d-flex align-self-end">
			<img src="http://webdesign.sheridanc.on.ca/images/sheridanlogo.png"	alt="" class="img-fluid logo"> 
			<span class="sub-logo"> <strong>Study</strong> </span>
		</a>
		<button type="button" class="navbar-toggler" data-toggle="collapse"	data-target="#navbarToggle" name="button">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end" id="navbarToggle">
			<ul class="navbar-nav text-right">
				<li class="nav-item">
					<a href="home.jsp" class="nav-link">Discussion	Boards</a>
				</li>
				<li class="nav-item">
					<a href="MessagingController?action=getAllUsers" class="nav-link">Messages</a>
				</li>
				<li class="nav-item">
					<a href="roomBooking.jsp" class="nav-link">Room Bookings</a>
				</li>
				<li class="nav-item">
					<a href="FlashcardController?action=listsets" class="nav-link">Flashcards</a>
				</li>
				<c:if test="${sessionScope.user.admin }">
					<li class="nav-item">
						<a href="AdminController?action=load" class="nav-link">User Management</a>
					</li>
				</c:if>
				<li class="nav-item">
					<a href="LoginController?action=logout" class="nav-link">Log Out</a>
				</li>
			</ul>
		</div>
	</nav>
</div>