package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.impl.CategoryDAOImpl;
import modelo.dao.impl.GameDAOImpl;
import modelo.pojo.Category;
import modelo.pojo.Game;

/**
 * Servlet implementation class SeriesController
 */
@WebServlet("/inicio")
public class IndexController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final GameDAOImpl dao = GameDAOImpl.getInstance(); 
	private static final CategoryDAOImpl daoC = CategoryDAOImpl.getInstance();
	
	private static final String ALL_CATEGORIES = "-1";
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Game> games = new ArrayList<Game>();
		ArrayList<Category> categoriesWithGames = new ArrayList<Category>();
		
		String categoryIdParameter = request.getParameter("categoryId");
		String categoryNameParameter = ( request.getParameter("categoryName") == null ) ? "All the categories" : request.getParameter("categoryName");
		
		String headingStr = "";
		
		
		
		//Si no se ha insertado una categoria muestra la pagina inicial con 10 resultados
		//Si se ha insertado muestra todos los resultados de la categoria
		
			
		if (ALL_CATEGORIES.equals(categoryIdParameter)) {
			
			categoriesWithGames = daoC.getAllWithProducts();
			games = null;
			request.setAttribute("heading", "All products by category");
			
		} else {
			
			categoriesWithGames = null;
			
			if (categoryIdParameter == null) {
				
				games = dao.getLast(10);
				
			} else {
				
				int categoryId = Integer.parseInt(categoryIdParameter);
				games = dao.getAllByCategory(categoryId, 10);
			}
			
			headingStr = "<b>" + games.size() + "</b> latest entries of <b>" + categoryNameParameter + "</b>";
		}
		
		
		
		request.setAttribute("games", games);
		request.setAttribute("heading",  headingStr);
		request.setAttribute("categoriesWithGames", categoriesWithGames);
		request.getRequestDispatcher("index.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
