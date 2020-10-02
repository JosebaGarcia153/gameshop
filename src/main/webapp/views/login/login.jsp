<jsp:include page ="../../include/header.jsp">
	<jsp:param name="pagina" value="login" />
	<jsp:param name="title" value="Login" />
</jsp:include>

<form action="login" method="post">

	<input type="text" name="name" value="${name}" autofocus placeholder="Insert your name">
	<br/>
	<input type="password" id="password" name="password" placeholder="Instert your password">
	<br/>
        
	<input type="submit" value="Login" class="btn btn-primary">
</form>
<br/>
<a href="views/login/sign.jsp">Sign Up</a>

<%@include file="../../include/footer.jsp" %>