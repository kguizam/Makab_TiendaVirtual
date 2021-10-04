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
<title>Petici�n usuario</title>
</head>
<!-------- Estructura de la petici�n en 3 pasos --------->
  <!--  1) La p�gina captura los datos del formulario. 
  	    2) Se organiza la informaci�n en una mejor
  		estructura de dato. Se imprimen.            
        3) Se hace un llamado a las funciones que:
  		  * Comprueban que la informaci�n es suficiente  
 		  * Llevan a cabo la acci�n y retornan una respuesta 
 		    (null indica que hubo alg�n error, usualmente que
 		    el usuario NO existe, o que NO fue posible la 
 		    conexi�n con la base de datos.)       	   -->
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
	
	<h1><%= "Usted escogi� " + opcion %></h1>
	
	<p><% 
	// 2. Organizaci�n e impresi�n
		String[] nombresFormulario = {
			"C�dula", "Nombre", "Correo", "Usuario", "Contrase�a"	
		};
		String[] formulario = {
			stringCedula, nombre, correo, usuario, contrasena
		};
		for (int i = 0; i < 5; i++) {
			out.println(nombresFormulario[i] + " = " + formulario[i] + "<br>");
		}
	%></p>
	
	<%
	// 3. Comprobaci�n de los requerimientos & ejecuci�n
	
	if (Peticion.esPosibleLaConsulta(opcion, formulario)){
		out.print("<p style='color: green'> * La Consulta es posible! :D </p>");
		out.print("<p style='color: red'> * (si 'RESULTADO: null', la petici�n fue infructuosa):</p><br>");
		out.print("<p> RESULTADO: " + Peticion.intentar(2, opcion, formulario) + "</p>");
	} else {
		out.print("<p style='color: red'> La Consulta es imposible [Insuficiencia de datos]</p>");
	}
	%>
	
	<% 
	// DISCLAIMER %>
	<br><br><br>Esta p�gina es temporal, y s�lo est� aqu� para mostrar resultados
	inmediatos. La idea es realizar todas las tareas que hace esta p�gina
	en el mismo formulario HTML "Usuarios", haciendo uso de Javascript 
	para la validaci�n de los datos en el frontend y la interacci�n con el 
	usuario.

</body>
</html>