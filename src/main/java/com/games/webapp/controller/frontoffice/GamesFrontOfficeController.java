package com.games.webapp.controller.frontoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class InicioController
 */
@WebServlet("/views/frontoffice/games")
public class GamesFrontOfficeController extends HttpServlet {
	
		
		private static final long serialVersionUID = 1L;
		private final static Logger LOG = Logger.getLogger(GamesFrontOfficeController.class);
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String validated = request.getParameter("validated");
			String title = "";
			
			if (validated == null) {
				
				title = "Validated Games";
				
			} else {
				
				title = "Products Pending Validation";
			}
			
			request.setAttribute("title", title);	
			
			String url = "games.jsp";
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
