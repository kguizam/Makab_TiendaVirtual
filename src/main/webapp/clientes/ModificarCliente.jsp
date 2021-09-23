<%@ page
	import="
		java.io.*,
		java.util.*,
		com.BO.Makab.*,
		com.DTO.Makab.*"%>
<!DOCTYPE html>
<html>
<%
// ClienteController cc=new ClienteController();
	Usuario cli=new Usuario();
	int id=Integer.parseInt(request.getParameter("idcliente"));
	cli.setIdCliente(id);
	cli.setNombreCliente(request.getParameter("nombre"));
	cli.setApellidoCliente(request.getParameter("apellido"));
	
	
	//cc.modificarCliente(cli);
%>
<head>
<meta charset="ISO-8859-1">
<title>Page Redirection</title>
</head>
<body>
	<h1>Using GET Method to Read Form Data</h1>
	<ul>
		<li><p>
				<b>Id: </b>
				<%= request.getParameter("idcliente") %>
			</p></li>
		<li><p>
				<b>Nombre:</b>
				<%= request.getParameter("nombre") %>
		<li><p>
				<b>Apellido:</b>
				<%= request.getParameter("nombre") %>
			</p></li>
	</ul>
</body>
</html>