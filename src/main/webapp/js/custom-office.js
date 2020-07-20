// ejecuta la funcion cuando todo el documento de html DOM este listo y cargado

$(document).ready(function() {
    // seleccion por id => #example y ejecuta el plugin .DataTable();
	$('#table').DataTable();
});

function init() {
	console.log('Document load and ready');
	
}

function confirmar(name) {
	
	// The confirm() method returns true if the user clicked "OK", and false otherwise. 
	if ( confirm('Are you sure you want to delete ' + name + '?') ){
		
		console.debug(' continua el evento por defecto del ancla ');
		
	}else {
		console.debug(' prevenimos o detenemos el evento del ancla ');
		event.preventDefault();
	}	
}