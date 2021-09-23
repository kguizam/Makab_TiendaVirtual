package com.DAO.Makab;
import java.sql.*;
import com.DTO.Makab.*;
//import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase que permite el acceso a la base de datos
 */
public class DataAcessObject {
	
	/**
	 * Permite registrar un Cliente nuevo
	 * @param persona
	 *//*
	public void registrarCliente(Usuario persona) {
		Conexion conex= new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO Cliente VALUES ('"+persona.getIdCliente()+"', '"
					+ persona.getNombreCliente()+"', '"+persona.getApellidoCliente()+"')");
		   //JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Informaci�n",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
		   	conex.desconectar();
	  	} catch (SQLException e) {
	  		System.out.println(e.getMessage());
		   //JOptionPane.showMessageDialog(null, "No se Registro el cliente");
		}
	}
	
	/**
	 * Permite consultar el Cliente asociado al documento enviado
	 * como parametro 
	 * @param documento 
	 * @return
	 *//*
	public static ArrayList<Usuario> consultarPersona(int documento) {
		ArrayList<Usuario> miCliente = new ArrayList< Usuario>();
		Conexion conex= new Conexion();
	    
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM Cliente where idCliente = ? ");
			consulta.setInt(1, documento);
			ResultSet res = consulta.executeQuery();
			if(res.next()){
				Usuario persona= new Usuario();
				persona.setIdCliente(Integer.parseInt(res.getString("idCliente")));
				persona.setNombreCliente(res.getString("Nombre"));
				persona.setApellidoCliente(res.getString("Apellido"));
		 
				miCliente.add(persona);
			}
	       	res.close();
	        consulta.close();
	        conex.desconectar();
	        
	  } catch (Exception e) {
		  //JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n"+e);
	  }
	  return miCliente;
	}
	
	/**
	 * Permite consultar la lista de Clientes
	 * @return
	 *//*
	public ArrayList<Usuario> listarPersonas() {
		ArrayList<Usuario> miCliente = new ArrayList<Usuario>();
		Conexion conex= new Conexion();
	    
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM Cliente");
			ResultSet res = consulta.executeQuery();
		while(res.next()){
			Usuario persona= new Usuario();
			persona.setId(Integer.parseInt(res.getString("idCliente")));
			persona.setNombre(res.getString("nombre"));
			persona.setApellid(res.getString("apellido"));
	  
			miCliente.add(persona);
		}
	      	res.close();
	  		consulta.close();
	  		conex.desconectar();
	  	} catch (Exception e) {
	  		//JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n"+e);
	  		}	return miCliente;
		}
	*/
	/**
	 * Consulta la base de datos para contrastar usuario y contrase�a
	 * @param documento 
	 * @return el usuario y la contrase�a dada le brindan acceso al usuario
	 */
	public static boolean esUsuarioAutorizado(String usuario, String clave) {
		/*
		 * IMPORTANTE: este m�todo consulta a una tabla en la base de datos formada
		 * por una instrucci�n sql similar a esta:
		 * 		CREATE TABLE `usuarios` (
					`ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
				    `CEDULA` int(10) NOT NULL,
					`NOMBRE` varchar(64) NOT NULL,
				    `CORREO` varchar(64) NOT NULL,
				    `USUARIO` varchar(32) NOT NULL,
					`CONTRASENIA` varchar(256) NOT NULL,
					PRIMARY KEY (`ID`),
					KEY `USUARIO` (`USUARIO`)
				)
		 * Si la base de datos es distinta, por favor cambie las instrucciones SQL de esta
		 * clase (linea 116) o la base de datos que contiene a los usuarios
		 * */
		Conexion conex= new Conexion();
		boolean estaAutorizado = false;
		
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios where USUARIO = ? AND CONTRASENIA = ?");
			consulta.setString(1, usuario);
			consulta.setString(2, clave);
			
			ResultSet rs = consulta.executeQuery();
			
			// si existe al menos 1 Resul Set, .next() ser� true
			if(rs.next()) estaAutorizado = true;
			
	       	rs.close();
	        consulta.close();
	        conex.desconectar();
	        
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Error\n"+e);
	  	}
		return estaAutorizado;
	}
	/**
	 * Inserta los datos de un nuevo usuario
	 * @param usuario Objeto Usuario con los datos a ingresar
	 * @return �La operaci�n ha sido o no exitosa?
	 */
	public static boolean insertar(Usuario usuario) {
		/*
		 * Existen restricciones importantes a tomar en cuenta:
		 * * Limitaciones de caracteres (Base de datos):
		 * 	 - 9.999.999.999 Usuarios posibles
		 * 	 - 10 char  -> C�dula
		 * 	 - 64 char  -> Nombre
		 * 	 - 64 char  -> Correo
		 * 	 - 32 char  -> Usuario
		 * 	 - 256 char -> Contrase�a
		 * * Datos que NO pueden repetirse:
		 *   - C�dula
		 *   - Usuario 		
		 * * C�dula s�lo puede ser un n�mero
		 */
		Conexion conex = new Conexion();
		boolean infoInsertadaCorrectamente = true;
		
		try {
			PreparedStatement request = conex.getConnection().prepareStatement("INSERT INTO usuarios (CEDULA, NOMBRE, CORREO, USUARIO, CONTRASENIA) VALUES (?, ?, ?, ?, ?)");
			request.setLong(1, usuario.getCedula());
			request.setString(2, usuario.getNombre());
			request.setString(3, usuario.getCorreo());
			request.setString(4, usuario.getUsuario());
			request.setString(5, usuario.getContrasena());
			
			request.executeUpdate();
			
	       	request.close();
	        conex.desconectar();
	        
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error\n"+e);
			infoInsertadaCorrectamente = false;
	  	}
		
		return infoInsertadaCorrectamente;
	}
	/**
	 * Consulta por la existencia de un usuario, retornando su ID
	 * @param cedula
	 * @return n�mero correspondiente al ID del usuario requerido, o 0 si NO existe
	 */
	private static int retornarIdUsuario(long cedula) {
		Conexion conex = new Conexion();
		int id = 0;
		
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios where CEDULA = ?");
			consulta.setLong(1, cedula);
			
			ResultSet rs = consulta.executeQuery();
			
			// si existe al menos 1 Resul Set, .next() retornar� true
			if(rs.next()) id = rs.getInt("ID");
			
	       	rs.close();
	        consulta.close();
	        conex.desconectar();
	        
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Error\n"+e);
	  	}
		return id;
	}
	/**
	 * Elimina al usuario dado
	 * @param cedula
	 * @return �La operaci�n ha sido o no exitosa?
	 */
	public static boolean eliminar(Long cedula) {
		Conexion conex = new Conexion();
		int id = retornarIdUsuario(cedula);
		boolean usuarioEliminado = false;
		
		if (id != 0) {
			try {
				PreparedStatement request = conex.getConnection().prepareStatement("DELETE FROM usuarios where ID = ?");
				request.setInt(1, id);
				request.executeUpdate();
				usuarioEliminado = true;
				
				request.close();
		        conex.desconectar();
		        
			} catch (Exception e) {
				//JOptionPane.showMessageDialog(null, "Error\n"+e);
		  	}
		}
		return usuarioEliminado;
	}
	/** 
	 * Reemplaza los datos del usuario
	 * @param cedula
	 * @param datosUsuario Array de nuevos datos para reemplazar a los viejos
	 * @return �La operaci�n ha sido o no exitosa?
	 */
	public static boolean actualizar(Long cedula, String[] datosUsuario) {
		Conexion conex = new Conexion();
		int id = retornarIdUsuario(cedula);
		boolean usuarioActualizado = false;
		
		if (id != 0) {
			try {
				PreparedStatement request = conex.getConnection().prepareStatement("UPDATE usuarios SET NOMBRE = ?, CORREO = ?, USUARIO = ?, CONTRASENIA = ? WHERE ID = ?");
				// 4 espacios; suposici�n de que todos los datos son nuevos
				for (int i = 1; i < 4; i++) {
					if (datosUsuario[i] != null) request.setString(i, datosUsuario[i]);
				}
				request.setInt(5, id);
				
				request.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuario modificado con la siguiente informaci�n:\n" + consultarUsuario(cedula).toString());
				usuarioActualizado = true;
				
				request.close();
		        conex.desconectar();
		        
			} catch (Exception e) {
				//JOptionPane.showMessageDialog(null, "Error\n"+e);
		  	}
		}
		return usuarioActualizado;
	}
	/**
	 * Recupera los datos del usuario 
	 * @param cedula
	 * @return Objeto Usuario con los datos requeridos, null si no existe el usuario 
	 */
	public static Usuario consultarUsuario(long cedula) {
		Conexion conex = new Conexion();
		int id = retornarIdUsuario(cedula);
		Usuario usuario = new Usuario();
		
		if (id != 0) {
			try {
				PreparedStatement request = conex.getConnection().prepareStatement("SELECT * FROM usuarios where ID = ?");
				request.setInt(1, id);
				
				ResultSet rs = request.executeQuery(); rs.next();
				usuario.setId(id);
				usuario.setCedula(cedula);
				usuario.setNombre(rs.getString("NOMBRE"));
				usuario.setCorreo(rs.getString("CORREO"));
				usuario.setUsuario(rs.getString("USUARIO"));
				usuario.setContrasena(rs.getString("CONTRASENIA"));
				
				rs.close();
				request.close();
		        conex.desconectar();
		        
			} catch (Exception e) {
				//JOptionPane.showMessageDialog(null, "Error\n"+e);
		  	}
		} else {
			usuario = null;
		}
		return usuario;
	}
}