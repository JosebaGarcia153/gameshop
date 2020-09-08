<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="../../include/frontoffice-header.jsp" />
<jsp:include page="../../include/frontoffice-navbar.jsp" />
    
	<h1 class="mt-2">Upload a new Game</h1>
	             
	<form action="views/frontoffice/add-game" method="post" enctype="multipart/form-data">
	
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
			<label for="imagen">Image:</label>
			<!-- <input type="text" name="image" id="image" value="${game.image}" class="form-control" placeholder="Image URL (.jpg or .png)" > -->
			<input type="file" name="imagefile">
		</div>
		
		<div class="form-group">
			<select class="custom-select" name="category_id">
				<c:forEach items="${categories}" var="c">
					<option value="${c.id}" ${(c.id eq game.category.id) ? "selected" : ""}>${c.name}</option>
				</c:forEach>					  					  
			</select>
		</div>
		
		<input type="submit" value="Save" class="btn btn-primary btn-block">
	    
	</form>
                        
  
 <jsp:include page="../../include/office-footer.jsp" />              