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
 * Servlet implementation class SeriesController
 */
@WebServlet("/inicio")
public class IndexController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final GameDAOImpl dao = GameDAOImpl.getInstance(); 
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categoryIdParameter = request.getParameter("categoryId");
		String categoryNameParameter = ( request.getParameter("categoryName") == null ) ? "All the categories" : request.getParameter("categoryName");
		
		String headingStr = "";
		
		ArrayList<Game> games = new ArrayList<Game>();
		
		//Si no se ha insertado una categoria muestra la pagina inicial con 10 resultados
		//Si se ha insertado muestra todos los resultados de la categoria
		if (categoryIdParameter == null) {
			
			if (categoryNameParameter.equals("all")) {
				
				games = dao.getAll();
				headingStr = "All entries";
				
			} else {
				
				games = dao.getLast(10);
				headingStr = "<b>" + games.size() + "</b> latest entries of <b>" + categoryNameParameter + "</b>";
			}
			
		} else {
			
			int categoryId = Integer.parseInt(categoryIdParameter);
			games = dao.getAllByCategory(categoryId, 10);
			
			headingStr = "<b>" + games.size() + "</b> last products of <b>" + categoryNameParameter + "</b>";
		}
		
		request.setAttribute("heading",  headingStr);
		request.setAttribute("games", games);
		request.getRequestDispatcher("index.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
