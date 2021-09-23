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
<body>
	<% 
		// Captura de los datos del formulario
		String opcion = request.getParameter("submit");
	
		String stringCedula = request.getParameter("cedula");
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("correo");
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
	%>
	
	<h1><%= "Usted escogi� " + opcion %></h1>
	
	<p><% 
	// impresi�n de los datos captados
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
	// comprobaci�n de los requerimientos necesario
	
	if (Peticion.esPosibleLaConsulta(opcion, formulario)){
		out.print("<p style='color: green'> * La Consulta es posible! :D </p>");
		out.print("<p style='color: red'> * (si 'RESULTADO: null', la petici�n fue infructuosa):</p><br>");
		out.print("<p> RESULTADO: " + Peticion.intentar(opcion, formulario) + "</p>");
	} else {
		out.print("<p style='color: red'> La Consulta es imposible [Insuficiencia de datos]</p>");
	}
	%>
	
	
	
</body>
</html>