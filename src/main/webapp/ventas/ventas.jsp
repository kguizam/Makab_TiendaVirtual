<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.BO.Makab.*, com.DTO.Makab.Producto"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/media.css">
    <link rel="icon" type="image/png" sizes="16x16" href="../assets/favicon.ico">
    <title>Makab</title>
</head>
<body>
    <header class="header-nav">
        <section class="header-container">
            <a href="../vista-principal/main.html"> <img src="../assets/Logo2.png" alt="Logo de mi blog"> </a>
        </section>

        <nav class="header-nav-container">
            <ul>
                <li> <a href="../usuarios/users.html">Usuarios</a></li>
                <li> <a href="../clientes/clientes.html">Clientes</a></li>
                <li> <a href="../proveedores/proveedores.html">Proveedores</a></li>
                <li> <a href="../productos/productos.html">Productos</a></li>
                <li> <a class="header-user-container" href="../ventas/ventas.html">Ventas</a></li>
                <li> <a href="../reportes/reportes.html">Reportes</a></li>
            </ul>
        </nav>
    </header>
    
    <%
    	// Programar comportamiento personalizado por botón
    	String[] codigosProductos = {request.getParameter("numero-1"), request.getParameter("numero-2"), request.getParameter("numero-3")};
    	Producto[] productos = {null, null, null};
    	String[] nombresProductos = {"", "", ""};
    	
    	int i = 0;
    	for (String codigo : codigosProductos) {
    		if (codigo != "") {
				productos[i] = Peticion.devolverProducto(Integer.parseInt(codigo));
				if (productos[i] != null) nombresProductos[i] = productos[i].getNombre();
    		}
    		i++;
    	}
    %>
    
    <main class="main-ucp">
        <div class="ucp">
	        <form method="post" action="ventas.jsp">
		        <section class="ventas__container">
		        <!-- AQUÍ EMPIEZA LA ACCIÓN U,U -->
		            <h2>Ventas</h2>
		            	<div class="ccc-container">
		            <p>Cédula</p>
		            <input type="number" name="cedula">
		            <button name="submit" value="consultar-cliente">Consultar</button>
		            <p>Cliente</p>
		            <input type="text">
		            <p>Consec</p>
		            <input type="text">
		            </div>
		            <div class="info-container">
		                <p>Codigo del Producto</p>
		                <p>Nombre del Producto</p>
		                <p>Cantidad</p>
		                <p>Valor Total</p>
		            </div>
		            <div class="write-container">
		                <input  type="number" name="numero-1" value="<%= request.getParameter("numero-1") %>">
		                <button value="consultar-cliente1" name="submit">Consultar</button>
		                <input  type="text"	  name="producto-1" value="<%= nombresProductos[0] %>">
		                <input  type="number" name="cantidad-1" class="cantidad-int">
		                <input  type="number" name="valor-1">
		            </div>
		            <div class="write-container">
		                <input  type="number" name="numero-2" value="<%= request.getParameter("numero-2") %>">
		                <button value="consultar-cliente2" name="submit">Consultar</button>
		                <input  type="text"	  name="producto-2" value="<%= nombresProductos[1] %>">
		                <input  type="number" name="cantidad-2" class="cantidad-int">
		                <input  type="number" name="valor-2">
		            </div>
		            <div class="write-container">
		                <input  type="number" name="numero-3" value="<%= request.getParameter("numero-3") %>">
		                <button value="consultar-cliente3" name="submit">Consultar</button>
		                <input  type="text"	  name="producto-3" value="<%= nombresProductos[2] %>">
		                <input  type="number" name="cantidad-3" class="cantidad-int">
		                <input  type="number" name="valor-3">
		            </div>
		            <div class="button-confirmar-container">
		                <button>Confirmar</button>
		                <div class="totales-container">
		                    <p>Total Venta</p>
		                    <p>Total IVA</p>
		                    <p>Total con IVA</p>
		                </div>
		                <div class="inputs-ventas">
		                    <input type="number">
		                    <input type="number">
		                    <input type="number">
		                </div>
		            </div>
		            
		        </section>
	        </form>
    	</div>
    </main>
</body>
</html>