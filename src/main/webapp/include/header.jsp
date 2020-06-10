<!doctype html>
<html lang="es">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
            	<li class="nav-item ${('form' eq param.page) ? 'active' : ''}">
            		<a class="nav-link" href="form-control">New Game</a>
            	</li>     
        	</ul>
		</div>
    </nav>

	<main role="main" class="container pt-5 mt-5">