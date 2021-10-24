<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <title>Calzado Pie Grande</title>
</head>
<body>    

      <div class="w3-card-4 w3-content w3-white" style="max-width:1000px; margin-top: 70px">
        <div class="w3-container  w3-teal">
          <h2>Datos De Los Productos</h2> 
        </div>
        <form class="w3-container" method="get" action="controlador">

          <div class="w3-container w3-half">
          
          <input type="hidden" type="text" name="menu" value="Productos">
          	<p>
              <label><b>Codigo Producto</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtcodigo" type="text" value="${usuarioSeleccionado.getNombre_usuario()}">
            </p>
            <p>
              <label><b>Nombre Producto</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtnombre" type="text" value="${usuarioSeleccionado.getNombre_usuario()}">
            </p>     
            <p>
              <label><b>Precio Compra</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtcompra" type="text" value="${usuarioSeleccionado.getCedula_usuario()}">
            </p>        
          </div>

          <div class="w3-container w3-half ">               
            <p>
              <label><b>IVA Compra</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtiva" type="text" value="${usuarioSeleccionado.getCedula_usuario()}">
            </p>         
            <p>
              <label><b>Precio Venta</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtventa" type="text" value="${usuarioSeleccionado.getEmail_usuario()}">
            </p>
             <p>
              <label><b>NIT Proveedor</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtproveedor" type="text" value="${usuarioSeleccionado.getEmail_usuario()}">
            </p>
          </div>              
          
          <div class="w3-container w3-center w3-mar w3-margin-top">
            <p>
              <button name="accion" value="Agregar" class="w3-btn  w3-teal w3-margin-right">Crear</button>
              <button name="accion" value="Agregar" class="w3-btn  w3-teal w3-margin-right">Actualizar</button>             
              <button name="accion" value="Cargar" class="w3-btn  w3-teal w3-margin-right">Cargar Datos</button>
               <button name="accion" value="Listar" class="w3-btn  w3-teal w3-margin-right">Consultar</button>
              <button name="accion" value="Eliminar" class="w3-btn  w3-teal w3-margin-right">Borrar</button>
                       
            </p>  
          </div>         
        </form>
      </div>      
      
      
              
      
    
</body>
</html>