<%@page import="com.games.webapp.modelo.pojo.Game"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="include/header.jsp">
	<jsp:param name="page" value="index" />
	<jsp:param name="title" value="Index" />
</jsp:include>

	<h1>${heading}</h1>
	<div class="row-card">
		<c:forEach items="${games}" var="g">
			<div class="card">
				<div class="card-body">
					<p>ID: ${g.id}</p>
					<h4>${g.name}</h4>
					<p>Price: ${g.price} &euro;</p>
					<p>Game Category: ${g.category.name}</p>
					<c:if test="${ not empty user_login }">
						<a href="form-control?id=${g.id}">
							<i class="fas fa-edit fa-lg" title="Edit"></i>
						</a>
						<a href="delete-control?id=${g.id}" onclick="confirmar('${g.name}')">
							<i class="fas fa-trash fa-lg" title="Delete"></i>
						</a>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<c:forEach items="${categoriesWithGames}" var="c">
	<h4>${c.name}</h4>
	
		<div class="row-card">
			<c:forEach items="${c.games}" var="g">
				<div class="card">
					<div class="card-body">
						<p>ID: ${g.id}</p>
						<h4>${g.name}</h4>
						<p>Price: ${g.price} &euro;</p>
						<p>Game Category: ${g.category.name}</p>
						<a href="form-control?id=${g.id}">
							<i class="fas fa-edit fa-lg" title="Edit"></i>
						</a>
						<a href="delete-control?id=${g.id}" onclick="confirmar('${g.name}')">
							<i class="fas fa-trash fa-lg" title="Delete"></i>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
		
</body>

<%@include file="include/footer.jsp" %>