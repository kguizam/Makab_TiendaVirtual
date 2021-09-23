package com.BO.Makab;
import com.DAO.Makab.DataAcessObject;
// import java.util.ArrayList;
// import java.util.Iterator;
// import javax.swing.JOptionPane;
import com.DTO.Makab.Usuario;

public class Peticion {
	/**
	 * Se�ala la viabilidad de la petici�n.
	 * 
	 * @param opcion String de la opci�n a consular (consultar, crear, actualizar o borrar).
	 * @param datosFormulario Array con los datos brindados por el cliente.
	 * @deprecated ESTE SERVICIO SE DEBER� EJECUTAR EN EL FRONTEND (JAVASCRIPT)
	 * @return �Los datos dados se ajustan a los requerimientos?
	 */
	public static boolean esPosibleLaConsulta(String opcion, String[] datosFormulario) {
		// Si el campo c�dula est� vac�o, ninguna petici�n es posible
		if (datosFormulario[0] == "") return false;
		
		int camposVacios = 0;
		for (String campo : datosFormulario) if (campo == "") camposVacios += 1;
		
		int informacionDisponible = 5 - camposVacios;
		
		// Es inviable crear un nuevo usuario si no est� toda la informaci�n
		if (opcion.equals("crear") && informacionDisponible != 5) return false;
		
		// No tiene sentido actualizar al usuario si s�lo se brind� un �nico dato 
		if (opcion.equals("actualizar") && informacionDisponible == 1) return false;
		
		/*
		 *  Si se pasaron todas las pruebas anteriores, la operaci�n
		 *  es posible, sin importar cu�l sea. 
		 */ 
		return true;
	}
	
	/**
	 * Intenta realizar la funci�n DAO solicitada por el ciberusuario
	 * @param opcion String de la opci�n a intentar (consultar, crear, actualizar o borrar).
	 * @param datosFormulario Array con los datos brindados por el cliente.
	 * @return Mensaje sobre la ejecuci�n de la petici�n; null si hubo un error
	 */
	public static String intentar(String opcion, String[] datosFormulario) {
		
		long cedula = Long.parseLong(datosFormulario[0]);
		DataAcessObject DAO = new DataAcessObject();
		String respuesta = null;
		
		// OPCION = CONSULTAR
		if (opcion.equals("consultar")) {
			Usuario dbUser = DAO.consultarUsuario(cedula); 
			/* Si no existe un usuario con la c�dula indicada, devuelve null... 
			 * Esa es la raz�n de tantos if que hay por ah� :) */
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
		/* Se podr�a modularizar mejor esta funci�n. Pero si 
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