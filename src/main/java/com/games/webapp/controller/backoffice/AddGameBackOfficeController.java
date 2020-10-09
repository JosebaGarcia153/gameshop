package com.games.webapp.controller.backoffice;


import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import com.games.webapp.modelo.pojo.Usuario;


/**
 * Controlador para administradores para el formulario de juegos.
 * El metodo GET se encarga de guardar los datos y seguir mostrándolos en el formulario en el caso de que halla habido algón error al enviarlos.
 * El metodo POST se encarga de recibir los datos del formularios y enviarlos a la implementación DAO donde se realizarán las llamadas SQL.
 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
 */
@WebServlet("/views/backoffice/add-game")
@MultipartConfig
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
		Usuario user = new Usuario();
		Game game = new Game();
		
		String url = "form.jsp";
		
		try {
			
			user = (Usuario)session.getAttribute("user_login");
			
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
		Usuario user = new Usuario();
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
			user = (Usuario)session.getAttribute("user_login");
			int userId = user.getId();
			
			/* **************************************************************** 
			 * Comprobar Seguridad, siempre que no sea un nuevo Juego 
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
					
					alert = new Alert ("success", "Game successfully added, pending approval");				
				
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
			
			alert = new Alert("warning", e.getMessage());
		
		} finally {
			
			
			session.setAttribute("alert", alert);
			session.setAttribute("game", game);
			
			String url = "form.jsp";
			LOG.debug("forward: " + url);
			
			response.sendRedirect(url);
		}//try
	}//dopost
}
