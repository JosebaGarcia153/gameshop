package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import controller.Alert;
import modelo.dao.impl.CategoryDAOImpl;
	
/**
 * Application Lifecycle Listener implementation class IndexListener
 *
 */
@WebListener
public class IndexListener implements ServletContextListener {
	
	private static final CategoryDAOImpl daoC = CategoryDAOImpl.getInstance();
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	// cuando paramos la App
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	
    	
    	// cuando ejecutamos la App en el Servidor, al arrancar la 1º vez
    	System.out.println("Estamos arrancado la App, y soy un evento");
    	
    	// Este contexto es para toda la Aplicacion y es accesible desde cualquier JSP o Servlet    	
    	ServletContext contextoAplicacion = sce.getServletContext();
    	
    	try {
    	
    		contextoAplicacion.setAttribute("category", daoC.getAll() );

    	} catch (Exception e) {
    		
    		contextoAplicacion.setAttribute("alert", new Alert("danger", "We have an unknown problem") );
		}	
    }
}
