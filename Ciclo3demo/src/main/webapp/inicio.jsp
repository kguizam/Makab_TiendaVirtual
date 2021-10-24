<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	    <header class="header">
	        <section class="header-container">
	            <a href="Principal.jsp"> <img src="assets/Logo2.png" alt="Logo de mi tienda"> </a>
	        </section>
	    </header>
	    <main class="main">
	        <div class="main__container--title">
	            <h1>Bienvenidos a </h1>
	            <img src="assets/Logo.png" alt="">
	        </div>
	        
	        <div class="login">
	            <section class="login__container">
	                <h2>Inicia sesión</h2>
	                <form class="login__container--form" method="get" action="./DemoServlet">
	                
	                    <input class="input" placeholder="Usuario" name="txtusuario" type="text">
	                    <input class="input" placeholder="Contraseña" name="txtpassword" type="password">
	                    
	                    <section class="main-buttons">
	                        <a href=""></a>
	                        <button  value="Ingresar" name="accion" class="button">Ingresar</button>
	                        <button class="button">Cancelar</button>
	                    </section>
	                    
	                    <div class="login__container--remember-me">
	                        <label><input type="checkbox" id="cbox1" value="first_checkbox"> Recuérdame</label>
	                        <a href="">Olvidé mi contraseña</a>
	                    </div>
	                </form>
	                <section class="login__container--social-media">
	                    <div><img src="assets/google.svg"> Inicia sesión con Google</div>
	                    <div><img src="assets/facebook.svg"> Inicia sesión con Facebook</div>
	                    <div><img src="assets/apple.svg"> Inicia sesión con Apple</div>
	                </section>
	
	                <p class="login__container--register">No tienes ninguna cuenta <a href="#">Regístrate</a></p>
	            </section>
	        </div>
	    </main>
    	<% if (request.getAttribute("respuesta") != null) { %>
    		<script type="text/javascript">
    			alert("<%= request.getAttribute("respuesta")%>");
    		</script>
		<% }%> 
	</body>
</html>