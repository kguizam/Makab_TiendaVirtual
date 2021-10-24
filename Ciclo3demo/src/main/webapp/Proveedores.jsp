<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="co.edu.unbosque.ciclo3demo.Proveedores"%>
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
                <h2>Proveedor</h2>
                <form method="get" action="controlador">
	                <div> <input class="input" name="txtnit" 	   type="number" value="${proveedorSeleccionado.getNitproveedor()}"    		placeholder="NIT" ></div>
	                <div> <input class="input" name="txtnombre"    type="text"   value="${proveedorSeleccionado.getNombre_proveedor()}"  	placeholder="Nombre Proveedor" ></div>
	                <div> <input class="input" name="txtdireccion" type="text"   value="${proveedorSeleccionado.getDireccion_proveedor()}" 	placeholder="Dirección"> </div>
	                <div> <input class="input" name="txttelefono"  type="number" value="${proveedorSeleccionado.getTelefono_proveedor()}" 	placeholder="Teléfono" > </div>
	                <div> <input class="input" name="txtciudad"    type="text"   value="${proveedorSeleccionado.getCiudad_proveedor()}"	   	placeholder="Ciudad" > </div>
	                
	                <section class="ucp__button--container">
	                 	<button name="accion" value="Agregar" 	class="ucp__button"> Crear </button>
			            <!-- <button name="accion" value="Agregar" 	class="ucp__button"> Actualizar </button>  -->             
			       		<button name="accion" value="Cargar" 	class="ucp__button"><!-- Cargar Datos  --> Consultar </button>
			            <button name="accion" value="Listar" 	class="ucp__button"> Listar </button>
			            <button name="accion" value="Eliminar" 	class="ucp__button"> Borrar </button>
	                </section>
	                
	                <input type="hidden" type="text" name="menu" value="Proveedores">
                </form>
            </section>
        </div>
	</body>
</html>