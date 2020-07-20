<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="../../include/office-header.jsp" />
<jsp:include page="../../include/office-navbar.jsp" />
    
           
                        
        <h2>Form</h2>
        
        ${game}
        
        <form action="views/frontoffice/add-game" method="post">
        
        	<input type="submit" value="Create">
        
        </form>
                        
  
 <jsp:include page="../../include/office-footer.jsp" />              