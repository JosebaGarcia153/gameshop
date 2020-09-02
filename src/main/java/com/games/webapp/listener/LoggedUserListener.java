package com.games.webapp.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.pojo.User;

/**
 * Application Lifecycle Listener implementation class LoggedUserListener
 *
 */
@WebListener
public class LoggedUserListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	private static HashMap<String, User> hashm = null;
	private static final  Logger LOG = Logger.getLogger(LoggedUserListener.class);
	
    /**
     * Default constructor. 
     */
    public LoggedUserListener() {
        
    	LOG.trace("constructor");
    	hashm = new HashMap<String, User>();
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	LOG.trace("New Session");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	LOG.trace("Session Eliminated");
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
         String attributeName = event.getName();
         ServletContext sc = event.getSession().getServletContext();
         String sessionId = event.getSession().getId();
         
         LOG.trace("New session atribute" + attributeName);
         
         //Se acaba de hacer el login en LoginController
         if ("user_login".equals(attributeName)) {
        	 User user = (User)event.getValue();
        	 LOG.trace("User logged " + user);
        	 
        	 hashm = (HashMap<String, User>)sc.getAttribute("loggedUsers");
        	 
        	 if (null == hashm) {
        		 
        		 hashm = new HashMap<String, User>();
        	 }
        	 
        	 hashm.put(sessionId, user);
        	 sc.setAttribute("loggedUsers", hashm);
        	 LOG.trace("User stored in the HashMap");
         }
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         
    	String attributeName = event.getName();
    	LOG.trace("Removing session attribute");
        ServletContext sc = event.getSession().getServletContext();
        String sessionId = event.getSession().getId();
        
        if ("user_login".equals(attributeName)) {
        	hashm = (HashMap<String, User>)sc.getAttribute("loggedUsers");
        	
        	if (null == hashm) {
       		 
        		hashm = new HashMap<String, User>();
       	 	}
        	
        hashm.remove(sessionId);
       	sc.setAttribute("loggedUsers", hashm);        	
        }
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
    	LOG.trace("Session attribute modified");
    }
}
