<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="com.BO.Makab.Peticion,
    		com.DAO.Makab.DataAcessObject,
    		com.DTO.Makab.Usuario,
    		java.util.Enumeration,
    		java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
	p {
		font-size: 16pt;
	}
</style>
<title>Petición usuario</title>
</head>
<!-------- Estructura de la petición en 3 pasos --------->
  <!--  1) La página captura los datos del formulario. 
  	    2) Se organiza la información en una mejor
  		estructura de dato. Se imprimen.            
        3) Se hace un llamado a las funciones que:
  		  * Comprueban que la información es suficiente  
 		  * Llevan a cabo la acción y retornan una respuesta 
 		    (null indica que hubo algún error, usualmente que
 		    el usuario NO existe, o que NO fue posible la 
 		    conexión con la base de datos.)       	   -->
<body>
	<% 
	// 1. Captura de los datos
		String opcion = request.getParameter("submit");
	
		String stringCedula = request.getParameter("cedula");
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("correo");
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
	%>
	
	<h1><%= "Usted escogió " + opcion %></h1>
	
	<p><% 
	// 2. Organización e impresión
		String[] nombresFormulario = {
			"Cédula", "Nombre", "Correo", "Usuario", "Contraseña"	
		};
		String[] formulario = {
			stringCedula, nombre, correo, usuario, contrasena
		};
		for (int i = 0; i < 5; i++) {
			out.println(nombresFormulario[i] + " = " + formulario[i] + "<br>");
		}
	%></p>
	
	<%
	// 3. Comprobación de los requerimientos & ejecución
	
	if (Peticion.esPosibleLaConsulta(opcion, formulario)){
		out.print("<p style='color: green'> * La Consulta es posible! :D </p>");
		out.print("<p style='color: red'> * (si 'RESULTADO: null', la petición fue infructuosa):</p><br>");
		out.print("<p> RESULTADO: " + Peticion.intentar(2, opcion, formulario) + "</p>");
	} else {
		out.print("<p style='color: red'> La Consulta es imposible [Insuficiencia de datos]</p>");
	}
	%>
	
	<% 
	// DISCLAIMER %>
	<br><br><br>Esta página es temporal, y sólo está aquí para mostrar resultados
	inmediatos. La idea es realizar todas las tareas que hace esta página
	en el mismo formulario HTML "Usuarios", haciendo uso de Javascript 
	para la validación de los datos en el frontend y la interacción con el 
	usuario.

</body>
</html>