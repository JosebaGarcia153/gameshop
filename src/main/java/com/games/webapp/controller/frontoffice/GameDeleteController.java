package com.games.webapp.controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.games.webapp.controller.Alert;
import com.games.webapp.modelo.dao.impl.GameDAOImpl;
import com.games.webapp.modelo.pojo.Game;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/game-delete-control")
public class GameDeleteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final GameDAOImpl  dao = GameDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParameter = request.getParameter("id");
		int id = Integer.parseInt(idParameter);
		
		String message = "";
		boolean fail = true;
		
		try {
			Game game = dao.delete(id);
			message = game.getName() + " deleted";
			fail = false;
			
		} catch (Exception e) {
			
			message = "Error: " + e.getMessage();
			e.printStackTrace();
			fail = true;
			
		} finally {
			
			if (fail == false) {
				request.setAttribute("alert", new Alert("success", message));				
			} else {
				request.setAttribute("alert", new Alert("danger", message));
			}
			
			request.getRequestDispatcher("inicio").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
