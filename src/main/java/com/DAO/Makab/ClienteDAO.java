package com.DAO.Makab;
import java.sql.*;
import com.DTO.Makab.*;
import java.util.ArrayList;
//import javax.swing.JOptionPane;

/**
 * Clase que permite el acceso a la base de datos
 */
public class ClienteDAO {
	
	/**
	 * Permite registrar un Cliente nuevo
	 * @param persona
	 */
	public void registrarCliente(ClienteVO persona) {
		Conexion conex= new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO Cliente VALUES ('"+persona.getIdCliente()+"', '"
					+ persona.getNombreCliente()+"', '"+persona.getApellidoCliente()+"')");
		   //JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
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
	 */
	public static ArrayList<ClienteVO> consultarPersona(int documento) {
		ArrayList<ClienteVO> miCliente = new ArrayList< ClienteVO>();
		Conexion conex= new Conexion();
	    
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM Cliente where idCliente = ? ");
			consulta.setInt(1, documento);
			ResultSet res = consulta.executeQuery();
			if(res.next()){
				ClienteVO persona= new ClienteVO();
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
	 */
	public ArrayList<ClienteVO> listarPersonas() {
		ArrayList<ClienteVO> miCliente = new ArrayList<ClienteVO>();
		Conexion conex= new Conexion();
	    
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM Cliente");
			ResultSet res = consulta.executeQuery();
		while(res.next()){
			ClienteVO persona= new ClienteVO();
			persona.setIdCliente(Integer.parseInt(res.getString("idCliente")));
			persona.setNombreCliente(res.getString("nombre"));
			persona.setApellidoCliente(res.getString("apellido"));
	  
			miCliente.add(persona);
		}
	      	res.close();
	  		consulta.close();
	  		conex.desconectar();
	  	} catch (Exception e) {
	  		//JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n"+e);
	  		}	return miCliente;
		}
	
	/**
	 * Consulta la base de datos para contrastar usuario y contraseña
	 * @param documento 
	 * @return
	 */
	public static boolean esUsuarioAutorizado(String usuario, String clave) {
		/*
		 * IMPORTANTE: este método consulta a una tabla en la base de datos formada
		 * por una instrucción sql similar a esta:
		 * 		CREATE TABLE `usuarios` (
				  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
				  `USUARIO` varchar(255) NOT NULL,
				  `CONTRASENIA` varchar(255) NOT NULL,
				  PRIMARY KEY (`ID`),
				  KEY `USUARIO` (`USUARIO`)
				)
		 * Si la base de datos es distinta, por favor cambie las instrucciones SQL de esta
		 * clase (linea 112) o la base de datos que contiene a los usuarios
		 * */
		Conexion conex= new Conexion();
		boolean autorizado = false;
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios where USUARIO = ? AND CONTRASENIA = ?");
			consulta.setString(1, usuario);
			consulta.setString(2, clave);
			
			ResultSet rs = consulta.executeQuery();
			
			if(rs.next()) autorizado = true;
			
	       	rs.close();
	        consulta.close();
	        conex.desconectar();
	        
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Datos incorrectos ú otro error\n"+e);
	  	}
		return autorizado;
	}

}