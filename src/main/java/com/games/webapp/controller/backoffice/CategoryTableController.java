package com.games.webapp.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.dao.impl.CategoryDAOImpl;
import com.games.webapp.modelo.pojo.Category;

/**
 * Servlet implementation class CategoryTableController
 */
@WebServlet("/views/backoffice/category-table-control")
public class CategoryTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(CategoryTableController.class);
	private static final CategoryDAOImpl daoC = CategoryDAOImpl.getInstance();
	private static final String VIEW_TABLE = "categories/categoryTable.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Category> categories = new ArrayList<Category>();
		
		try {
			
			categories = daoC.getAll();
			
		} catch (Exception e) {
			
			LOG.error(e);
			
		} finally {
			
			request.setAttribute("categories", categories);
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
