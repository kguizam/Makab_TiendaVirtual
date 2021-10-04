package com.BO.Makab;
import com.DAO.Makab.DataAcessObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import com.csvreader.CsvReader;
// import javax.swing.JOptionPane;
import com.DTO.Makab.*;

public class Peticion {
	/**
	 * Señala la viabilidad de la petición.
	 * 
	 * @param opcion String de la opción a consular (consultar, crear, actualizar o borrar).
	 * @param datosFormulario Array con los datos brindados por el cliente.
	 * @deprecated ESTE SERVICIO SE DEBERÁ EJECUTAR EN EL FRONTEND (JAVASCRIPT)
	 * @return ¿Los datos dados se ajustan a los requerimientos?
	 */
	public static boolean esPosibleLaConsulta(String opcion, String[] datosFormulario) {
		// Si el campo cédula está vacío, ninguna petición es posible
		if (datosFormulario[0] == "") return false;
		
		int camposVacios = 0;
		for (String campo : datosFormulario) if (campo == "") camposVacios += 1;
		
		int informacionDisponible = 5 - camposVacios;
		
		// Es inviable crear un nuevo usuario si no está toda la información
		if (opcion.equals("crear") && informacionDisponible != 5) return false;
		
		// No tiene sentido actualizar al usuario si sólo se brindó un único dato 
		if (opcion.equals("actualizar") && informacionDisponible == 1) return false;
		
		/*
		 *  Si se pasaron todas las pruebas anteriores, la operación
		 *  es posible, sin importar cuál sea. 
		 */ 
		return true;
	}
	
	/**
	 * Intenta realizar la función DAO solicitada por el ciberusuario
	 * @param opcion String de la opción a intentar (consultar, crear, actualizar o borrar).
	 * @param datosFormulario Array con los datos brindados por el cliente.
	 * @return Mensaje sobre la ejecución de la petición; null si hubo un error
	 */
	public static String intentar(int modo, String opcion, String[] datosFormulario) {
		
		long cedula = Long.parseLong(datosFormulario[0]);
		DataAcessObject DAO = new DataAcessObject(modo);
		String respuesta = null;
		
		// OPCION = CONSULTAR
		if (opcion.equals("consultar")) {
			switch (modo) {
				case 1:
					Usuario dbUser = DAO.consultarUsuario(cedula); 
					/* Si no existe un usuario con la cédula indicada, devuelve null... 
					 * Esa es la razón de tantos if que hay por ahí :) */
					if (dbUser != null) respuesta = dbUser.toString() + " u";
					System.out.println("Consultar User");
					break;
				case 2:
					Cliente dbCliente = DAO.consultarCliente(cedula); 
					if (dbCliente != null) respuesta = dbCliente.toString() + " C";
					System.out.println("Consultar Cliente");
					break;
				case 3: 
					Proveedor dbProveedor = DAO.consultarProveedor(cedula); 
					if (dbProveedor != null) respuesta = dbProveedor.toString() + " p";
					System.out.println("Consultar Proveedor");
					break;
			}
		// OPCION = CREAR
		} else if (opcion.equals("crear")) {
			boolean resultadoPeticion;
			switch (modo) {
				case 1:
					Usuario newUser = new Usuario(
							0 , cedula, datosFormulario[1], datosFormulario[2], 
							datosFormulario[3], datosFormulario[4]);
					
					resultadoPeticion = DAO.insertar(newUser);
					if (resultadoPeticion) {
						respuesta = "Usuario " + newUser.getUsuario() + " creado exitosamente";
					} break;
				case 2:
					Cliente newClient = new Cliente(
							0 , cedula, datosFormulario[1], datosFormulario[2], 
							datosFormulario[3], datosFormulario[4]);
					
					resultadoPeticion = DAO.insertar(newClient);
					if (resultadoPeticion) {
						respuesta = "Cliente " + newClient.getNombre() + " creado exitosamente";
					} break;
				case 3:
					Proveedor newProveedor = new Proveedor(
							0 , cedula, datosFormulario[1], datosFormulario[2], 
							datosFormulario[3], datosFormulario[4]);
					
					resultadoPeticion = DAO.insertar(newProveedor);
					if (resultadoPeticion) {
						respuesta = "Proveedor " + newProveedor.getNombre() + " creado exitosamente";
					} break;
			}
		// OPCION = ACTUALIZAR
		} else if (opcion.equals("actualizar")) {
			String datosNuevos = "";
			switch (modo) {
				case 1:
					Usuario dbUser = DAO.consultarUsuario(cedula);
					
					if (dbUser != null) {
						String[] datosAntiguos = dbUser.getArrayDatos();
						
						for (int i = 1; i < 5; i++) {
							if (datosFormulario[i] == "") datosFormulario[i] = datosAntiguos[i];
							datosNuevos += datosFormulario[i] + " ";
						}
						
						boolean resultadoPeticion = DAO.actualizar(cedula, datosFormulario);
						if (resultadoPeticion) {
							respuesta = "Usuario actualizado exitosamente: " + datosNuevos;
						}
					} break;
				case 2:
					Cliente dbClient = DAO.consultarCliente(cedula);
					
					if (dbClient != null) {
						String[] datosAntiguos = dbClient.getArrayDatos();
						
						for (int i = 1; i < 5; i++) {
							if (datosFormulario[i] == "") datosFormulario[i] = datosAntiguos[i];
							datosNuevos += datosFormulario[i] + " ";
						}
						
						boolean resultadoPeticion = DAO.actualizar(cedula, datosFormulario);
						if (resultadoPeticion) {
							respuesta = "Cliente actualizado exitosamente: " + datosNuevos;
						}
					} break;
				case 3:
					Proveedor dbProveedor = DAO.consultarProveedor(cedula);
					
					if (dbProveedor != null) {
						String[] datosAntiguos = dbProveedor.getArrayDatos();
						
						for (int i = 1; i < 5; i++) {
							if (datosFormulario[i] == "") datosFormulario[i] = datosAntiguos[i];
							datosNuevos += datosFormulario[i] + " ";
						}
						
						boolean resultadoPeticion = DAO.actualizar(cedula, datosFormulario);
						if (resultadoPeticion) {
							respuesta = "Proveedor actualizado exitosamente: " + datosNuevos;
						}
					} break;					
			}
		// OPCION = BORRAR 
		} else if (opcion.equals("borrar")) {
			boolean resultadoPeticion = DAO.eliminar(cedula);
			if (resultadoPeticion) {
				respuesta = "Usuario borrado exitosamente";
			}
		}
		/* Se podría modularizar mejor esta función. Pero si 
		 * funciona y es comprensible, no es necesario ;) */
		return respuesta;
	}
	
