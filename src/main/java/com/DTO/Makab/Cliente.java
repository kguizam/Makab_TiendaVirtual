package com.DTO.Makab;
/**
 * Objeto para transpasar los datos entre métodos
 */
public class Cliente extends Persona{
	private String direccion;
	private String telefono;
	private String correo;
	 
	public Cliente() {
	}
	 
	public Cliente(int id, long cedula, String nombre, String direccion, String telefono, String correo) {
		super(id, cedula, nombre);
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}

	public String getCampo1() {
		return direccion;
	}

	public void setCampo1(String direccion) {
		this.direccion = direccion;
	}

	public String getCampo2() {
		return telefono;
	}

	public void setCampo2(String telefono) {
		this.telefono = telefono;
	}

	public String getCampo3() {
		return correo;
	}

	public void setCampo3(String correo) {
		this.correo = correo;
	}

	protected String[] getNombresCampos() {
		return new String[] {
			"Cédula", "Nombre", "Dirección", "Teléfono", "Correo"
		};
	}
	
	public String[] getArrayCampos() {
		return new String[] {
			identificador + "", nombre, direccion, telefono, correo
		};
	}
}