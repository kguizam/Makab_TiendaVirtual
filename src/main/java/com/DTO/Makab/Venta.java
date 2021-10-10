package com.DTO.Makab;

public class Venta {
	int codigo;
	long cedulaCliente;
	long cedulaUsuario;
	double valorTotalBruto;
	double valorIva;
	double valorTotalNeto;
	
	protected Venta(int codigo, long cedulaCliente, long cedulaUsuario, double valorTotalBruto, double valorIva,
			double valorTotalNeto) {
		super();
		this.codigo = codigo;
		this.cedulaCliente = cedulaCliente;
		this.cedulaUsuario = cedulaUsuario;
		this.valorTotalBruto = valorTotalBruto;
		this.valorIva = valorIva;
		this.valorTotalNeto = valorTotalNeto;
	}
}
