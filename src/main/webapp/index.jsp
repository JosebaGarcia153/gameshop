<%@page import="model.pojo.Game"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="include/header.jsp">
	<jsp:param name="pagina" value="index" />
	<jsp:param name="title" value="Index" />
</jsp:include>

	<h1>Games Table</h1>
	
	<table id="table">	
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${games}" var="g">
			<tr>
				<td>${g.id}</td>
				<td>${g.name}</td>
				<td>${g.price}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>

<%@include file="include/footer.jsp" %>