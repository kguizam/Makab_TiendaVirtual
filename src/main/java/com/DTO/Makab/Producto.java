package com.DTO.Makab;

public class Producto {
	long codigo;
	String nombre;
	long nitProveedor;
	double precioCompra;
	double precioVenta;
	double iva;
	
	public Producto() {
	}
	
	public Producto(long codigo, String nombre, long nitProveedor, double precioCompra,
			double iva, double precioVenta) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.nitProveedor = nitProveedor;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.iva = iva;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(long nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}
}
