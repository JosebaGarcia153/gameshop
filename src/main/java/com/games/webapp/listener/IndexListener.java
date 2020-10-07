package com.games.webapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.dao.impl.CategoryDAOImpl;

import com.games.webapp.controller.Alert;


	
/**
 * Listener que se ejecuta cada vez que se inicia el servidor
 * Setea los usuarios logueados a 0 y guarda los datos de las categorias para poder mostrarlas en el header sin pasar por un controlador.
 *
 */
@WebListener
public class IndexListener implements ServletContextListener {
	
	private static final Logger LOG = Logger.getLogger(IndexListener.class);
	private static final CategoryDAOImpl daoC = CategoryDAOImpl.getInstance();
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	// cuando paramos la App
    	LOG.info("Shutting down the server");
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
    		
    		contextoAplicacion.setAttribute("logged_users", 0);
    		contextoAplicacion.setAttribute("categories", daoC.getAll() );

    	} catch (Exception e) {
    		
    		LOG.fatal(e);
    		contextoAplicacion.setAttribute("alert", new Alert("danger", "We have an unknown problem") );
		}	
    }
}
