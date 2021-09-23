package com.BO.Makab;
import java.util.ArrayList;
// import java.util.Iterator;

import javax.swing.JOptionPane;
import com.DAO.Makab.DataAcessObject; 
import com.DTO.Makab.Usuario;

public class Peticion {
	static ArrayList<Usuario> clientes;
	/*
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
	public static void pruebaConsulta() {
		Usuario user = DataAcessObject.consultarUsuario((long) 1001275983);
		JOptionPane.showMessageDialog(null, "Usuario consultado correctamente\n" + user.toString());
	}
	public static void insertarDatos() {
		String[] s = {
				"Andrea Torres", "TorresAndrea@gmail.com", "Andrea", "contraseña" 
		};
		DataAcessObject.actualizar((long) 66666, s);
	}
	/**
	 * Señala la viabilidad de la petición. ESTA FUNCIÓN DEBERÍA EJECUTARSE
	 * EN EL FRONTEND
	 * @param opcion String de la opción a consular
	 * @param formulario
	 * @deprecated
	 * @return
	 */
	public static boolean esPosibleLaConsulta(String opcion, String[] formulario) {
		// Si el campo cédula está vacío, ninguna consulta es posible
		if (formulario[0] == "") return false;
		
		int camposVacios = 0;
		for (String campo : formulario) if (campo == "") camposVacios += 1;
		
		int informacionDisponible = 5 - camposVacios;
		
		// Es inviable crear un nuevo usuario si no está toda la información
		if (opcion.equals("crear") && informacionDisponible != 5) return false;
		
		// No tiene sentido actualizar al usuario si sólo se brindó un único dato 
		if (opcion.equals("actualizar") && informacionDisponible == 1) return false;
		
		// Si se pasaron todas las pruebas anteriores, la operación 
		// es posible sin, importar cuál sea esta
		return true;
	}
	public static String intentar(String opcion, String[] formulario) {
		
		long cedula = Long.parseLong(formulario[0]);
		DataAcessObject DAO = new DataAcessObject();
		String respuesta = null;
		
		// OPCION = CONSULTAR
		if (opcion.equals("consultar")) {
			Usuario newUser = DAO.consultarUsuario(cedula);
			if (newUser != null) respuesta = newUser.toString();
			
		// OPCION = CREAR
		} else if (opcion.equals("crear")) {
			Usuario newUser = new Usuario(
					0 , cedula, formulario[1], formulario[2], 
					formulario[3], formulario[4]);
			
			boolean resultado = DAO.insertar(newUser);
			if (resultado) {
				respuesta = "Usuario " + newUser.getUsuario() + " creado exitosamente";
			}
		
		// OPCION = ACTUALIZAR
		} else if (opcion.equals("actualizar")) {
			Usuario usuarioSinModificar = DAO.consultarUsuario(cedula);
			String datos = "";
			
			if (usuarioSinModificar != null) {
				String[] datosAntiguos = usuarioSinModificar.getArrayDatos();
				
				for (int i = 1; i < 5; i++) {
					if (formulario[i] == "") formulario[i] = datosAntiguos[i];
					datos += formulario[i] + " ";
				}
				
				boolean resultado = DAO.actualizar(cedula, formulario);
				if (resultado) {
					respuesta = "Usuario actualizado exitosamente: " + datos;
				}
			}
			
		// OPCION = BORRAR 
		} else if (opcion.equals("borrar")) {
			boolean resultado = DAO.eliminar(cedula);
			if (resultado) {
				respuesta = "Usuario borrado exitosamente";
			}
		}
		return respuesta;
	}
}