package com.games.webapp.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.games.webapp.modelo.dao.impl.UserDAOImpl;
import com.games.webapp.modelo.pojo.Rol;
import com.games.webapp.modelo.pojo.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		UserDAOImpl dao = UserDAOImpl.getInstance();
		User user = dao.exists(name, password);
		
		if (user == null) {
			
			request.setAttribute("alert", new Alert("danger", "Wrong data"));
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
			
		} else {
			
			session.setMaxInactiveInterval(60*20); //la sesion dura 20 minutos
			session.setAttribute("user_login", user);
			
			//usuarios conectados recuperar y actualizar, inicializado en InicioAppListenner
			ServletContext sc = request.getServletContext();
			int loggedUsers = (int) sc.getAttribute("logged_users");
			sc.setAttribute("logged_users", ++loggedUsers);
			
			request.setAttribute("alert", new Alert("success", "You are logged in"));
		
			
			if (user.getRol().getId() == Rol.ADMIN) {
				
				request.getRequestDispatcher("views/backoffice/inicio").forward(request, response);
				
			} else {
				
				request.getRequestDispatcher("views/frontoffice/inicio").forward(request, response);
			}
		}
	}
}