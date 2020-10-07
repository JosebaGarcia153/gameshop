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
 * Controlador para ir al index del BacktOffice.
 * El metodo GET se encarga de contar los juegos añadidos por el usuario separados por aprobados, pendientes y el numero total.
 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
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
