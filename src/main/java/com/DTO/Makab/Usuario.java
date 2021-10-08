package com.DTO.Makab;
/**
 * Objeto para transpasar los datos entre métodos
 */
public class Usuario extends Persona {
	 private String correo;
	 private String usuario;
	 private String contrasena;
	 
	 public Usuario() {
	 }
	 
	public Usuario(int id, long cedula,String nombre, String correo, String usuario, String contrasena) {
		super(id, cedula, nombre);
		this.correo = correo;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	public String getCampo1() {
		return correo;
	}

	public void setCampo1(String correo) {
		this.correo = correo;
	}

	public String getCampo2() {
		return usuario;
	}

	public void setCampo2(String usuario) {
		this.usuario = usuario;
	}

	public String getCampo3() {
		return contrasena;
	}

	public void setCampo3(String contrasena) {
		this.contrasena = contrasena;
	}

	protected String[] getNombresCampos() {
		return new String[] {
			"NIT", "Nombre", "Correo", "Usuario", "Contraseña"
		};
	}
	
	public String[] getArrayCampos() {
		return new String[] {
			identificador + "", nombre, correo, usuario, contrasena
		};
	}
}