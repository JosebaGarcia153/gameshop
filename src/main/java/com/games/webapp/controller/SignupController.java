package com.games.webapp.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.dao.impl.UserDAOImpl;
import com.games.webapp.modelo.pojo.Usuario;

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/signup")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(SignupController.class);
	private static final UserDAOImpl dao = UserDAOImpl.getInstance();
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
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
		
		Usuario user = new Usuario();
		Alert alert = new Alert();
		
		String url = "";
		
		//Recoger parametros del formulario
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		
		String birthday = year + "-" + month + "-" + day;
		
		try {	
			
			//Crear objeto con esos parametros	
			user.setName(name);
			user.setPassword(password);
			user.setBirthday(birthday);
			
			//Comprobar que la contrasenia sea repetida correctamente
			if (password.equals(repassword)) {
				
				//Validar pojo
				Set<ConstraintViolation<Usuario>> violations = validator.validate(user);
				
				if ( violations.isEmpty() ) {
					
					dao.create(user);
					alert = new Alert ("success", "You have properly Signed Up");
					url = "views/login/login.jsp";

				} else {
					
					String errors = "";
					
					for (ConstraintViolation<Usuario> v : violations) {	
						errors += "<p><b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";		
					}
					alert = new Alert("warning", errors);
					url = "views/login/sign.jsp";
				}//if
				
			} else {
				
				alert = new Alert("warning", "Password confirmation doesn't match");
				request.setAttribute("alert", alert);
				request.setAttribute("user", user);
				
				LOG.debug("forward: " + url);
				
				request.getRequestDispatcher(url).forward(request, response);
			}//if		
			
		} catch (Exception e) {
			
			LOG.error(e);
			alert = new Alert("warning", e.getMessage());
			url = "views/login/sign.jsp";
		
		} finally {
			
			//Volver al formulario
			request.setAttribute("alert", alert);
			request.setAttribute("user", user);
			
			
			LOG.debug("forward: " + url);
			
			request.getRequestDispatcher(url).forward(request, response);
		}//try
	}
}
