package com.DTO.Makab;

public class Producto {
	long codigo;
	String nombre;
	long nitProveedor;
	double precioCompra;
	double precioVenta;
	double iva;
	
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

	public String getNombre() {
		return nombre;
	}

	public long getNitProveedor() {
		return nitProveedor;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public double getIva() {
		return iva;
	}
}
