<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Date"%>
    <%@page import="co.edu.unbosque.ciclo3demo.Proveedores"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	    <link rel="stylesheet" href="w3.css">
	    <link rel="stylesheet" href="css/main.css">
		<link rel="stylesheet" href="css/media.css">
	    <title>Resultado</title>
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
	     
	    <div class="w3-container w3-center" >
	        <h2>Listado De Proveedores</h2>
	        <p>Proveedores registrados en la tienda Makab</p>
	      
	        <table class=" w3-table-all w3-card-4 w3-hoverable" style="max-width:900px; margin: auto">
	          <tr class="w3-cyan">
	            <th>NIT</th>
	            <th>Nombre</th>
	            <th>Ciudad</th>
	            <th>Direccion</th>
	            <th>Telefono</th>
	          </tr>
	          
	          <% ArrayList<Proveedores> lista= (ArrayList<Proveedores>) request.getAttribute("listaProveedores");
				for (Proveedores proveedor:lista){
				%>
	          <tr>         
	            <td><%=proveedor.getNitproveedor()%></td>
	            <td><%=proveedor.getNombre_proveedor()%></td>
	            <td><%=proveedor.getCiudad_proveedor()%></td>
	            <td><%=proveedor.getDireccion_proveedor()%></td>
	            <td><%=proveedor.getTelefono_proveedor()%></td>            
	          </tr> 
	          <%}%>         
	        </table>
	      </div>
	</body>
</html>