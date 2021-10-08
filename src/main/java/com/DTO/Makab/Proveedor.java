package com.DTO.Makab;
/**
 * Objeto para transpasar los datos entre métodos
 */
public class Proveedor extends Persona {
	 private String direccion;
	 private String telefono;
	 private String ciudad;
	 
	 public Proveedor() {
	 }
	 
	public Proveedor(int id, long cedula,String nombre, String direccion, String telefono, String ciudad) {
		super(id, cedula, nombre);
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
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
		return ciudad;
	}

	public void setCampo3(String ciudad) {
		this.ciudad = ciudad;
	}

	protected String[] getNombresCampos() {
		return new String[] {
			"NIT", "Nombre", "Dirección", "Teléfono", "Ciudad"
		};
	}
	
	public String[] getArrayCampos() {
		return new String[] {
			identificador + "", nombre, direccion, telefono, ciudad
		};
	}
}