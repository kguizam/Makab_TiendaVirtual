<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8" import="com.BO.Makab.Peticion" %>
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
                <li> <a class="header-user-container" href="../productos/productos.html">Productos</a></li>
                <li> <a href="../ventas/ventas.html">Ventas</a></li>
                <li> <a href="../reportes/reportes.html">Reportes</a></li>
            </ul>
        </nav>
    </header>
    <main class="main-ucp">
        <div class="ucp">
            <section class="ucp__container">
                <h2>Productos</h2>
                <!-- CUERPO DEL FORMULARIO -->                
                <form action="productos.jsp" method="post">
	                <div>
	                    <input class="input-product" type="text" placeholder="Nombre del archivo" value="<%= request.getParameter("archivo") %>" name="archivo">
	                </div>
	                <section class="ucp__button--product">
	                <input type="file" accept=".csv" id="button" style="display: none">
	                <label for="button" class="ucp__button">Examinar</label>
	                </section>
	                <section class="ucp__button--carga">
	                <input type="submit" class="ucp__button" value="Cargar">
	                </section>
                </form>
            </section>
            
        </div>
    </main>
</body>
<script>
    alert(<%= '"' + Peticion.insertarCSV(request.getParameter("archivo")) + '"'%>  );
</script>
</html>