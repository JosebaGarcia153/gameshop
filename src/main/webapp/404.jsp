<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page isErrorPage="true" %>

<jsp:include page="include/header.jsp" >
  <jsp:param name="pagina" value="404" />
  <jsp:param name="title" value="404" /> 
</jsp:include>


<div class="jumbotron">
  <h1 class="text-center display-1">404</h1>
  <p class="lead">This page doesn't exist.</p>
  <hr class="my-4">  
  <a class="btn btn-primary btn-lg" href="inicio" role="button">Return to Index</a>
</div>

<%@include file="include/footer.jsp" %>