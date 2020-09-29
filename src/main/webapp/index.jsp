<%@page import="com.games.webapp.modelo.pojo.Game"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="include/header.jsp">
	<jsp:param name="page" value="index" />
	<jsp:param name="title" value="Index" />
</jsp:include>
	
	<ol>
	 	<li>TODO para hacer visible algo creado por un usuario, un administrador lo tiene que dar por valido</li>
	 	<li>TODO para validar el administrador necesita su propia interfaz grafica</li>
		<li>TODO Imagenes para juegos que no sean de picsum</li>
	</ol>
	
	
	<h1>${heading}</h1>
	<div class="row-card">
		<c:forEach items="${games}" var="g">
			<div class="card">
				<div class="card-body">
					<p>ID: ${g.id}</p>
					<h4>${g.name}</h4>
					<p>Price: ${g.price} &euro;</p>
					<p>Game Category: ${g.category.name}</p>
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
					</div>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
	
	<a href="views/ejemplos/rest.jsp">Web Service REST</a>
		
</body>

<%@include file="include/footer.jsp" %>