package com.games.webapp.controller.backoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.games.webapp.controller.Alert;
import com.games.webapp.modelo.dao.impl.GameDAOImpl;


/**
 * Controlador para administradores para aprovar juegos para ser mostrados en la pagina principal de la web.
 * El metodo POST se encarga de indicar a la implementación DAO cual es la ID del juego a aprobar.
 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
 */
@WebServlet("/views/backoffice/approve-game")
public class ApproveGameBackOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(ApproveGameBackOfficeController.class);
	private static final GameDAOImpl daoG = GameDAOImpl.getInstance();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Alert alert = new Alert();
		
		String idParam = request.getParameter("id");
		
		try {
			
			int id = Integer.parseInt(idParam);
		
			daoG.validate(id);
			
			alert = new Alert("success", "Game has been validated");


		} catch (Exception e) {
			
			LOG.error(e);
			alert = new Alert("warning", "We apologize but there has been an error");
		
		} finally {
			
			//Volver
			request.setAttribute("alert", alert);
			
			String url = "games?approved=0";
			LOG.debug("forward: " + url);
			
			request.getRequestDispatcher(url).forward(request, response);
		}//try
	}//dopost
}
