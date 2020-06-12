package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.pojo.User;
import modelo.dao.UserDAOImpl;

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
		// TODO Auto-generated method stub
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
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else {
			
			session.setMaxInactiveInterval(60*20); //la sesion dura 20 minutos
			session.setAttribute("user_login", user);
			
			request.setAttribute("alert", new Alert("success", "You are logged in"));
			request.getRequestDispatcher("inicio").forward(request, response);
		}
	}
}