	public static void insertarDatos() {
		List<Producto> productos;
		productos = new ArrayList<Producto>();
		productos.add(new Producto(1, "Carne", 1, 5000, 200, 5500));
		productos.add(new Producto(2, "Pescado", 1, 2000, 100, 4000));
		productos.add(new Producto(3, "Huevos", 1, 1800, 0, 1800));
		productos.add(new Producto(4, "Leche", 2, 2500, 0, 3000));
		productos.add(new Producto(5, "Queso", 2, 5000, 100, 5600));
		DataAcessObject.insertarProductos(productos);
	}
	
	public static String insertarCSV(String file) {
		List<Producto> productos = new ArrayList<Producto>(); 
		String message;
		
		try {
			CsvReader lector = new CsvReader("C:\\Users\\kevin\\3D Objects\\Makab_TiendaVirtual\\" + file);
			lector.readHeaders();
			int leidos = 0; int i = 0;
			System.out.println("\nSe intentarán leer el archivo \"" + file  + "\":");
			
			while(lector.readRecord()) {
				try {
					Long codigo = Long.parseLong(lector.get(0));
					String nombre = lector.get(1);
					Long nit = Long.parseLong(lector.get(2));
					double precioCompra = Double.parseDouble(lector.get(3));
					double iva = Double.parseDouble(lector.get(4));
					double precioVenta = Double.parseDouble(lector.get(5));
					
					Producto nuevoProducto = new Producto(codigo, nombre, nit, precioCompra, iva, precioVenta);
					productos.add(nuevoProducto);
					
					System.out.println("\t{" + ++i + "} Producto " + nuevoProducto.getNombre() + " leído");
					leidos++;
				} catch (Exception e) {
					System.out.println("\t{" + ++i + "} Error: " + e);
				}
			}
			System.out.println("\nSe leyeron " + leidos +" de " + i + " registros.");
			List<Producto> registrosInvalidos = DataAcessObject.insertarProductos(productos);
			
			if (registrosInvalidos.size() == 0 && leidos > 0) {
				message = leidos + " de " + i + " registros leídos y guardados exitosamente.";
			} else if (leidos == 0) {
				message = "No se leyó ningún registro. Por favor, revise que su archivo tenga el formato apropiado.";
			} else {
				message = "\n " + leidos +" de " + i + " registros guardados en la base de datos. Revise los registros que se guardaron.";
			}
			
			lector.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR_EL ARCHIVO NO EXISTE (" + e + ")");
			message = "El archivo NO existe.";
		} catch (Exception e) {
			if (e.toString().contains(";")) {
				System.out.println("Por favor, use un archivo separado por comas (el suyo está separado por punto y coma): " + e);
				message ="Por favor, use un archivo separado por comas (el suyo está separado por punto y coma).";
			} else {
				System.out.println("Error en la lectura del archivo CSV:\n" + e);
				message = "Archivo inválido. Los campos no corresponden.";
			}
			
		}
		return message;
	}
	/*
	static ArrayList<Usuario> clientes
	public static String[] retornar() {
		DataAcessObject c = new DataAcessObject();
		usuarios = c.listarPersonas();
		
		Iterator<Usuario> iter = clientes.iterator();
		String[] nombreClientes = new String[clientes.size()];
		
		int i = 0;
		while (iter.hasNext()) {
			nombreClientes[i] = iter.next().getNombreCliente();
			i++;
		}
		return nombreClientes;
	}*/
}