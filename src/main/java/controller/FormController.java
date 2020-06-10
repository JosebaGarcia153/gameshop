package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.pojo.Game;
import modelo.dao.GameDAOImpl;

/**
 * Servlet implementation class EntryCreationController
 */
@WebServlet("/form-control")
public class FormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//crea nueva instancia de un juego antes de ir al formulario
		Game game = new Game();
		
		request.setAttribute("game", game);
		request.getRequestDispatcher("form.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//arraylist de posibles errores
		ArrayList<String> validation = new ArrayList<String>();
		
		//crea nueva instancia de juego
		GameDAOImpl dao = GameDAOImpl.getInstance();
		Game game = new Game();
		
		String message = "";
		
		try {
			//recoge parametros de formulario
			String idParameter = request.getParameter("id");
			String nameParameter = request.getParameter("name");
			String priceParameter = request.getParameter("price");
			
			int id = Integer.parseInt(idParameter);
			double price = 0;
			
			//prueba por posibles errores de usuario
			try {
				price = Double.parseDouble(priceParameter);
			} catch (Exception e) {
				validation.add("You must insert a number");
			}

			if (nameParameter.length() < 3 || nameParameter.length() > 100) {			
				validation.add("The name must be between 3 and 100 characters long");	
			}
			
			if (price <= 0) {		
				validation.add("The price must be more than 0");		
			}

			//guarda los parametros insertados por el usuario
			game.setId(id);
			game.setName(nameParameter);
			game.setPrice(price);
	
			
		} catch (Exception e) {
	
			e.printStackTrace();
			
		} finally {
			//si no ha habido errores
			if (validation.isEmpty()) {
				
				try {
					//crea el juego
					dao.create(game);
					message = "Game successfully added";
					
				} catch (Exception e) {
					
					//si ha habido errores mientras se creaba el juego
					validation.add("The game already exists");
					
					//recoge los atributos y se queda en el formulario
					request.setAttribute("game", game);
					request.setAttribute("validation", validation);	
					request.getRequestDispatcher("form.jsp").forward(request, response);
				}

				//recoge los atributos y vuelve al inicio
				request.setAttribute("message", message);
				request.setAttribute("games", dao.getAll());
				request.getRequestDispatcher("inicio").forward(request, response);
				
				
//				request.setAttribute("game", game);
//				request.getSession().setAttribute("message", message);
//				response.sendRedirect("inicio");
				
			
			//si ha habido errores
			} else {
				
				//recoge los atributos y se queda en el formulario
				request.setAttribute("game", game);
				request.setAttribute("validation", validation);	
				request.getRequestDispatcher("form.jsp").forward(request, response);
			}
		}//finally
	}//post
}
