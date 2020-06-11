package controller;

import java.io.IOException;
import java.util.ArrayList;
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

import model.pojo.Game;
import modelo.dao.GameDAOImpl;


		
/**
 * Servlet implementation class EntryCreationController
 */
@WebServlet("/form-control")
public class FormController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static GameDAOImpl dao = GameDAOImpl.getInstance();
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

			// ir a la nueva vista / jsp
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//crea nueva instancia de juego
		Game game = new Game();
		
		String message = "";
		double price = 0;
		int id = 0;
		
		boolean fail = true;
		
		String errors = "";
		
		try {
			//recoge parametros de formulario
			String idParameter = request.getParameter("id");
			String nameParameter = request.getParameter("name");
			String priceParameter = request.getParameter("price");
			
			//guarda los parametros insertados por el usuario
			game.setName(nameParameter);
			
			id = Integer.parseInt(idParameter);
			game.setId(id);
			
			
			
			try {
				price = Double.parseDouble(priceParameter);
				
			} catch (Exception e) {
				errors = "<p><b>price:</b> You must insert a number for the price</p>";
			}
			game.setPrice(price);
			
			
			//set de violaciones
			Set<ConstraintViolation<Game>> violations = validator.validate(game);
			
			//guarda las violaciones en un string de errores
			for (ConstraintViolation<Game> violation : violations) {
				errors += "<p><b>" + violation.getPropertyPath() + "</b>: "  + violation.getMessage() + "</p>";
			}
				
		} catch (Exception e) {
	
			e.printStackTrace();
			
		} finally {
			//si no ha habido errores
			if ("".equals(errors)) {
				
				try {
					if (id == 0) {
						//crea el juego
						dao.create(game);
						message = "Game successfully added";
						fail = false;
						
					} else {
						
						//actualiza datos del juego
						dao.update(game);
						message = "Game successfully updated";
						fail = false;
					}		
				} catch (Exception e) {
					
					//si ha habido errores mientras se creaba el juego
					errors += "<p><b>name:</b> The game already exists</p>";
					fail = true;
					
					//recoge los atributos y se queda en el formulario
					request.setAttribute("game", game);
					request.setAttribute("alert", new Alert("danger", errors));
					request.getRequestDispatcher("form.jsp").forward(request, response);
				}

				//recoge los atributos y vuelve al inicio
				request.setAttribute("message", message);
				request.setAttribute("alert", new Alert("success", message));
				request.getRequestDispatcher("inicio").forward(request, response);
				

			//si ha habido errores
			} else {
				
				//recoge los atributos y se queda en el formulario
				request.setAttribute("game", game);
				request.setAttribute("alert", new Alert("danger", errors));
				request.getRequestDispatcher("form.jsp").forward(request, response);
			}
		}//finally
	}//post
}
