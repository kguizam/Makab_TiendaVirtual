package com.BO.Makab;
import java.util.ArrayList;
import java.util.Iterator;
import com.DAO.Makab.ClienteDAO; 
import com.DTO.Makab.ClienteVO;

public class Listar {
	static ArrayList<ClienteVO> clientes;

	public static String[] retornar() {
		ClienteDAO c = new ClienteDAO();
		clientes = c.listarPersonas();
		
		Iterator<ClienteVO> iter = clientes.iterator();
		String[] nombreClientes = new String[clientes.size()];
		
		int i = 0;
		while (iter.hasNext()) {
			nombreClientes[i] = iter.next().getNombreCliente();
			i++;
		}
		return nombreClientes;
	}
}
