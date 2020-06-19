<%@page import="model.pojo.Game"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="include/header.jsp">
	<jsp:param name="page" value="index" />
	<jsp:param name="title" value="Index" />
</jsp:include>

	<h1>Games Table</h1>
	
	<table id="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<td>Cathegory</td>
				<td>Operations</td>	
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${games}" var="g">
			<tr>
				<td>${g.id}</td>
				<td>${g.name}</td>
				<td>${g.price} &euro;</td>
				<td>${g.cathegory.name}</td>
				<td>
					<a href="form-control?id=${g.id}"><i class="fas fa-edit fa-lg" title="Edit"></i></a>
					
					<a href="delete-control?id=${g.id}" onclick="confirmar('${g.name}')">
						<i class="fas fa-trash fa-lg" title="Delete"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>

<%@include file="include/footer.jsp" %>