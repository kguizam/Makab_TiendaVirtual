<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="css/main.css">
	    <link rel="stylesheet" href="css/media.css"> 
	    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon.ico">
	    <title>Makab</title>
	</head>
	<body>
		<header class="header-nav">		
		    <section class="header-container">
		    	<a href="Principal.jsp">
		        <img src="assets/Logo2.png" alt="Logo de mi tienda"></a>
		    </section>
	
		    <nav class="header-nav-container">
	           <ul>
	               <li> <a href="controlador?menu=Usuarios&accion=Inicio" 	 target="myFrame"> Usuarios </a></li>
	               <li> <a href="controlador?menu=Clientes&accion=Inicio" 	 target="myFrame"> Clientes </a></li>
	               <li> <a href="controlador?menu=Proveedores&accion=Inicio" target="myFrame"> Proveedores </a></li>
	               <li> <a href="controlador?menu=Productos&accion=Inicio" 	 target="myFrame"> Productos </a></li>
	               <li> <a href="controlador?menu=Ventas&accion=Inicio" 	 target="myFrame"> Ventas </a></li>
	               <li> <a href="controlador?menu=Reportes&accion=Inicio"	 target="myFrame"> Reportes </a></li>
	           </ul>
	     	</nav>
	       
	  		<main class="main-ucp">
				<iframe style="height: 100%; width: 100%; border:none;" name="myFrame"></iframe>
			</main>
	   </header>
	</body>
</html>