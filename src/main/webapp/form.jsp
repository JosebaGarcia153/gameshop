<%@page import="model.pojo.Game"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="include/header.jsp">
	<jsp:param name="page" value="form" />
	<jsp:param name="title" value="Form" />
</jsp:include>
	
	<h1>Add games to the Games List</h1>
		
	<c:if test="${not empty  validation}">
		<ol>
			<c:forEach items="${validation}" var="validation">
				<li style="color:red;">${validation}</li>
			</c:forEach>
		</ol>
	</c:if>
	
	<div class="row">
		<div class="col">
			<form action="form-control" method="post">
				<div class="form-group">
					<label for="id">ID:</label>
					<input type="text" name="id" id="id" value="${game.id}" readonly class="form-control">
				</div>
				
				<div class="form-group">
					<label for="nombre">Name:</label>
					<input type="text" name="name" value="${game.name}" class="form-control" autofocus placeholder="Write the product's name">
				</div>
				<div class="form-group">
					<label for="nombre">Price:</label>
					<input type="text" name="price" value="${game.price}" class="form-control" placeholder="Write the product's price">
				</div>

				<input type="submit" value="Save" class="btn btn-primary">
			</form>
		</div>
	</div>

<%@include file="include/footer.jsp" %>