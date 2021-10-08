package com.DAO.Makab;
import java.sql.*;
import com.DTO.Makab.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que permite el acceso a la base de datos, aka 'DAO'
 */
public class DataAcessObject {
	int modo;
	private String tabla;
	private String identificador = "CEDULA";
	private String propiedades = "CORREO = ?, USUARIO = ?, CONTRASENIA = ?";
	//private String valores = "(" + identificador + ", NOMBRE, CORREO, USUARIO, CONTRASENIA)";
	private String valores = "(" + identificador + ", NOMBRE, ";
	
	public DataAcessObject(int modo) {
		super();
		this.modo = modo;
		switch (modo) {
			case 1:
				tabla = "usuarios";
				valores += "CORREO, USUARIO, CONTRASENIA)";
				break;
			case 2:
				tabla = "clientes";
				propiedades = "DIRECCION = ?, TELEFONO = ?, CORREO = ?";
				valores += "DIRECCION, TELEFONO, CORREO)";
				break;
			case 3:
				tabla = "proveedores";
				propiedades = "DIRECCION = ?, TELEFONO = ?, CIUDAD = ?";
				identificador = "NIT";
				valores = "(" + identificador + ", NOMBRE, DIRECCION, TELEFONO, CIUDAD)";
				break;
		}
	}
	
