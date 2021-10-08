package com.DAO.Makab;
import java.sql.*;

/**
 * Clase que permite conectar con la base de datos
 */
public class Conexion {
	/**Parametros de conexion*/
	static String bd = "tienda_66_2";
	static String url = "jdbc:mysql://localhost/"+bd;
	static String login = "root";
	static String password = "";
	Connection connection = null;

	/** Constructor de DbConnection */
	public Conexion() {
		try{
			//obtenemos el driver de para mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			//obtenemos la conexión
			connection = DriverManager.getConnection(url,login,password);

			if (connection != null){
				System.out.println("La conexión a base de datos " + bd + " ha sido un éxito!");
			}
		}
		catch(SQLException e){
			System.out.println("EXCEPCIÓN SQL!!! : " + e);
		} catch (ClassNotFoundException e){
			System.out.println("CLASS NOT FOUND!!! :" + e);
		} catch (Exception e){
			System.out.println("EXCEPCIÓN GENÉRICA!!!" + e);
		}
	}
	/**Permite retornar la conexión*/
	public Connection getConnection(){
		return connection;
	}

	public void desconectar(){
		connection = null;
	}
}