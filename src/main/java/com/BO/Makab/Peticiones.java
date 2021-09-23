package com.BO.Makab;
import java.util.ArrayList;
// import java.util.Iterator;

import javax.swing.JOptionPane;
import com.DAO.Makab.DataAcessObject; 
import com.DTO.Makab.Usuario;

public class Peticiones {
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
}