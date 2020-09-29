package com.games.webapp.security;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.games.webapp.modelo.pojo.Rol;
import com.games.webapp.modelo.pojo.Usuario;

/**
 * Servlet Filter implementation class BackOfficeFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/views/frontoffice/*" })
public class FrontOfficeFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(FrontOfficeFilter.class);
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.trace("Filter gets destroyed");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//Cuidado que hay que castear de ServletRequest a HttpServletRequest
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//Recogemos la url de como comienza nuestra App para construir una ruta ABSOLUTA
		String urlIndexApp = req.getContextPath();
		
		LOG.trace("filtrando " + req.getRequestURI());
		
		//Recuperar usuario de session
		Usuario userLogin = (Usuario) req.getSession().getAttribute("user_login");
		
		if (userLogin == null) {
			
			LOG.warn("Didn't pass through the LOGIN, user is  NULL, WITHOUT AUTHORITATION");
			//res.sendRedirect("login.jsp"); => Ruta relativa, se mete en un bucle
			res.sendRedirect(urlIndexApp + "/views/login.jsp"); //Ruta absoluta
			
		} else if (userLogin.getRol().getId() != Rol.USER && userLogin.getRol().getId() != Rol.ADMIN) {
			
			LOG.warn("User without USER priviledges, WITHOUT AUTHORITATION");
			res.sendRedirect(urlIndexApp + "/views/login/login.jsp");
			
		} else {
			
			//Si el usuario esta logeado 
			//Dejamos pasar y continua la request
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
