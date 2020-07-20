 		<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand" href="views/frontoffice/inicio">My Panel</a>
            <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>         
            <!-- Navbar-->
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item">
                      <a class="dropdown-item text-white" href="logout">Close Session</a>                 
                </li>
            </ul>
        </nav>
        
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            
                            <a class="nav-link" href="inicio">
                                <div class="sb-nav-link-icon"><i class="fa fa-globe"></i></div>
                                Index
                            </a>
                            <a class="nav-link" href="views/frontoffice/inicio">
                                <div class="sb-nav-link-icon"><i class="fa fa-tachometer-alt"></i></div>
                                My Panel
                            </a>
                            <a class="nav-link" href="views/frontoffice/games">
                                <div class="sb-nav-link-icon"><i class="fa fa-check-square-o"></i></div>
                                Games
                            </a>
                            <a class="nav-link" href="views/frontoffice/games?approved=0">
                                <div class="sb-nav-link-icon"><i class="fa fa-check-circle-o"></i></div>
                                Pending Validation
                            </a>
                            <a class="nav-link" href="views/frontoffice/add-game">
                                <div class="sb-nav-link-icon"><i class="fa fa-plus-circle"></i></div>
                                Add game
                            </a>
                         </div>   
                    </div>       
                </nav>
            </div>
            
            
            <!-- CONTENIDO PRINCIPAL -->
             <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid mb-3">
                    	<%@ include file="alerts.jsp" %>
					