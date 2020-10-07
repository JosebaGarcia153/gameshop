package com.games.webapp.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.dao.impl.GameDAOImpl;
import com.games.webapp.modelo.pojo.Game;
import com.games.webapp.modelo.pojo.Usuario;

/**
 * Controlador para administradores para mostrar juegos.
 * El metodo GET se encarga de pedir a la implementación DAO la lista de juegos pendientes de aprobar, aprobados o todos dependiendo de la petición hecha.
 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
 */
@WebServlet("/views/backoffice/games")
public class GamesBackOfficeController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private static final Logger LOG = Logger.getLogger(GamesBackOfficeController.class);
		private static final GameDAOImpl daoG = GameDAOImpl.getInstance();
		
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String approved = request.getParameter("approved");
			String total = request.getParameter("total");
			String title = "";
			ArrayList<Game> games = new ArrayList<Game>();
			
			try {
				
				Usuario userSession = (Usuario) request.getSession().getAttribute("user_login");
				int userId = userSession.getId();
				
				if (total == null) {
					
					if (approved == null) {
						
						title = "Approved Games";
						games = daoG.getByUser(userId, true);
						
					} else {
						
						title = "Products Pending Approval";
						games = daoG.getByUser(userId, false);
					}
				
				} else {
					
					title = "All Games";
					games = daoG.getAllByUser(userId);
				}
				
			} catch (Exception e) {
				
				LOG.error(e);
				
			} finally {
				
				request.setAttribute("games", games);
				request.setAttribute("title", title);	
				
				String url = "games.jsp";
				LOG.debug("forward: " + url);
				
				request.getRequestDispatcher(url).forward(request, response);
			}
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
			doGet(request, response);
		}
}
