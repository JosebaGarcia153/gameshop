package com.games.webapp.controller.backoffice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.ConnectionManager;

/**
 * Controlador para realizar una prueba de un ejemplo de migraciones.
 * El metodo GET se encarga de leer un archivo de texto con contenido en formato CSV para poder añadirlo a la tabla de usuarios en la base de datos
 */
@WebServlet("/views/backoffice/migracion")
public class MigrationBackOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOG = Logger.getLogger(MigrationBackOfficeController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.trace("Inicio");
		String fichero = "";
		int numLineas = 0;
		int numInsert = 0;
		int numErroresCampos = 0;
		int numErroresNombresDuplicados = 0;
		long tiempoInicio = System.currentTimeMillis();
		long tiempoFin = 0;

		final String SQL = "INSERT INTO gameshop.users (name,password, image, rol_id) VALUES (?,'user','https://picsum.photos/50',1);";	
		BufferedReader reader;
		
		//Conseguir archivo de la carpeta de recursos
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource("personas.txt");
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            fichero = resource.getFile();
        }
		
		/*** Logica Programacion ***/
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL)
			){
			
			//Cuando establecemos una conexion en Java siempre es autocomitable
			//Con esta linea le decimos que no lo sea y deberemos hacer un COMMIT para guardar los cambios temporales
			connection.setAutoCommit(false);
			
			reader = new BufferedReader(new FileReader(fichero));
			
			//Salta la primera linea del documento
			reader.readLine();
			String linea=null;
			
			
			LOG.trace("Recorrer linea a linea");
			while ((linea = reader.readLine()) != null){
				
				String[] campos = linea.split(";");
				
				try {
					numLineas++;
					
					if (campos.length == 6) {
						
						pst.setString(1, campos[0]);
						
						LOG.debug(pst);
						int affectedRows = pst.executeUpdate();
						
						if ( affectedRows != 1 ) {
							numErroresCampos++;
							LOG.warn("FALLO Insert affectedRows != 1");	
						}else {
							numInsert++;
							LOG.trace("Persona Insertada");
						}
						
					} else {
						
						numErroresCampos++;
						LOG.trace("No se puede Insertar Persona");
					}			
				} catch (Exception e) {
					
					LOG.warn("Nombre duplicado: " +  campos[0] );
					numErroresNombresDuplicados++;
				}				
			}//while
			
			LOG.trace("AL finalizar, realizar un commit para guardar en BBDD");
			connection.commit();
					
		} catch (Exception e) {
			
			LOG.error(e);
			e.printStackTrace();
			
		} finally {
			
			request.setAttribute("fichero", fichero);
			request.setAttribute("numero_lineas", numLineas);
			request.setAttribute("numero_inserciones", numInsert);
			request.setAttribute("numero_errores_campos", numErroresCampos);
			request.setAttribute("numero_errores_nombre", numErroresNombresDuplicados);
			
			tiempoFin = System.currentTimeMillis();
			request.setAttribute("tiempo",  (tiempoFin - tiempoInicio));
			
			request.getRequestDispatcher("resumen-migracion.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
