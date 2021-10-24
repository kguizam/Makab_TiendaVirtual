<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Date"%>
    <%@page import="co.edu.unbosque.ciclo3demo.Cliente"%>
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
		<div class="w3-container w3-center" >
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
		        <h2>Listado de Clientes</h2>
		        <p>Clientes registrados en la tienda Makab</p>
		      
		        <table class=" w3-table-all w3-card-4 w3-hoverable" style="max-width:900px; margin: auto">
		          <tr class="w3-cyan">
		            <th>Cedula</th>
		            <th>Nombre</th>
		            <th>Correo Electronico</th>
		            <th>Direccion</th>
		            <th>Telefono</th>
		          </tr>
		          
		          <%  ArrayList<Cliente> lista = (ArrayList<Cliente>) request.getAttribute("lista");
					for (Cliente cliente:lista){
					%>
		          <tr>         
		            <td><%=cliente.getCedula_cliente()%></td>
		            <td><%=cliente.getNombre_cliente()%></td>
		            <td><%=cliente.getEmail_cliente()%></td>
		            <td><%=cliente.getDireccion_cliente()%></td>
		            <td><%=cliente.getTelefono_cliente()%></td>            
		          </tr> 
		          <%}%>         
		        </table>
		      </div>
		</div>
	</body>
</html>