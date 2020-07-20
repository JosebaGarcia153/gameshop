<%@page import="com.games.webapp.modelo.pojo.Game"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="../../include/header.jsp">
	<jsp:param name="page" value="gform" />
	<jsp:param name="title" value="Game Form" />
</jsp:include>
	
	<h1>Add/Edit Games</h1>
	
	<div class="row">
		<div class="col">
			<form action="game-form-control" method="post">
				<div class="form-group">
					<label for="id">ID:</label>
					<input type="text" name="id" id="id" value="${game.id}" readonly class="game-form-control">
				</div>
				
				<div class="form-group">
					<label for="name">Name:</label>
					<input type="text" name="name" value="${game.name}" class="game-form-control" autofocus placeholder="Write the game's name">
				</div>
				
				<div class="form-group">
					<label for="price">Price:</label>
					<input type="text" name="price" value="${game.price}" class="game-form-control" placeholder="Write the game's price">
				</div>
				
				<div class="form-group">
					<select class="custom-select" name="category_id">
					  <c:forEach items="${categories}" var="c">
					  	<option value="${c.id}" ${(c.id eq game.category.id) ? "selected" : ""}>${c.name}</option>
					  </c:forEach>					  					  
					</select>
				</div>
				
				<input type="submit" value="Save" class="btn btn-primary">
			</form>
		</div>
	</div>

<%@include file="../../include/footer.jsp" %>