package controller;

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

import model.pojo.Category;
import model.pojo.Game;
import modelo.dao.CategoryDAOImpl;
import modelo.dao.GameDAOImpl;

		
/**
 * Servlet implementation class EntryCreationController
 */
@WebServlet("/form-control")
public class FormController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//direcciones a donde ir
	private static final String TOFORM = "form.jsp";
	private static final String TOINDEX = "inicio";
	
	private static GameDAOImpl daoGame = GameDAOImpl.getInstance();
	private static CategoryDAOImpl daoCategory = CategoryDAOImpl.getInstance();
	
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

			e.printStackTrace();

		} finally {
			
			request.setAttribute("categories", daoCategory.getAll());
			// ir a la nueva vista / jsp
			request.getRequestDispatcher("form.jsp").forward(request, response);
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
		double price = 0;
		int id = 0;	
		int categoryId = 0;
		String message = "";
		String whereTo = "";
		
		try {
			//recoge parametros de formulario
			String idParameter = request.getParameter("id");
			String nameParameter = request.getParameter("name");
			String priceParameter = request.getParameter("price");
			String categoryIdParameter = request.getParameter("category_id");
			
			id = Integer.parseInt(idParameter);
			categoryId = Integer.parseInt(categoryIdParameter);
			
			try {
				price = Double.parseDouble(priceParameter);				
			} catch (Exception e) {
				price = -1;
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
					daoGame.create(game);
					message = "Game successfully added";
					
				} else {
					
					//actualiza datos del juego
					daoGame.update(game);
					message = "Game successfully updated";	
				}
				
				prompt = new Alert("success", message);
				whereTo = TOINDEX;
				
			} else {
				
				//guarda las violaciones en un string de errores
				for (ConstraintViolation<Game> violation : violations) {
					message += "<p><b>" + violation.getPropertyPath() + "</b>: "  + violation.getMessage() + "</p>";
				}
				
				prompt = new Alert("danger", message);
				whereTo = TOFORM;
			}//if
			
		} catch (SQLException e) {		
			prompt = new Alert("danger", e.getMessage());
			whereTo = TOFORM;
			
		} catch (Exception e) {
			prompt = new Alert("danger","There has been an unknown error");
			whereTo = TOFORM;
			
		} finally {
			
			//recoge los atributos y vuelve a la vista
			request.setAttribute("game", game);
			request.setAttribute("alert", prompt);
			request.setAttribute("categories", daoCategory.getAll());
			request.getRequestDispatcher(whereTo).forward(request, response);
		}//finally
	}//post
}