	/**
	 * Consulta por la existencia de un usuario, retornando su ID
	 * @param cedula
	 * @return número correspondiente al ID del usuario requerido, o 0 si NO existe
	 */
	private int retornarId(long cedula) {
		int id = 0;
		Conexion conex = new Conexion();
		String statement = "SELECT * FROM " + tabla + " where " + identificador + " = ?";
		
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement(statement);
			consulta.setLong(1, cedula);
			
			ResultSet rs = consulta.executeQuery();
			
			// si existe al menos 1 Resul Set, .next() retornará true
			if(rs.next()) id = rs.getInt("ID");
			
	       	rs.close();
	        consulta.close();
	        conex.desconectar();
	        
		} catch (Exception e) {
			System.out.println("Error\n"+e);
	  	}
		return id;
	}

	/**
	 * Inserta los datos de un nuevo usuario
	 * @param usuario Objeto Usuario con los datos a ingresar
	 * @return ¿La operación ha sido o no exitosa?
	 */
	public boolean insertar(Persona persona) {
		/*
		 * Existen restricciones importantes a tomar en cuenta:
		 * * Limitaciones de caracteres (Base de datos):
		 * 	 - 9.999.999.999 Usuarios posibles
		 * 	 - 10 char  -> Cédula
		 * 	 - 64 char  -> Nombre
		 * 	 - 64 char  -> Correo
		 * 	 - 32 char  -> Usuario
		 * 	 - 256 char -> Contraseña
		 * * Datos que NO pueden repetirse:
		 *   - Cédula
		 *   - Usuario 		
		 * * Cédula sólo puede ser un número
		 */
		Conexion conex = new Conexion();
		boolean infoInsertadaCorrectamente = true;
		
		try {
			PreparedStatement request = conex.getConnection().prepareStatement("INSERT INTO " + tabla + " " + valores + " VALUES (?, ?, ?, ?, ?)");
			request.setLong(1, persona.getIdentificador());
			request.setString(2, persona.getNombre());
			request.setString(3, persona.getCampo1());
			request.setString(4, persona.getCampo2());
			request.setString(5, persona.getCampo3());
			
			request.executeUpdate();
			
	       	request.close();
	        conex.desconectar();
	        
		} catch (Exception e) {
			System.out.println("Error\n"+e);
			infoInsertadaCorrectamente = false;
	  	}
		return infoInsertadaCorrectamente;
	}
	
	/**
	 * Elimina al usuario dado
	 * @param cedula
	 * @return ¿La operación ha sido o no exitosa?
	 */
	public boolean eliminar(Long cedula) {
		Conexion conex = new Conexion();
		int id = retornarId(cedula);
		boolean usuarioEliminado = false;
		
		String statement = "DELETE FROM " + tabla + " where ID = ?";
		
		if (id != 0) {
			try {
				PreparedStatement request = conex.getConnection().prepareStatement(statement);
				request.setInt(1, id);
				request.executeUpdate();
				usuarioEliminado = true;
				
				request.close();
		        conex.desconectar();
		        
			} catch (Exception e) {
				System.out.println("Error\n"+e);
		  	}
		}
		return usuarioEliminado;
	}
	
	/** 
	 * Reemplaza los datos del usuario
	 * @param cedula
	 * @param datosUsuario Array de nuevos datos para reemplazar a los viejos
	 * @return ¿La operación ha sido o no exitosa?
	 */
	public boolean actualizar(Long cedula, String[] datosUsuario) {
		Conexion conex = new Conexion();
		int id = retornarId(cedula);
		boolean usuarioActualizado = false;	
		String statement = "UPDATE " + tabla + " SET NOMBRE = ?, " + propiedades + " WHERE ID = ?";
		
		if (id != 0) {
			try {
				PreparedStatement request = conex.getConnection().prepareStatement(statement);
				for (int i = 1; i < 5; i++) request.setString(i, datosUsuario[i]);
				request.setInt(5, id);
				
				request.executeUpdate();
				usuarioActualizado = true;
				
				request.close();
		        conex.desconectar();
		        
			} catch (Exception e) {
				System.out.println("Error\n"+e);
		  	}
		}
		return usuarioActualizado;
	}
	
	/**
	 * Recupera los datos del usuario mediante un objeto usuario 
	 * @param cedula
	 * @return Objeto Usuario con los datos requeridos, null si no existe el usuario 
	 */
	public Usuario consultarUsuario(long cedula) {
		Conexion conex = new Conexion();
		int id = retornarId(cedula);
		Usuario usuario = new Usuario();
		
		if (id != 0) {
			try {
				PreparedStatement request = conex.getConnection().prepareStatement("SELECT * FROM usuarios where ID = ?");
				request.setInt(1, id);
				
				ResultSet rs = request.executeQuery(); rs.next();
				usuario.setNumberID(id);
				usuario.setIdentificador(cedula);
				usuario.setNombre(rs.getString("NOMBRE"));
				usuario.setCampo1(rs.getString("CORREO"));
				usuario.setCampo2(rs.getString("USUARIO"));
				usuario.setCampo3(rs.getString("CONTRASENIA"));
				
				rs.close();
				request.close();
		        conex.desconectar();
		        
			} catch (Exception e) {
				System.out.println("Error\n"+e);
		  	}
		} else {
			usuario = null;
		}
		return usuario;
	}
	
	/**
	 * Recupera los datos del usuario mediante un objeto usuario 
	 * @param cedula
	 * @return Objeto Usuario con los datos requeridos, null si no existe el usuario 
	 */
	public Cliente consultarCliente(long cedula) {
		Conexion conex = new Conexion();
		int id = retornarId(cedula);
		Cliente cliente = new Cliente();
		
		if (id != 0) {
			try {
				PreparedStatement request = conex.getConnection().prepareStatement("SELECT * FROM clientes where ID = ?");
				request.setInt(1, id);
				
				ResultSet rs = request.executeQuery(); rs.next();
				cliente.setNumberID(id);
				cliente.setIdentificador(cedula);
				cliente.setNombre(rs.getString("NOMBRE"));
				cliente.setCampo1(rs.getString("DIRECCION"));
				cliente.setCampo2(rs.getString("TELEFONO"));
				cliente.setCampo3(rs.getString("CORREO"));
				
				rs.close();
				request.close();
		        conex.desconectar();
		        
			} catch (Exception e) {
				System.out.println("Error\n"+e);
		  	}
		} else {
			cliente = null;
		}
		return cliente;
	}
	
	/**
	 * Recupera los datos del usuario mediante un objeto usuario 
	 * @param cedula
	 * @return Objeto Usuario con los datos requeridos, null si no existe el usuario 
	 */
	public Proveedor consultarProveedor(long cedula) {
		Conexion conex = new Conexion();
		int id = retornarId(cedula);
		Proveedor proveedor = new Proveedor();
		
		if (id != 0) {
			try {
				PreparedStatement request = conex.getConnection().prepareStatement("SELECT * FROM proveedores where ID = ?");
				request.setInt(1, id);
				
				ResultSet rs = request.executeQuery(); rs.next();
				proveedor.setNumberID(id);
				proveedor.setIdentificador(cedula);
				proveedor.setNombre(rs.getString("NOMBRE"));
				proveedor.setCampo1(rs.getString("DIRECCION"));
				proveedor.setCampo2(rs.getString("TELEFONO"));
				proveedor.setCampo3(rs.getString("CIUDAD"));
				
				rs.close();
				request.close();
		        conex.desconectar();
		        
			} catch (Exception e) {
				System.out.println("Error CONSULTA PROVEEDOR\n"+e);
		  	}
		} else {
			proveedor = null;
		}
		return proveedor;
	}
	
	/**
	 * Consulta la base de datos para contrastar usuario y contraseña
	 * @param usuario
	 * @param clave
	 * @return ¿Deberíamos brindarle acceso al usuario?
	 */
	public static boolean esUsuarioAutorizado(String usuario, String clave) {
		/*
		 * IMPORTANTE: este método consulta a una tabla en la base de datos formada
		 * por una instrucción sql similar a esta:
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
			
			// si existe al menos 1 Resul Set, .next() será true
			if(rs.next()) estaAutorizado = true;
			
	       	rs.close();
	        consulta.close();
	        conex.desconectar();
	        
		} catch (Exception e) {
			System.out.println("Error\n"+e);
	  	}
		return estaAutorizado;
	}
	
	public static List<Producto> insertarProductos(List<Producto> productos) {
		List<Producto> registrosInvalidos = new ArrayList<>();
		
		Iterator<Producto> iProductos = productos.iterator();
		System.out.println("Se añadirán " + productos.size() + " registros a la base de datos.\n");
		int guardados = 0;
		int i = 0; Long nit;
		
		Conexion conex= new Conexion();
		
		try {
			PreparedStatement request = conex.getConnection().prepareStatement("INSERT INTO productos (CODIGO, NOMBRE, NIT, PRECIO_COMPRA, IVA_COMPRA, PRECIO_VENTA) VALUES(?, ?, ?, ?, ?, ?)");
			List<Long> listaNit = nitValidos();
			
			while(iProductos.hasNext()) {
				Producto nuevoProducto = iProductos.next();
				
				
				try {
					nit = nuevoProducto.getNitProveedor();
					if (listaNit.indexOf(nit) != -1) {
						request.setLong(1, nuevoProducto.getCodigo());
						request.setString(2, nuevoProducto.getNombre());
						request.setLong(3, nit);
						request.setDouble(4, nuevoProducto.getPrecioCompra());
						request.setDouble(5, nuevoProducto.getIva());
						request.setDouble(6, nuevoProducto.getPrecioVenta());
						
						request.executeUpdate();
						
						System.out.println("\t[" + ++i + "] Producto " + nuevoProducto.getNombre() + " con el código " + nuevoProducto.getCodigo() + " añadido");
						guardados++;  
						request.clearParameters();
						
					} else {
						System.out.println("\t["+ i +"] Error: NIT del proveedor NO válido");
						registrosInvalidos.add(nuevoProducto);
					}
				} catch (Exception e) {
					System.out.println("\t["+ i +"] Error: " + e);
					registrosInvalidos.add(nuevoProducto);
			  	}
			}
			System.out.println("\nSe guardaron " + guardados  +" de " + productos.size() + " registros.");
			
	        request.close();
	        conex.desconectar();
	        
		} catch (Exception e) {
			System.out.println("Excepción en la base de datos: " + e);
	  	}
		return registrosInvalidos;
	}
	
	private static List<Long> nitValidos() {
		List<Long> nitValidos = new ArrayList<>(); Long nit;
		Conexion conex = new Conexion();
		String statement = "SELECT NIT FROM proveedores";
		
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement(statement);
			ResultSet rs = consulta.executeQuery();
			
			while(rs.next()) {
				nit = rs.getLong("NIT");
				nitValidos.add(nit);
			}
			
	       	rs.close();
	        consulta.close();
	        conex.desconectar();
	        
		} catch (Exception e) {
			System.out.println("Error\n"+e);
	  	}
		return nitValidos;
 	}
	
	/* /**
	 * Permite registrar un Cliente nuevo
	 * @param persona
	 *//*
	public void registrarCliente(Usuario persona) {
		Conexion conex= new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO Cliente VALUES ('"+persona.getIdCliente()+"', '"
					+ persona.getNombreCliente()+"', '"+persona.getApellidoCliente()+"')");
		   System.out.println("Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
		   	conex.desconectar();
	  	} catch (SQLException e) {
	  		System.out.println(e.getMessage());
		   System.out.println("No se Registro el cliente");
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
		  System.out.println("no se pudo consultar la Persona\n"+e);
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
	  		System.out.println("no se pudo consultar la Persona\n"+e);
	  		}	return miCliente;
		}
	*/
}