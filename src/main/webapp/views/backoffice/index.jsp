<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="../../include/backoffice-header.jsp">
	<jsp:param name="page" value="index" />
	<jsp:param name="title" value="Index" />
</jsp:include>
<jsp:include page="../../include/backoffice-navbar.jsp" />
    

                        <h2>All Products</h2>
                        <div class="row">
                        
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                    <div class="card-body">Published <span class="big-number">${approved_products}</span></div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="views/backoffice/games">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-danger text-white mb-4">
                                    <div class="card-body">Pending <span class="big-number">${pending_products}</span></div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="views/backoffice/games?approved=0">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div> 
                            
                           <div class="col-xl-3 col-md-6">
                                <div class="card bg-secondary text-white mb-4">
                                    <div class="card-body">Total <span class="big-number">${total_products}</span></div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="views/backoffice/games?total=0">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>                          
                           
                        </div>
                        
                         <h2>My Data</h2>
                        <div class="row">
                        	${user_login}
                        </div>
                        
  
 <jsp:include page="../../include/office-footer.jsp" />