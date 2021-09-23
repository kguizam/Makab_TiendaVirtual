<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8" 
    import="com.DAO.Makab.DataAcessObject,
    		com.DTO.Makab.Usuario,
    		com.BO.Makab.Peticiones" %>

<!DOCTYPE html>
<html lang="en">
	<head>
	<!-- DEPRECATED JSP PAGE, PLEASE USE THE ONE ON "confirmacion/confirmacion.jsp" -->
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="../css/main.css">
		
		<link rel="icon" type="image/png" sizes="16x16" href="../assets/favicon.ico">
		<title>Makab</title>
	</head>
	<body>
		<header class="header">
			<section class="header-container">
				<a href=""> <img src="../assets/Logo2.png" alt="Logo de mi blog"> </a>
			</section>
		</header>
		<main class="main-register">
			<div class="login">
				<section class="login__container">
					<h1>ESTO ES UNA PRUEBA</h1>
					<p>Contáctese con el administrador del sitio si cree que es un error</p>
					<a href="main.html" class="button">Volver a ingresar</a>
				</section>
			</div>
		</main>
		<%
		if (DataAcessObject.esUsuarioAutorizado(request.getParameter("usuario"), request.getParameter("contrasena"))) {
				out.print("BIEN HECHO!");
				Peticiones.insertarDatos();
			}
		%>
    </body>
</html>