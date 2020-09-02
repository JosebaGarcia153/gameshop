<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="../../include/backoffice-header.jsp" />
<jsp:include page="../../include/backoffice-navbar.jsp" />
                        
	<h2>${title}</h2>
    
    <table class="tabla table table-striped">
		<thead>
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Price</td>
				<td>Image</td>
				<td>Category</td>
				<td>User ID</td>
				<td>Operations</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${games}" var="g">
				<tr>
					<td>${g.id}</td> <% // no hace falta usar el getter p.id == p.getId() %>
					<td>${g.name}</td>
					<td>${g.price} &euro;</td>
					<td><img src="${g.image}"  class="img-thumbnail" alt="image..."></td>
					<td>${g.category.name}</td>
					<td>${g.user.id}</td>
					<td>
						<a href="views/backoffice/approve-game?id=${g.id}" class="mr-4">
							<i class="fas fa-check fa-2x" title="Approve Game"></i>
						</a>
						<a href="views/backoffice/add-game?id=${g.id}" class="mr-4">
							<i class="far fa-edit fa-2x" title="Edit Game"></i>
						</a>
						<a href="views/backoffice/delete?id=${g.id}" onclick="confirmar('${g.name}')">
							<i class="fas fa-trash fa-2x" title="Delete Game"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
    
 <jsp:include page="../../include/office-footer.jsp" />