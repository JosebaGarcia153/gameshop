package com.games.webapp.controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.games.webapp.controller.Alert;
import com.games.webapp.modelo.dao.impl.GameDAOImpl;
import com.games.webapp.modelo.pojo.Game;
import com.games.webapp.modelo.pojo.Usuario;

/**
* Controlador para usuarios comunes para borrar juegos de la base de datos.
* El metodo GET se encarga de indicar a la implementación DAO cual es la ID del juego a borrar.
* @see com.games.webapp.modelo.dao.impl.GameDAOImpl
*/
@WebServlet("/views/frontoffice/delete")
public class GameDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(GameDeleteController.class);
	private static final GameDAOImpl  dao = GameDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario user = new Usuario();
		Alert alert = new Alert();
		
		String idParameter = request.getParameter("id");
		LOG.trace("We enter delete game: " + idParameter);
		
		String url = "games";
		
		try {
			
			user = (Usuario)session.getAttribute("user_login");
			
			int userId = user.getId();
			int id = Integer.parseInt(idParameter);
			
			Game game = dao.delete(id, userId);
			alert = new Alert ("success" , game.getName() + " deleted");
			
		} catch (SecurityException e) {		
			LOG.error(user + " is trying to bypass security");	
		} catch (Exception e) {	
			LOG.error(e);
			url = "inicio";	
			
		} finally {
			
			LOG.debug("forward: " + url);
			session.setAttribute("alert", alert);
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
