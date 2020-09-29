package com.games.webapp.controller.ejemplos;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.games.webapp.modelo.dao.impl.GameDAOImpl;
import com.games.webapp.modelo.pojo.Game;

/**
 * Servlet implementation class EjemploRestController
 */
@WebServlet("/ejemplo-rest2")
public class EjemploRestController2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			GameDAOImpl dao = GameDAOImpl.getInstance();
			
			try {
				Game game = dao.getById(id);
				
				// respuesta para el body
				PrintWriter out = response.getWriter();
				
				Gson gson = new Gson();
				String stringBody = gson.toJson(game);
				
				out.write( stringBody );
				
				out.flush();
				
				//Codigo de estado 200
				response.setStatus(HttpServletResponse.SC_OK);
				
			}catch (Exception e) {
				//Codigo de estado 204
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}		
	}
}


