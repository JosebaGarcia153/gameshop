<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!doctype html>
<html lang="es">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<!-- Todas las rutas relativas comienzan por el href indicado -->
	<!--  ${pageContext.request.contextPath} == http://localhost:8080/games -->
	<base href="${pageContext.request.contextPath}/" />

    <!-- fontawesome 5 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
 
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    
    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/styles.css">
     
    <title> ${param.title} | Games Shop</title>
</head>
<body>
	
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark mb-5">
	
        <!-- icono para desplegar menu en moviles -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <!-- lista enlaces -->
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
          	<ul class="navbar-nav mr-auto">
	        	<li class="nav-item" ${('index' eq param.page) ? 'active' : ''}>
	            	<a class="nav-link" href="inicio">
	              		<i class="fas fa-gamepad fa-2x"></i>
	            	</a>
				</li>
				
				<c:if test="${ not empty sessionScope.user_login }">
	            	<c:if test="${not (sessionScope.user_login.name eq 'admin')}">
		            	<li class="nav-item ${('index' eq param.page) ? 'active' : ''}">
		            		<a class="nav-link" href="views/frontoffice/inicio">Front Office</a>
		            	</li>
	            	</c:if>
	            	<c:if test="${sessionScope.user_login.name eq 'admin'}">
		            	<li class="nav-item ${('index' eq param.page) ? 'active' : ''}">
		            		<a class="nav-link" href="views/backoffice/inicio">Back Office</a>
		            	</li> 
            		</c:if> 
				</c:if>

            	<li>
           			<div class="dropdown show">
            			<a class="btn btn-secondary dropdown-toggle" href="inicio" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            				Categories
            			</a>
            			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            				<c:forEach items="${categories}" var="c">
            					<a class="dropdown-item" href="inicio?categoryId=${c.id}&categoryName=${c.name}">${c.name}</a>	
            				</c:forEach>
            				<a class="dropdown-item" href="inicio">Last 10</a>
            				<a class="dropdown-item" href="inicio?categoryId=-1">All</a>
            			</div>
            		</div> 
            	</li> 
            	
            	<li class="nav-item"  >
					<a class="nav-link ${ ( 'api' eq param.page ) ? 'active' : '' }" href="views/api.jsp">API</a>
				</li> 
        	</ul>
			
			
        	<span class="form-inline">
	         	<c:if test="${ empty sessionScope.user_login }">
	            	  <a class="nav-link  btn btn-outline-warning" href="views/login/login.jsp">Login</a>
	            </c:if>
	            
	            <c:if test="${ not empty sessionScope.user_login }">
	            	<div class="mr-2" style="color:white">
        				${user_login.name}
        			</div>
	            	<div class="mr-2">
        				<img src="${sessionScope.user_login.image}" alt="Profile Image" >
        			</div>
	            	<span class="badge badge-pill badge-light mr-3">${sessionScope.usuario_login.nombre}</span>
	            	<a class="nav-link  btn btn-outline-light" href="logout">Logout</a>
	            </c:if>     
         	</span>
         
		</div>
    </nav>

	<main role="main" class="container pt-5 mt-5">
	
		<jsp:include page="alerts.jsp"></jsp:include>