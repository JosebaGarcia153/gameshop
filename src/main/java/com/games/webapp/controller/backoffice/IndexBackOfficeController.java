package com.games.webapp.controller.backoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.dao.impl.GameDAOImpl;

/**
 * Servlet implementation class IndexBackofficeController
 */
@WebServlet("/views/backoffice/inicio")
public class IndexBackOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(IndexBackOfficeController.class);
	private static final GameDAOImpl daoG = GameDAOImpl.getInstance();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* -- Migration stuff
		request.setAttribute("game_count", 34578);
		request.setAttribute("user_count", 10000);
		request.setAttribute("logged_count", 456);
		request.setAttribute("pending_games_count", 2);
		*/
		
		request.setAttribute("approved_products", daoG.getAllGameCount().getApproved());
		request.setAttribute("pending_products", daoG.getAllGameCount().getPending());
		request.setAttribute("total_products", daoG.getAllGameCount().getTotal());
		
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
