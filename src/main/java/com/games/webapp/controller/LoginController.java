package com.games.webapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.games.webapp.modelo.dao.impl.UserDAOImpl;
import com.games.webapp.modelo.pojo.Rol;
import com.games.webapp.modelo.pojo.Usuario;

/**
 * Servlet encargado de recibir el nombre y contraseña insertados en login.jsp para logear a un usuario en la pagina web.
 * Si el logeo falla, vuelve una vez mas al la jsp, sino lo redirecciona al controlador del area del usuario o admin.
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
	 * Recive el nombre y contraseña de login.jsp, y comprueba si son correctos para redireccionar o quedarse en el login.
	 * @see GamesBackOfficeController com.games.webapp.controller.backoffice.GamesBackOfficeController
	 * @see GamesFrontOfficeController com.games.webapp.controller.frontoffice.GamesFrontOfficeController
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		UserDAOImpl dao = UserDAOImpl.getInstance();
		Usuario user = dao.exists(name, password);
		
		if (user == null) {
			
			request.setAttribute("alert", new Alert("danger", "Wrong data"));
			request.setAttribute("name", name);
			request.getRequestDispatcher("views/login/login.jsp").forward(request, response);
			
			
		} else {			
			
			session.setMaxInactiveInterval(60 * 1 * 20); //la sesion dura 20 minutos
			session.setAttribute("user_login", user); // @see ListenerUsuarioLogeados => attributeAdded
			
			session.setAttribute("alert", new Alert("success", "You are logged in"));
		
			
			if (user.getRol().getId() == Rol.ADMIN) {
				
				//request.getRequestDispatcher("views/backoffice/inicio").forward(request, response);
				response.sendRedirect("views/backoffice/inicio");
				
			} else {
				
				//request.getRequestDispatcher("views/frontoffice/inicio").forward(request, response);
				response.sendRedirect("views/frontoffice/inicio");
			}
		}
	}
}