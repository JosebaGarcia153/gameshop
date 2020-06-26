package com.games.webapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.dao.impl.CategoryDAOImpl;

import com.games.webapp.controller.Alert;


	
/**
 * Application Lifecycle Listener implementation class IndexListener
 *
 */
@WebListener
public class IndexListener implements ServletContextListener {
	
	private final static Logger LOG = Logger.getLogger(IndexListener.class);
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
    	
    	//Cuando ejecutamos la App en el Servidor, al arrancar la 1º vez
    	
    	//Evento de log
    	LOG.info("Executing the APP");
    	
    	// Este contexto es para toda la Aplicacion y es accesible desde cualquier JSP o Servlet    	
    	ServletContext contextoAplicacion = sce.getServletContext();
    	
    	try {
    	
    		contextoAplicacion.setAttribute("category", daoC.getAll() );

    	} catch (Exception e) {
    		
    		LOG.fatal(e);
    		contextoAplicacion.setAttribute("alert", new Alert("danger", "We have an unknown problem") );
		}	
    }
}
