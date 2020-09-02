package com.games.webapp.controller.backoffice;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.games.webapp.controller.Alert;
import com.games.webapp.modelo.dao.impl.GameDAOImpl;
import com.games.webapp.modelo.pojo.Category;
import com.games.webapp.modelo.pojo.Game;
import com.games.webapp.modelo.pojo.User;


/**
 * Servlet implementation class InicioController
 */
@WebServlet("/views/backoffice/add-game")
public class AddGameBackOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(AddGameBackOfficeController.class);
	private static final GameDAOImpl daoG = GameDAOImpl.getInstance();
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = new User();
		Game game = new Game();
		
		String url = "form.jsp";
		
		try {
			
			user = (User)session.getAttribute("user_login");
			
			String idParameter = request.getParameter("id");
			int userId = user.getId();
			
			if (idParameter != null) {
				
				int id = Integer.parseInt(idParameter);
				game = daoG.getById(id, userId);
			}
			
			request.setAttribute("game", game);	
			
		} catch (Exception e) {

			LOG.error(e);
			url = "inicio";

		} finally {
			
			LOG.debug("forward: " + url);
			//Las categorias estan disponibles a traves del Listener
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = new User();
		Game game = new Game();
		Alert alert = new Alert();
		
		//Recoger parametros del formulario
		String idParam = request.getParameter("id");
		String name = request.getParameter("name");
		String priceParam = request.getParameter("price");
		String image = request.getParameter("image");
		String categoryIdParam = request.getParameter("category_id");
		
		try {
			
			int id = Integer.parseInt(idParam);
			int idCategory = Integer.parseInt(categoryIdParam);
			float price = Float.parseFloat(priceParam);
			
			//Recuperar usuario de session y setearlo en el producto
			user = (User)session.getAttribute("user_login");
			int userId = user.getId();
			
			/* **************************************************************** 
			 * Comprobar Seguridad, siempre que no sea un nuevo Producto 
			 * ***************************************************************/
			if (id != 0) {	
				game = daoG.getById(id, userId); //Lanza SecurityException si el userId no pertenece al producto
			}
			
			//Crear objeto con esos parametros			
			game.setId(id);
			game.setName(name);
			game.setPrice(price);
			game.setImage(image);
			
			Category category = new Category();
			category.setId(idCategory);
			
			game.setCategory(category);
			
			//Recuperar usuario de session y setearlo en el producto
			game.setUser(user);
			
				
			//Validar pojo
			Set<ConstraintViolation<Game>> violations = validator.validate(game);
			
			if ( violations.isEmpty() ) {
				
				if (id == 0) {
					
					daoG.create(game);
					alert = new Alert ("success", "Once a game is added, you will need to wait a few hours for it to be approved");				
				
				} else {
					
					daoG.update(game);
					alert = new Alert ("success", "Game successfully updated, pending approval");	
				}
				
			} else {
				
				String errors = "";
				
				for (ConstraintViolation<Game> v : violations) {	
					errors += "<p><b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";		
				}
				alert = new Alert("warning", errors);
			}//if

		} catch (Exception e) {
			
			LOG.error(e);
			alert = new Alert("warning", "We apologize but that name is already registered");
		
		} finally {
			
			//Volver al formulario
			request.setAttribute("alert", alert);
			request.setAttribute("game", game);
			
			String url = "form.jsp";
			LOG.debug("forward: " + url);
			
			request.getRequestDispatcher(url).forward(request, response);
		}//try
	}//dopost
}
