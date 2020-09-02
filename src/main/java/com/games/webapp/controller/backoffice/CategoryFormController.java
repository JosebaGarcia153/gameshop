package com.games.webapp.controller.backoffice;

import java.io.IOException;

import java.sql.SQLException;
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

import org.apache.log4j.Logger;

import com.games.webapp.controller.Alert;
import com.games.webapp.modelo.dao.impl.CategoryDAOImpl;
import com.games.webapp.modelo.pojo.Category;


/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/views/backoffice/category-form-control")
public class CategoryFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(CategoryFormController.class);	
	private static final CategoryDAOImpl daoC = CategoryDAOImpl.getInstance();
	private static final String VIEW_FORM = "categories/categoryForm.jsp";
	private static final String VIEW_TABLE = "/views/backoffice/category-table-control";
	
	//Validators
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//crea nueva instancia de una categoria antes de ir al formulario
			Category category = new Category();
			
			String idParameter = request.getParameter("id");
			
			if (idParameter != null) {

				int id = Integer.parseInt(idParameter);
				category = daoC.getById(id);
			}
			
			request.setAttribute("category", category);
			
		} catch (Exception e) {

			LOG.error(e);

		} finally {
			
			// ir a la nueva vista / jsp
			request.getRequestDispatcher(VIEW_FORM).forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//crea nuevas instancias
		Category category = new Category();
		
		
		Alert prompt = null;
		
		//Define parametros
		int id = 0;
		String message = "";
		String whereTo = "";
		
		try {
			//recoge parametros de formulario
			String idParameter = request.getParameter("id");
			String nameParameter = request.getParameter("name").trim();
			
			id = Integer.parseInt(idParameter);
			
			//guarda los parametros en la instancia
			category.setId(id);
			category.setName(nameParameter);			

			//set de violaciones
			Set<ConstraintViolation<Category>> violations = validator.validate(category);
			
			if ( violations.isEmpty() ) {
				
				if (id == 0) {
					
					//crea el juego
					daoC.create(category);
					message = "Category successfully added";
					
				} else {
					
					//actualiza datos del juego
					daoC.update(category);
					message = "Category successfully updated";	
				}
				
				prompt = new Alert("success", message);
				whereTo = VIEW_TABLE;
				
			} else {
				
				//guarda las violaciones en un string de errores
				for (ConstraintViolation<Category> violation : violations) {
					message += "<p><b>" + violation.getPropertyPath() + "</b>: "  + violation.getMessage() + "</p>";
				}
				
				prompt = new Alert("danger", message);
				whereTo = VIEW_FORM;
			}//if
			
		} catch (SQLException e) {		
			prompt = new Alert("danger", e.getMessage());
			whereTo = VIEW_FORM;
			
		} catch (Exception e) {
			prompt = new Alert("danger","There has been an unknown error");
			whereTo = VIEW_FORM;
			
		} finally {
			
			//recoge los atributos y vuelve a la vista
			request.setAttribute("category", category);
			request.setAttribute("alert", prompt);
			request.getRequestDispatcher(whereTo).forward(request, response);
		}//finally
	}//post
}
