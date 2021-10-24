<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Date"%>
    <%@page import="co.edu.unbosque.ciclo3demo.Productos"%>
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
	     
	    <div class="w3-container w3-center" style="margin-top: 0px">
	        <h2>Listado De Los Productos</h2>
	        <p>Productos registrados en la tienda Makab</p>
	      
	        <table class=" w3-table-all w3-card-4 w3-hoverable" style="max-width:900px; margin: auto">
	          <tr class="w3-cyan">
	            <th>Codigo Producto</th>
	            <th>Nombre</th>
	            <th>Precio Compra</th>
	            <th>IVA Compra</th>
	            <th>Precio Venta</th>
	            <th>NIT Proveedor</th>
	          </tr>
	          
	          <% ArrayList<Productos> lista= (ArrayList<Productos>) request.getAttribute("listaProductos");
				for (Productos producto:lista){
				%>
	          <tr>         
	            <td><%=producto.getCodigo_producto()%></td>
	            <td><%=producto.getNombre_producto()%></td>
	            <td><%=producto.getPrecio_compra()%></td>
	            <td><%=producto.getIvacompra()%></td>
	            <td><%=producto.getPrecio_venta()%></td> 
	            <td><%=producto.getNitproveedor()%></td>           
	          </tr> 
	          <%}%>
	        </table>
	      </div>
	</body>
</html>