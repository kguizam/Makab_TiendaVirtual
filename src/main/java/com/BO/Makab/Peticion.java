package com.BO.Makab;
import com.DAO.Makab.DataAcessObject;
// import java.util.ArrayList;
// import java.util.Iterator;
// import javax.swing.JOptionPane;
import com.DTO.Makab.Usuario;

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
	public static String intentar(String opcion, String[] datosFormulario) {
		
		long cedula = Long.parseLong(datosFormulario[0]);
		DataAcessObject DAO = new DataAcessObject();
		String respuesta = null;
		
		// OPCION = CONSULTAR
		if (opcion.equals("consultar")) {
			Usuario dbUser = DAO.consultarUsuario(cedula); 
			/* Si no existe un usuario con la cédula indicada, devuelve null... 
			 * Esa es la razón de tantos if que hay por ahí :) */
			if (dbUser != null) respuesta = dbUser.toString(); 
			
		// OPCION = CREAR
		} else if (opcion.equals("crear")) {
			Usuario newUser = new Usuario(
					0 , cedula, datosFormulario[1], datosFormulario[2], 
					datosFormulario[3], datosFormulario[4]);
			
			boolean resultadoPeticion = DAO.insertar(newUser);
			if (resultadoPeticion) {
				respuesta = "Usuario " + newUser.getUsuario() + " creado exitosamente";
			}
		
		// OPCION = ACTUALIZAR
		} else if (opcion.equals("actualizar")) {
			Usuario dbUser = DAO.consultarUsuario(cedula);
			String datosNuevos = "";
			
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
	/*
	static ArrayList<Usuario> clientes;
	
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