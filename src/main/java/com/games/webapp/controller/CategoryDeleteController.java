package com.games.webapp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.dao.impl.CategoryDAOImpl;
import com.games.webapp.modelo.pojo.Category;

/**
 * Servlet implementation class CategoryDeleteController
 */
@WebServlet("/category-delete-control")
public class CategoryDeleteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(CategoryDeleteController.class);
	
	private static final CategoryDAOImpl daoC = CategoryDAOImpl.getInstance();
	
	private static final String VIEW_TABLE = "/category-table-control";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParameter = request.getParameter("id");
		int id = Integer.parseInt(idParameter);
		
		String message = "";
		boolean fail = true;
		
		try {
			Category category = daoC.delete(id);
			message = category.getName() + " deleted";
			fail = false;
			
		} catch (SQLException e) {
			
			message = "Error: " + "The category can't be deleted because it is not empty";
			LOG.error(e);
			fail = true;
			
		} catch (Exception e) {
			
			message = "Error: " + e.getMessage();
			LOG.error(e);
			fail = true;
			
		} finally {
			
			if (fail == false) {
				request.setAttribute("alert", new Alert("success", message));				
			} else {
				request.setAttribute("alert", new Alert("danger", message));
			}
			
			
			request.getRequestDispatcher(VIEW_TABLE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
