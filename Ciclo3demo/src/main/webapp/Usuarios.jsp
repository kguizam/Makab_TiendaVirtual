<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="co.edu.unbosque.ciclo3demo.Usuarios"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="css/main.css">
	    <link rel="stylesheet" href="css/media.css">
	    <title>Makab</title>
	</head>
	<body>
		<div class="ucp">
		    <section class="ucp__container">
		        <h2>Usuarios</h2>
		        <form method="get" action="controlador">
			        <div> <input class="input" placeholder="Cédula" 			name="txtcedula"   type="number"   value="${usuarioSeleccionado.getCedula_usuario()}"> </div>
			        <div> <input class="input" placeholder="Nombre Completo" 	name="txtnombre"   type="text"     value="${usuarioSeleccionado.getNombre_usuario()}"> </div>
			        <div> <input class="input" placeholder="Correo Electrónico" name="txtemail"    type="email"    value="${usuarioSeleccionado.getEmail_usuario()}"> </div>
			        <div> <input class="input" placeholder="Usuario" 			name="txtusuario"  type="text"     value="${usuarioSeleccionado.getUsuario()}"> </div>
			        <div> <input class="input" placeholder="Contraseña" 		name="txtpassword" type="password" value="${usuarioSeleccionado.getPassword()}"> </div>
			        
			        <section class="ucp__button--container">
				        <button name="accion" value="Agregar" 	class="ucp__button"> Crear </button>
			            <!-- <button name="accion" value="Agregar" 	class="ucp__button"> Actualizar </button>  -->             
			       		<button name="accion" value="Cargar" 	class="ucp__button"><!-- Cargar Datos  --> Consultar </button>
			            <button name="accion" value="Listar" 	class="ucp__button"> Listar </button>
			            <button name="accion" value="Eliminar" 	class="ucp__button"> Borrar </button>
			        </section>
			        
			        <input type="hidden" type="text" name="menu" value="Usuarios">
		        </form>
		    </section>
		</div>
	</body>
</html>