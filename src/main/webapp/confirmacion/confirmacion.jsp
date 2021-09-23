<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8" 
    import="com.DAO.Makab.DataAcessObject,
    		com.DTO.Makab.Usuario,com.BO.Makab.Peticiones" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/main.css">
    
    <link rel="icon" type="image/png" sizes="16x16" href="../assets/favicon.ico">
    <title>Makab</title>
</head>
<body>
    <header class="header">
        <section class="header-container">
            <a href="../vista-principal/main.html"> <img src="../assets/Logo2.png" alt="Logo de mi blog"> </a>
        </section>
    </header>
    <main class="main-ucp">
        <div class="confirmacion">
            <section class="confi__container">
                <img src="../assets/person.svg" alt="" width="50%">
            <section class="info-confirmacion">
                <h1>Usuario <span>NO</span> autorizado</h1>
                <div class="text-confirmacion">
                    <p class="text-one">¡Usuario o contraseña incorrecta, vuelva a intentarlo!.</p>
                </div>
                <button class="button-report"><a href="../login.html">Intentarlo de nuevo</a></button>
                <div class="text-confirmacion">
                    <p class="text-two">Si cree que es un error de la plataforma porfavor comuníquese con el administrador</p>
                </div>
            </section>
        </div>
        <%
		if (DataAcessObject.esUsuarioAutorizado(request.getParameter("usuario"), request.getParameter("contrasena"))) {
				response.sendRedirect("../vista-principal/main.html");
			}
		%>
    </main>
</body>

</html> 