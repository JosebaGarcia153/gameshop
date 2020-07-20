package com.games.webapp.controller;

import java.io.IOException;
import java.sql.SQLException;
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

import com.games.webapp.modelo.dao.impl.CategoryDAOImpl;
import com.games.webapp.modelo.dao.impl.GameDAOImpl;
import com.games.webapp.modelo.pojo.Category;
import com.games.webapp.modelo.pojo.Game;

		
/**
 * Servlet implementation class EntryCreationController
 */
@WebServlet("/game-form-control")
public class GameFormController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(GameFormController.class);
	
	//direcciones a donde ir
	private static final String VIEW_FORM = "views/games/gameform.jsp";
	private static final String VIEW_INDEX = "inicio";
	
	private static GameDAOImpl daoG = GameDAOImpl.getInstance();
	private static CategoryDAOImpl daoC = CategoryDAOImpl.getInstance();
	
	//Validators
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//crea nueva instancia de un juego antes de ir al formulario
			Game game = new Game();
			
			String idParameter = request.getParameter("id");
			
			if (idParameter != null) {

				int id = Integer.parseInt(idParameter);
				GameDAOImpl dao = GameDAOImpl.getInstance();
				game = dao.getById(id);
			}
			
			request.setAttribute("game", game);
			
		} catch (Exception e) {

			LOG.error(e);

		} finally {
			
			request.setAttribute("categories", daoC.getAll());
			// ir a la nueva vista / jsp
			request.getRequestDispatcher(VIEW_FORM).forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//crea nuevas instancias
		Game game = new Game();
		Alert prompt = null;
		
		//Define parametros
		Float price = 0f;
		int id = 0;	
		int categoryId = 0;
		String message = "";
		String whereTo = "";
		
		try {
			//recoge parametros de formulario
			String idParameter = request.getParameter("id");
			
			
			//TODO comprobar estos trims
			
			
			String nameParameter = request.getParameter("name").trim();
			String priceParameter = request.getParameter("price").trim();
			String categoryIdParameter = request.getParameter("category_id").trim();
			
			id = Integer.parseInt(idParameter);
			categoryId = Integer.parseInt(categoryIdParameter);
			
			try {
				price = Float.parseFloat(priceParameter);				
			} catch (Exception e) {
				price = -1f;
			}
			
			//guarda los parametros en la instancia
			game.setId(id);
			game.setName(nameParameter);			
			game.setPrice(price);
			
			Category c = new Category();
			c.setId(categoryId);
			game.setCategory(c);
			
			
			//set de violaciones
			Set<ConstraintViolation<Game>> violations = validator.validate(game);
			
			if ( violations.isEmpty() ) {
				
				if (id == 0) {
					
					//crea el juego
					daoG.create(game);
					message = "Game successfully added";
					
				} else {
					
					//actualiza datos del juego
					daoG.update(game);
					message = "Game successfully updated";	
				}
				
				prompt = new Alert("success", message);
				whereTo = VIEW_INDEX;
				
			} else {
				
				//guarda las violaciones en un string de errores
				for (ConstraintViolation<Game> violation : violations) {
					message += "<p><b>" + violation.getPropertyPath() + "</b>: "  + violation.getMessage() + "</p>";
				}
				
				prompt = new Alert("danger", message);
				whereTo = VIEW_FORM;
			}//if
			
		} catch (SQLException e) {		
			prompt = new Alert("danger", e.getMessage());
			whereTo = VIEW_FORM;
			
		} catch (Exception e) {
			prompt = new Alert("danger","There has been an unknown error");
			whereTo = VIEW_FORM;
			
		} finally {
			
			//recoge los atributos y vuelve a la vista
			request.setAttribute("game", game);
			request.setAttribute("alert", prompt);
			request.setAttribute("categories", daoC.getAll());
			request.getRequestDispatcher(whereTo).forward(request, response);
		}//finally
	}//post
}