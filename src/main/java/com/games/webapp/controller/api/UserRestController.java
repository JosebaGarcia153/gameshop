package com.games.webapp.controller.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.dao.impl.UserDAOImpl;

/**
 * Controlador para la API de REST para mostrar los usuarios en formato JSON
 */
@WebServlet("/api/user")
public class UserRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOG = Logger.getLogger(UserRestController.class);
	private static UserDAOImpl dao = UserDAOImpl.getInstance();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("url pathInfo:" + request.getPathInfo());
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		
		if (dao.searchByName(name)) {
		response.setStatus( HttpServletResponse.SC_OK );
		} else {
		response.setStatus( HttpServletResponse.SC_NO_CONTENT );
		}
	}
}
