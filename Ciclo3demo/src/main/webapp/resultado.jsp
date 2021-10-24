<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Date"%>
    <%@page import="co.edu.unbosque.ciclo3demo.Usuarios"%>
    <%@page import="co.edu.unbosque.ciclo3demo.Cliente"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/media.css">
    <title>Document</title>
</head>
	<body>
		<form method="get" action="controlador">
	    	<div class="main-buttons">	
	            <p>
			        <button name="accion" class="button" value="ListaUsuarios">Lista Usuarios</button>
			        <button name="accion" class="button" value="ListaClientes">Lista Clientes</button> 
			        <button name="accion" class="button" value="ListaProveedores">Lista Proveedores</button>
			        <button name="accion" class="button" value="ListaProductos">Lista Productos</button>            
			        <button name="accion" class="button" value="ListaDetalleVentas">Lista Ventas</button>                              
	       		</p>  
			</div> 
			<input type="hidden" type="text" name="menu" value="Reportes">
	 	</form>
	</body>
</html>