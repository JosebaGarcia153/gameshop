# games


Proyecto Web **Java EE** siguiendo el patrón de **MVC** contra una bbdd **Mysql**
CMS para gestionar los productos de una tienda de juegos. 


![screenshot 1]( screenshot1.png?raw=true)


## Técnologia

- Maven 4.0.0
- Java 8
- Java Servlet Api 3.1.0
- JSP 2.2
- JSTL 1.2
- Javax Validation Api 1.1
- Bootstrap 4.5.0
- FontAwesome 5.13.0
- Datatables.net 1.10.21

Para ver mas detalles sobre las depencias de este proyecto mirar el [pom.xml](https://github.com/JosebaGarcia153/gameshop/blob/master/pom.xml)




## configuración de la bbdd

Para crear la bbdd disponemos de un script **script-db.sql** en la raiz del proyecto, el cual crear el esquema **supermercado** y las tablas necesarias, ademas de insertar datos de prueba.

![screenshot 2]( screenshot2.png?raw=true)

Para realizar la conexión a la bbdd cambiar el siguiente fichero **src/main/webapp/META-INF/context.xml**


```
<?xml version="1.0" encoding="UTF-8"?>
  <Context path="/ejemplo05">
      <Resource
          type="javax.sql.DataSource"
          auth="Container"
          name="jdbc/super"
          driverClassName="com.mysql.jdbc.Driver"
          url="jdbc:mysql://localhost:3306/gameshop"
          username="USUARIO"
          password="PASSWORD"
          maxActive="100"
          maxIdle="30"
          maxWait="10000"          
      />
 </Context>
```


 -- TODO DIAGRAMA DE LA BBDD  ---

## Ejecutar Proyecto

Al ser un proyecto web necesitamos un servidor de aplicaciones, en nuestro caso recomendamos **Apache Tomcat 9.0**.

Podemos navegar por los diferentes enlaces de la cabecera puesto que son públicos.
Si queremos entrar a los paneles de Administración deberemos *Iniciar Sesión*.
Tenemos dos roles diferentes:

1. Administrador   **[admin, admin]** : Permisos Totales para cambiar cualquier juego
2. Usuario Normal  **[user, user]** : Permismos solo para sus juegos

*Las contraseñas estan haseadas en MD5 dentro de la bbdd.*


## Estructura Clases del proyecto

Interesante consultar la documentacion [JavaDoc API](https://github.com/JosebaGarcia153/gameshop/tree/master/src/main/webapp/doc) la cual esta accesible una vez ejecuitado el proyecto en la propia barra de navegación.

Intersante mirar los siguientes packages de java:

- **com.ipartek.formacion.listener** Listener que se ejecuta al arrancar la APP
- **com.ipartek.formacion.controller.backoffice** Controladores para el usuario administrador
- **com.ipartek.formacion.controller.frontoffice** Controladores para el usuario normal
- **com.ipartek.formacion.modelo.pojo** Pojos o Clases para crear Objetos e java
- **com.ipartek.formacion.modelo.dao** DAO para relacionar los Pojos de Java con las tablas de la BBDD
- **com.ipartek.formacion.security** Filtros de seguridad


