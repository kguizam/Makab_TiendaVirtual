<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8" 
    import="com.DAO.Makab.ClienteDAO,
		    com.DTO.Makab.ClienteVO,
	        com.BO.Makab.Listar" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Makab</title>
<style>
* {
	text-align: center;
}
</style>
</head>
<body>
	<h1 style="color: red">
	<% 
		if (ClienteDAO.esUsuarioAutorizado(request.getParameter("usuario"), request.getParameter("contrasena"))) {
			out.print("Usted está autorizado :D");
			response.sendRedirect("main.html");
		} else {
			out.print("Usted NO está autorizado :(");
		}
	%>
	</h1>
	<p style="font-size: 15pt"> Si cree que esto es un error, contáctese con el administrador del sitio o con cualquiera de los miembros del grupo alpinito ;)</p>
	<a href="../login.html" style="font-size: 15pt">Volver al inicio de sesión</a>
</body>
</html>