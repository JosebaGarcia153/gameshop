package com.games.webapp.controller.frontoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.pojo.Game;


/**
 * Servlet implementation class InicioController
 */
@WebServlet("/views/frontoffice/add-game")
public class AddGameFrontOfficeController extends HttpServlet {
	
		
		private static final long serialVersionUID = 1L;
		private final static Logger LOG = Logger.getLogger(AddGameFrontOfficeController.class);
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			// Ir al Formulario y enviamos un producto inicializado 
			Game g = new Game();
			
			request.setAttribute("game", g);	
			
			String url = "form.jsp";
			LOG.debug("forward: " + url);
			
			request.getRequestDispatcher(url).forward(request, response);
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			// recoger parametros del formulario
			
			
			// crear objeto con esos parametros
			Game g = new Game();
			g.setId(34);
			g.setName("Game Added");
			// validar pojo
			
			// llamar al dao
			
			//volver al formulario de nuevo
			request.setAttribute("game", g);
			
			String url = "form.jsp";
			LOG.debug("forward: " + url);
			
			request.getRequestDispatcher(url).forward(request, response);
		}	
}
