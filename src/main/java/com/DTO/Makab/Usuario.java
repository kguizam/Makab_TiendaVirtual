//git
package com.DTO.Makab;

public class Usuario {
	 private int id;
	 private long cedula;
	 private String nombre;
	 private String correo;
	 private String usuario;
	 private String contrasena;
	 
	 public Usuario() {
	 }
	 
	public Usuario(int id, long cedula,String nombre, String correo, String usuario, String contrasena) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.correo = correo;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", correo=" + correo + ", usuario="
				+ usuario + ", contrasena=" + contrasena + "]";
	}
	
	public String[] getArrayDatos() {
		String[] miArray = {
				cedula + "", nombre, correo, usuario, contrasena
		};
		return miArray;
	}
}