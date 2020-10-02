
<jsp:include page="../include/header.jsp" >
  <jsp:param name="page" value="api" />
  <jsp:param name="title" value="API" /> 
</jsp:include>

<h1>API WS REST</h1>

<h2>Juegos</h2>
<p>Endpoint: <b>http://localhost:8080/gameshop/api/game/</b></p>
<p>Podemos realizar un CRUD los juegos:</p>


<p>Metodos habilitados:</p>

<ul class="list-group">

	<li class="list-group-item">
		<span class="badge badge-success">GET</span> 
		<b>endpoint/game/</b>   
		<span class="float-right badge badge-secondary ml-2">200</span>
		<span class="float-right">Listado de todos los juegos</span>
	</li>
	<li class="list-group-item">
		<span class="badge badge-success">GET</span> 
		<b>endpoint/game/{id}</b>   
		<span class="float-right badge badge-secondary ml-2">200</span>
		<span class="float-right badge badge-secondary ml-2">204</span>
		<span class="float-right">Detalle de un juego</span>
	</li>
	
	<li class="list-group-item">
		<span class="badge badge-danger">DELETE</span> 
		<b>endpoint/game/{id}</b>   
		<span class="float-right badge badge-secondary ml-2">200</span>
		<span class="float-right badge badge-secondary ml-2">409</span>
		<span class="float-right">Eliminar un juego</span>
	</li>
	
	<li class="list-group-item">
		<span class="badge badge-warning">PUT</span> 
		<b>endpoint/game/{id}</b>   
		<span class="float-right badge badge-secondary ml-2">200</span>
		<span class="float-right badge badge-secondary ml-2">409</span>
		<span class="float-right">Modificar un juego</span>
	</li>
	
	<li class="list-group-item">
		<span class="badge badge-info">POST</span> 
		<b>endpoint/game/</b>   
		<span class="float-right badge badge-secondary ml-2">201</span>
		<span class="float-right badge badge-secondary ml-2">409</span>
		<span class="float-right">Crear un juego</span>
	</li>
	
	

</ul>

<p>Los metodos POST y PUT deben enviar datos en el body con formato JSON, los datos obligatorios tienen asterisco</p>

<code><pre>
{
  "id": 8,
  <b>*"name"</b>: "DOOT",
  <b>*"image"</b>: "https://i.blogs.es/6b70d4/acelgas-arco-jamon/1366_2000.jpg",
 <b> *"price"</b>: 32,
  "user": {
    <b>*"id"</b>: 6,
    "name": "user",
    "password": "user",
    "rol": {
      "id": 2,
      "name": "user"
    }
  },
  "category": {
    <b>*"id"</b>: 3,
    "name": "Demonic Musical"   
  }
}
</pre></code>

	

<%@include file="../include/footer.jsp" %>