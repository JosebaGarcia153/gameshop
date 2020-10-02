<jsp:include page ="../../include/header.jsp">
	<jsp:param name="pagina" value="login" />
	<jsp:param name="title" value="Login" />
</jsp:include>

<form action="signup" method="post">
	
	<div class="form-group">
	<label for="name">Name:</label>
	<small id="nameHelp" class="form-text text-muted"></small>
	<input type="text" id="name" name="name" autofocus value="${name}" onkeyUp="searchUser(event)" class="form-control" placeholder="Insert your name">	
	</div>
	<div class="form-group">
	<label for="password">Password:</label>
	<input type="text" id="password" name="password" class="form-control" placeholder="Write a password 6 to 15 characters long">
	</div>
	<div class="form-group">
	<label for="repassword">Confirm your password:</label>
	<input type="text" id="repassword" name="repassword" class="form-control" placeholder="Password">
	</div>
	<div class="form-group">
	<label for="year">Birthday:</label>
	<input type="text" id="year" class="date" name="year" class="form-control" placeholder="YYYY"> /
	<input type="text" id="month" class="date" name="month" class="form-control" placeholder="MM"> /
	<input type="text" id="day" class="date" name="day" class="form-control" placeholder="DD">
	</div>
        
	<input type="submit" value="Sign Up" class="btn btn-primary">
</form>


<%@include file="../../include/footer.jsp" %>