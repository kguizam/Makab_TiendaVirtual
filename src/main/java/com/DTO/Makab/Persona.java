package com.DTO.Makab;

public abstract class Persona {
	protected int numeroID;		// Identificador único en la base de datos
	protected long identificador; // Cédula o NIT
	protected String nombre;		// Empresa o individuo
	
	protected Persona() {
		
	}
	protected Persona(int numeroID, long identificador, String nombre) {
		this.numeroID = numeroID;
		this.identificador = identificador;
		this.nombre = nombre;
	}
	
	public int getNumberID() {
		return numeroID;
	}

	public void setNumberID(int id) {
		numeroID = id;
	}
	
	public long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(long cedula) {
		identificador = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract String getCampo1();
	public abstract String getCampo2();
	public abstract String getCampo3();
	
	@Override
	public String toString() {
		String toString = "";
		String[] nombres = this.getNombresCampos();
		String[] campos  = this.getArrayCampos();
		
		for (int i = 1; i < 5; i++) {
			toString += nombres[i] + ": " + campos[i] + "; ";
		}
		return toString;
	}
	
	protected abstract String[] getArrayCampos();
	protected abstract String[] getNombresCampos();
}
