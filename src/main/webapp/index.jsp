<%@page import="model.pojo.Game"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="include/header.jsp">
	<jsp:param name="page" value="index" />
	<jsp:param name="title" value="Index" />
</jsp:include>

	<h1>Games Table</h1>
		
	<div class="row">
		<c:forEach items="${games}" var="g">
			<div class="col-3">
				<div class="card">
					<div class="card-body">
						<p>ID: ${g.id}</p>
						<h3>${g.name}</h3>
						<p>Price: ${g.price} &euro;</p>
						<p>Game Cathegory: ${g.cathegory.name}</p>
						<a href="form-control?id=${g.id}">
							<i class="fas fa-edit fa-lg" title="Edit"></i>
						</a>
						<a href="delete-control?id=${g.id}" onclick="confirmar('${g.name}')">
							<i class="fas fa-trash fa-lg" title="Delete"></i>
						</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
</body>

<%@include file="include/footer.jsp" %>