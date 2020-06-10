package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.pojo.Game;
import modelo.dao.GameDAOImpl;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/delete-control")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParameter = request.getParameter("id");
		int id = Integer.parseInt(idParameter);
		
		GameDAOImpl  dao = GameDAOImpl.getInstance();
		String message = "";
		
		try {
			Game game = dao.delete(id);
			message = game.getName() + " deleted";
			
		} catch (Exception e) {
			
			message = "Error: " + e.getMessage();
			e.printStackTrace();
			
		} finally {
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("inicio").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
