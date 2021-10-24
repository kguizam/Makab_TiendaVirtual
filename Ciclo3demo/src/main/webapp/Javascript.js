var nombreArchivo;
var campoNombre = document.getElementById('nombreArchivo');
function cargarNombre() {
    nombreArchivo = document.getElementById('archivo').files[0].name;
    campoNombre.value = nombreArchivo;
}
function cargarArchivo(elemento){
	var file = elemento.files[0];
	var objHidden = document.formulario.nombre;
	objHidden.value = file.name;
	document.formulario.action = "controladorProductos";
	document.formulario.submit();
	alert("¡Archivo procesado!");
}

function mensaje(opcion){
	switch (opcion) {
		case 0: 
			alert("¡Carga ejecutada exitosamente! Compruébelo al listar los productos en el módulo 'Reportes')");
			break;
		case 1: 
			alert("Error: no se ha seleccionado ningún archivo.");
			break;
		case 2: 
			alert("Error: datos inválidos. Por favor, compruebe el orden de las columnas en su archivo, el formato, etc.");
			break;
		case 3: 
			alert("Error: formato inválido.");
			break;
		case 4: 
			alert("Error: algunos registros no se cargaron.");
			break;
		case 5:
			alert("Error: no hay conexión con el servidor. Por favor, inténtelo más tarde.");
			break;
		case 6:
			alert(generarInforme());
			break;
		case 7:
			alert("Error: datos inválidos. Revise que el suyo sea un archivo separado por comas ','");
		default:
			break;
	}
}

function generarInforme() {
	var mensaje;
	
	added = document.getElementById("anadidos").value;
	send = document.getElementById("enviados").value;
	proveedoresInvalidos = document.getElementById("proveedoresINV").value;
	productosInvalidos = document.getElementById("productosINV").value;
	
	var report = "Se guardaron " + added + " de " + send + " registros. ";
	
	if (added == 0) {
		mensaje = "No se guardó ningún registro. ";
	} else {
		mensaje = report;
	}
	
	if (productosInvalidos != "") {
		mensaje += "Los siguientes productos YA existen el la base de datos: " + productosInvalidos;
	} else if (proveedoresInvalidos != "") {
		mensaje += "Los siguientes provedores NO existen el la base de datos: " + proveedoresInvalidos;
	} else {
		mensaje += "Por favor, revise que los NIT sean válidos y los códigos de nuevos productos no existan.";
	}
	return mensaje
}