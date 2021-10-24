<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="co.edu.unbosque.ciclo3demo.Cliente"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
	    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	    <link rel="stylesheet" href="w3.css">
		<title>Ventas Alpinito</title>
	</head>
	<body>
		<div class="w3-card-4 w3-content w3-white" style="max-width:1300px; margin-top: 40px">
	        <div class="w3-container  w3-cyan">
				<h2>Ventas</h2> 
	        </div>
	
	        <div class="w3-container">
	          	<form  method="get" action="controlador">
	            	<div class="w3-container w3-quarter w3-row">
	                	<h3>Datos Cliente</h3>
	                	
	                	<div  style="padding-right: 5px;padding-bottom: 3px;">
	                      	<input class="w3-input w3-border" type="text" placeholder="Cedula" name="cedulaCliente" value="${clienteSeleccionado.getCedula_cliente()}">
	                    </div>
		                <div style="padding-right: 5px; padding-bottom: 8px;">
		                	<input class="w3-input w3-border" type="text" placeholder="Nombre" name="nombreCliente" value="${clienteSeleccionado.getNombre_cliente()}" disabled>
		                </div>
		                <div  style="padding-right: 5px;padding-bottom: 8px;">
	                    	<input class="w3-input w3-border" type="text" placeholder="Correo Electronico" name="correoCliente" value="${clienteSeleccionado.getEmail_cliente()}" disabled>
	                    </div>
		               	<div  style="padding-right: 5px;padding-bottom: 8px;">
		                	<input class="w3-input w3-border" type="text" placeholder="Direccion" name="direccionCliente" value="${clienteSeleccionado.getDireccion_cliente()}" disabled>
		                </div>
		                <div  style="padding-right: 5px;padding-bottom: 8px;">
		                	<input class="w3-input w3-border" type="text" placeholder="Telefono" name="telefonoCliente" value="${clienteSeleccionado.getTelefono_cliente()}" disabled>
		                </div> 
	                    
	                    <input type="hidden" name="menu" value="Ventas">
	                  	<input type="hidden" name="UsuarioActivo" value="${usuarioSeleccionado.getCedula_usuario()}">
	                                         
	                    <div class="w3-container ">   
	                        <p>
		                        <button class="w3-btn  w3-cyan w3-margin-right" name="accion" value="Buscar" >Buscar</button>                        
	                        </p>
	                    </div>
	            	</div>

		       		<div class="w3-container w3-quarter ">
			            <h3>Datos Productos</h3>
	
				        <div  style="padding-right: 5px; padding-bottom: 8px;">
			              	<input class="w3-input w3-border" type="text" placeholder="Nombre Producto" name="nombreProducto" value="${productoSeleccionado.getNombre_producto()}" readonly>
			            </div>
			            <div style="padding-right: 5px;padding-bottom: 8px;">
			              	<input class="w3-input w3-border" type="text" placeholder="Valor" name="valorProducto" value="${productoSeleccionado.getPrecio_venta()}" readonly>
			            </div>
			            <div  style="padding-right: 5px;padding-bottom: 8px;">
			              	<input class="w3-input w3-border" type="text" placeholder="IVA" name="iva" value="${productoSeleccionado.getIvacompra()}" readonly>
			            </div>
			  
			            <div class="w3-half" style="padding-right: 5px;padding-bottom: 8px;">
				            <input class="w3-input w3-border" style="margin-bottom: 8px;" type="text" placeholder="Codigo Producto" name="codigoProducto" value="${productoSeleccionado.getCodigo_producto()}">
				            <input class="w3-input w3-border" style="margin-bottom: 8px;" type="text" placeholder="Cantidad" name="cantidadProducto">
			            </div>
	                  	
			            <div class="w3-quarter">
			              <button class="w3-btn  w3-cyan w3-margin-right" style="margin-bottom: 11px;" name="accion" value="Buscar">Buscar   </button> 
			              <button class="w3-btn  w3-cyan w3-margin-right" style="margin-bottom: 8px;" name="accion" value="AgregarProducto">Agregar  </button> 
			            </div>
		        	</div>            
	     		</form>
	
		        <div class="w3-container w3-half">
		            <h3>Productos a comprar</h3>
		        	<input class="w3-input w3-border" type="text" placeholder="N° Factura" name="numeroFactura" value="${numeroFactura}">
	            
	            <table class=" w3-table-all w3-card-4 w3-hoverable" style="max-width:900px; margin: auto">
	              	<tr class="w3-cyan">
		                <th>#</th>
		                <th>Codigo</th>
		                <th>Producto</th>
		                <th>Cantidad</th>
		                <th>Precio</th>              
		                <th>IVA</th>
		                <th>Total</th>
	              	</tr>  
	            	<c:forEach var="lista" items="${listaventas}">
			            <tr>         
				            <td>${lista.getCodigo_detalle_venta()}</td>
				            <td>${lista.getCodigo_producto() }</td>
				            <td>${lista.getDescripcion_producto()}</td>	                
				            <td>${lista.getCantidad_producto()}</td>   
				            <td>${lista.getPrecio_producto()}</td>
				            <td>${lista.getValor_iva() }</td>
				            <td>${lista.getValor_venta()}</td>          
			            </tr>    
            		</c:forEach>          
	            </table>  
	
	            <div class="w3-third" style="padding-right: 5px;padding-bottom: 8px;margin-top: 30px;">
		            <h5 style="margin-bottom: 18px; margin-left: 80px;">Sub Total</h5>
		            <h5 style="margin-bottom: 18px;margin-left: 80px;">IVA</h5>
		            <h5 style="margin-bottom: 8px;margin-left: 80px;">Total a Pagar</h5>
	            </div>
	            <div class="w3-half" style="padding-right: 5px;padding-bottom: 8px;margin-top: 30px;">
		            <input class="w3-input w3-border" style="margin-bottom: 8px;" type="text" value="${totalsubtotal}" placeholder="0.000" name="txtsubtotal" disabled="disabled">
		            <input class="w3-input w3-border" style="margin-bottom: 8px;" type="text" value="${totaliva}" placeholder="0.000" name="txttotaliva" disabled="disabled">
		            <input class="w3-input w3-border" style="margin-bottom: 8px;" type="text" value="${totalapagar}" placeholder="0.000" name="txttotalapagar" disabled="disabled">
	            </div>
	
	            <div class="w3-row">
		            <a class="w3-button" onclick="print()" href="controlador?menu=Ventas&accion=GenerarVenta&cedulaCliente=${clienteSeleccionado.getCedula_cliente()}&UsuarioActivo=${usuarioSeleccionado.getCedula_usuario()}&numeroFactura=${numeroFactura}">Generar Venta</a>
		            <a class="w3-button" href="controlador?menu=Ventas&accion=NuevaVenta">Nueva Venta</a>
	            </div>
				</div>
	    	</div>
		</div>
	</body>
</html>