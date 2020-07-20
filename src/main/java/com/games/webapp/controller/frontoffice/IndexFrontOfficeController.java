package com.games.webapp.controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class IndexBackOfficeController
 */
@WebServlet("/views/frontoffice/inicio")
public class IndexFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(IndexFrontOfficeController.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO recuperar datos inicio para el usuario
		
		request.setAttribute("approved_products", 3);
		request.setAttribute("pending_products", 2);
		
		// CUIDAOD: mirar la URL del servlet "/views/frontoffice/inicio"
		// Cuando hacemos forward se pierde lo ultimo de la url y se le suma la variable pagina
		//------------------------
		// El forward resuelve la URL de la siguiente manera:
		//	 "/views/frontoffice/inicio" + "index.jsp" = "/views/frontoffice/index.jsp"
		//------------------------
		String url = "index.jsp";
		LOG.debug("forward: " + url);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
