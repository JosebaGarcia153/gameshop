<%@page import="com.games.webapp.modelo.pojo.Category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="../../../include/header.jsp">
	<jsp:param name="page" value="cform" />
	<jsp:param name="title" value="Category Form" />
</jsp:include>

	<h1>Add/Edit Categories</h1>
	
	<div class="row">
		<div class="col">
			<form action="views/backoffice/category-form-control" method="post">
				<div class="form-group">
					<label for="id">ID:</label>
					<input type="text" name="id" id="id" value="${category.id}" readonly class="views/backoffice/category-form-control">
				</div>
				
				<div class="form-group">
					<label for="name">Name:</label>
					<input type="text" name="name" value="${category.name}" class="views/backoffice/category-form-control" autofocus placeholder="Write the category's name">
				</div>
				
				<input type="submit" value="Save" class="btn btn-primary">
			</form>
		</div>
	</div>

<%@include file="../../../include/footer.jsp" %>
