<%@page import="com.games.webapp.modelo.pojo.Category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page ="../../../include/header.jsp">
	<jsp:param name="page" value="categories" />
	<jsp:param name="title" value="Categories" />
</jsp:include>

	<h1>${fn:length(categories)} Categories</h1>
	
	<a href="views/backoffice/category-form-control">Create New Category</a>
	
	<table class="table table-striped">
		<thead>
			<tr>
				<td>ID</td>
				<td>Name</td>
				<td>Operations</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.categories}" var="c">
				<tr>
					<td>${c.id}</td>
					<td>${c.name}</td>
					<td>
						<a href="views/backoffice/category-form-control?id=${c.id}">
							<i class="fas fa-edit fa-lg" title="Edit"></i>
						</a>
						<a href="views/backoffice/category-delete-control?id=${c.id}" onclick="confirmar('${c.name}')">
							<i class="fas fa-trash fa-lg" title="Delete"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<%@include file="../../../include/footer.jsp" %>