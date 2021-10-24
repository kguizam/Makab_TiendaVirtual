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
	    <link rel="stylesheet" href="css/main.css">
	    <link rel="stylesheet" href="css/media.css">
	    <title>Makab</title>
	</head>
	<body>
	    <main class="main-ucp">
	        <div class="ucp">
	            <section class="ucp__container">
	                <h2>Productos</h2>
	                <p> Por favor, cargue un csv con el siguiente orden de registros separados por comas: [Código, Nombre, Nit, PrecioCompra, Iva, PrecioVenta]</p>
	                <form method="post" name="formulario" enctype="multipart/form-data"> <!-- action="productos.jsp"-->
		                <div><input class="input-product" type="text" placeholder="Nombre del archivo" value="" name="nombreArchivo" id="nombreArchivo" disabled></div>
		                
		                <section class="ucp__button--product">
		                	<!-- <input type="file" accept=".csv" id="button" style="display: none"> -->
		                	<input type="file" name="archivo" id="archivo" class="input-product" onchange="cargarNombre()" style="display: none">
		                	<label for="archivo" class="ucp__button">Examinar</label>
		                </section>
		                
		                <section class="ucp__button--carga">
		                	<!-- <input type="submit" class="ucp__button" value="Cargar"> -->
		                	<input type="submit" value="Cargar" name="Procesar" onclick="cargarArchivo(archivo)" class="ucp__button">
		                </section>
			                <input type="hidden" name="nombre" value="">
							<input type="hidden" name="nombre2" value="">
	                </form>
	          	</section>
	          	<input type="hidden" id="anadidos" value="<%= request.getAttribute("anadidos") %>">
				<input type="hidden" id="enviados" value="<%= request.getAttribute("enviados") %>">
				
				<input type="hidden" id="proveedoresINV" value="<%= request.getAttribute("proveedoresNoValidos") %>">
				<input type="hidden" id="productosINV" value="<%= request.getAttribute("productosNoValidos") %>">
	                <!-- 
	                <form method="post" name="formulario" enctype="multipart/form-data">
						<div class="row">
							<div class="col-11">
								<input type="file" name="archivo" class="form-control" id="archivo">
							</div>
							<div class="col-1">
								<input type="submit" value="Procesar" name="Procesar" onclick="cargarArchivo(archivo)" class="btn btn-success aling-center">
							</div>
						</div>
						<input type="hidden" name="nombre" value="">
						<input type="hidden" name="nombre2" value="">
					</form>
					 -->
	        </div>
	    </main>
	    <script type="text/javascript" src="Javascript.js" charset="utf-8"></script>
	    <script> setTimeout (()=> window.onload = mensaje(<%= request.getAttribute("error") %>), 1); </script>
	</body>
</html>