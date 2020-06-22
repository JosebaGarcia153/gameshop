package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.GameDAOImpl;

/**
 * Servlet implementation class SeriesController
 */
@WebServlet("/inicio")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GameDAOImpl dao = GameDAOImpl.getInstance();
		
		String categoryIdParameter = request.getParameter("categoryId");
		
		//Si no se ha insertado una categoria muestra la pagina inicial con 10 resultados
		//Si se ha insertado muestra todos los resultados de la categoria
		if (categoryIdParameter == null) {
			
			request.setAttribute("games", dao.getLast(10));
			
		} else {
			
			int categoryId = Integer.parseInt(categoryIdParameter);
			request.setAttribute("games", dao.getAllByCategory(categoryId, 500));
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
