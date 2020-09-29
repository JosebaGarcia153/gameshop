<jsp:include page ="../../include/header.jsp">
	<jsp:param name="pagina" value="login" />
	<jsp:param name="title" value="Login" />
</jsp:include>

<form action="signup" method="post">
	
	<label for="name">Name:</label>
	<input type="text" id="name" name="name" autofocus placeholder="Insert your name">
	<br/>
	<label for="password">Password:</label>
	<input type="text" id="password" name="password" placeholder="Write a password 6 to 15 characters long">
	<br/>
	<label for="repassword">Confirm your password:</label>
	<input type="text" id="repassword" name="repassword" placeholder="Password">
	<br/>
	<label for="year">Birthday:</label>
	<input type="text" id="year" class="date" name="year" placeholder="YYYY"> /
	<input type="text" id="month" class="date" name="month" placeholder="MM"> /
	<input type="text" id="day" class="date" name="day" placeholder="DD">
	<br/>
        
	<input type="submit" value="Sign Up" class="btn btn-primary">
</form>


<%@include file="../../include/footer.jsp" %>