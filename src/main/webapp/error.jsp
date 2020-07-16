<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page isErrorPage="true" %>

<jsp:include page="include/header.jsp" >
  <jsp:param name="pagina" value="error" />
  <jsp:param name="title" value="error" /> 
</jsp:include>


<div class="jumbotron">
  <h1 class="display-4">Opsss!!!</h1>
  <p class="lead">We apologize. There has been an ERROR.</p>
  <hr class="my-4">
  <p>Please contact the administrator.</p>
  <a class="btn btn-primary btn-lg" href="#<%=exception.getMessage()%>" role="button">Send Email</a>
  <hr class="my-4">
  <a class="btn btn-primary btn-lg" href="inicio" role="button">Return to Index</a>
</div>




<%@include file="include/footer.jsp" %>