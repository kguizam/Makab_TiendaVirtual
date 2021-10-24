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
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="w3.css">
    <title>Calzado Pie Grande</title>
</head>
<body>    

      <div class="w3-card-4 w3-content w3-white" style="max-width:1000px; margin-top: 70px">
      <div class="w3-panel w3-red w3-display-container">
    <span onclick="this.parentElement.style.display='none'"
    class="w3-button w3-large w3-display-topright">&times;</span>
    <h3>Danger!</h3>
    <p>Red often indicates a dangerous or negative situation.</p>
  </div>
        <div class="w3-container  w3-teal">
          <h2>Datos De Usuarios</h2> 
        </div>
        <form class="w3-container" method="get" action="controlador">

          <div class="w3-container w3-half">
          
          <input type="hidden" type="text" name="menu" value="Usuarios">
            <p>
              <label><b>Nombre Completo</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtnombre" type="text" value="${usuarioSeleccionado.getNombre_usuario()}">
            </p>
            <p>
              <label><b>Cedula</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtcedula" type="text" value="${usuarioSeleccionado.getCedula_usuario()}">
            </p>            
            <p>
              <label><b>Correo Electronico</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtemail" type="text" value="${usuarioSeleccionado.getEmail_usuario()}">
            </p>
          </div>

          <div class="w3-container w3-half ">
            <p>
              <label><b>Usuario</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtusuario" type="text" value="${usuarioSeleccionado.getUsuario()}">
            </p>
            <p>
              <label><b>Password</b></label>
              <input class="w3-input w3-border w3-light-grey" name="txtpassword" type="text" value="${usuarioSeleccionado.getPassword()}">
            </p>
          </div>              
          
          <div class="w3-container w3-center">
            <p>
              <button name="accion" value="Agregar" class="w3-btn  w3-teal w3-margin-right">Crear</button>
              <button name="accion" value="Listar" class="w3-btn  w3-teal w3-margin-right">Consultar</button>
              <button name="accion" value="Cargar" class="w3-btn  w3-teal w3-margin-right">Cargar Datos</button>
              <button name="accion" value="Eliminar" class="w3-btn  w3-teal w3-margin-right">Borrar</button>
                       
            </p>  
          </div>         
        </form>
      </div>       
</body>
</html>