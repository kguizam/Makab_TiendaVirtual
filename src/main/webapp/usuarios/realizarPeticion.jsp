<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="com.DAO.Makab.DataAcessObject,
    		com.DTO.Makab.Usuario,
    		java.util.Enumeration,
    		java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		// Captura de los datos del formulario
		String opcion = request.getParameter("submit");
	
		String cedula = request.getParameter("cedula");
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("correo");
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
	%>
	
	<h1><%= "Usted escogi� " + opcion %></h1>
	
	<p><% 
	// impresi�n de los datos captados
	int datosEnBlanco = 0;
	String[] nombresFormulario = {
		"C�dula", "Nombre", "Correo", "Usuario", "Contrasena"	
	};
	String[] formulario = {
		cedula, nombre, correo, usuario, contrasena
	};
	for (int i = 0; i < 5; i++) {
		out.println(nombresFormulario[i] + " = " + formulario[i] + "<br>");
		if (formulario[i] == "") datosEnBlanco += 1;
	}
	%></p>
	
	<p><%
	// comprobaci�n de los requerimientos necesarios
	boolean realizarTarea = false;
	long cedulaLong; boolean cedulaDisponible = false;
	
	try {
		cedulaLong = Long.parseLong(cedula);
		cedulaDisponible = true;
	} catch (Exception e) {
		// e.printStackTrace();
	}
	
	switch (opcion) {
		case "consultar":
		case "borrar":
			if (cedulaDisponible){
				out.print("Es posible intentar la tarea con los datos suministrados :)");
				realizarTarea = true;
			} else {
				out.print("Es imposible realizar la tarea con los datos suministrados :'( <br> [C�dula inv�lida]");
			}
			break;
		case "actualizar":
			if (cedulaDisponible && datosEnBlanco < 4){
				out.print("Es posible intentar la tarea con los datos suministrados :)");
				realizarTarea = true;
			} else {
				out.print("Es imposible realizar la tarea con los datos suministrados :'(");
			}
			break;
		case "crear":
			if (cedulaDisponible && datosEnBlanco == 0){
				out.print("Es posible intentar la tarea con los datos suministrados :)");
				realizarTarea = true;
			} else {
				out.print("Es imposible realizar la tarea con los datos suministrados :'(");
			}
			break;
	}
	%></p>
	
	<p><% 
	// Realizaci�n de la tarea (si es posible)
	boolean ejecucionExitosa = false;
	if (realizarTarea) {
		switch (opcion) {
		case "consultar":
			Usuario user = DataAcessObject.consultarUsuario(cedulaLong);
			if (user != null) {
				out.print(user.toString());
				ejecucionExitosa = true;
			}
			break;
		case "borrar":
			ejecucionExitosa = DataAcessObject.eliminar(cedulaLong);
			break;
		case "actualizar":
			//DataAcessObject.actualizar(cedulaLong, formulario);
			for (int i = 1; i < 4; i++) {
				if (formulario[i] != "") {
					out.print(i + formulario[i] + "<br>");
				} else {
					formulario[i] = null;
					out.print(i + " Null");
				}
			}
			break;
		case "crear":
			Usuario newUser = new Usuario(0, cedulaLong, nombre, correo, usuario, contrasena);
			DataAcessObject.insertar(newUser);
			ejecucionExitosa = true;
			break;
		}
	%> </p>
	
	<% 
	// Notificaci�n al usuario del resultado final de la operaci�n
	if (ejecucionExitosa) {
		out.print("<br> <p style='color: green'>OPERACI�N REALIZADA CON �XITO </p>");
	} else {
		out.print("<br> <p style='color: red'>LA OPERACI�N FRACAS�, INT�NTELO DE NUEVO :'('</p>");
	}
	%>
	
</body>
</html>