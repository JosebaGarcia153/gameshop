// ejecuta la funcion cuando todo el documento de html DOM este listo y cargado

$(document).ready(function() {
    // seleccion por id => #example y ejecuta el plugin .DataTable();
	$('#table').DataTable();
});

function confirmar(name) {
	
	// The confirm() method returns true if the user clicked "OK", and false otherwise. 
	if ( confirm('Are you sure you want to delete ' + name + '?') ){
		
		console.debug(' continua el evento por defecto del ancla ');
		
	}else {
		console.debug(' prevenimos o detenemos el evento del ancla ');
		event.preventDefault();
	}	
}

/**
 * Funcion asociada al evento onkeyUp para el id:input#Nombre
 * Llama mediante Ajax a un servicio REST para comprobar si existe el nombre de usuario en la BBDD
 * 
 */
function searchUser(event) {
	//console.debug(event);
	const name = event.target.value;
	console.debug(`value of the input ${name}`);
	
	let elNameHelp = document.getElementById('nameHelp');
	let elBtnSign = document.getElementById('btnSign');
	
	const url =`http://localhost:8080/games/api/user?name=${name}`;
	// Llamada Ajax
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url);
	xhttp.send();
	xhttp.onreadystatechange = function() {
		
		if (this.readyState == 4 && this.status == 200) {
			
			elNameHelp.innerHTML = 'Name not available';
			elNameHelp.classList.add('text-danger');
			elNameHelp.classList.remove('test-success');
			elBtnSign.setAttribute('disabled','disabled');
			
		} else {
			
			elNameHelp.innerHTML = 'Name available';
			elNameHelp.classList.add('test-success');
			elNameHelp.classList.remove('text-danger');
			elBtnSign.removeAttribute('disabled');
		}
	}
}