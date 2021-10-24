package co.edu.unbosque.ciclo3demo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DemoServlet() {
		super();
	}

	public void validarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			ArrayList<Usuarios> lista = JSON_Usuarios.getJSON();
			request.setAttribute("lista", lista);
			String usua = request.getParameter("txtusuario");
			String pass = request.getParameter("txtpassword");
			int respuesta = 0;
			String txtRespuesta;
			for (Usuarios usuario:lista){
				if (usuario.getUsuario().equals(usua) && usuario.getPassword().equals(pass)) {
				    request.setAttribute("usuario", usuario);
				    request.getRequestDispatcher("/Principal.jsp").forward(request, response);
				    txtRespuesta = "Ingreso exitoso!";
				    respuesta = 1;
				}
			}
				
			if (respuesta == 0) {
				if (usua == "" || pass == "") {
					txtRespuesta = "Usuario o contraseña vacíos";
				} else {
					txtRespuesta = "Usuario o contraseña inválidos";
				}
				request.setAttribute("respuesta", txtRespuesta);
				request.getRequestDispatcher("/inicio.jsp").forward(request, response);
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
	      if (accion.equals("Ingresar")) {
		    this.validarUsuarios(request, response);		
		}
	}
}