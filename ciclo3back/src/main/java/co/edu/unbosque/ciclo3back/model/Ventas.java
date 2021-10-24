package co.edu.unbosque.ciclo3back.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ventas {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo_venta;
	private long  cedula_cliente;
	private long cedula_usuario;
	private long iva_venta;
	private long total_venta;
	private long valor_venta;
	
	public long getCodigo_venta() {
		return codigo_venta;
	}
	public void setCodigo_venta(long codigo_venta) {
		this.codigo_venta = codigo_venta;
	}
	public long getCedula_cliente() {
		return cedula_cliente;
	}
	public void setCedula_cliente(long cedula_cliente) {
		this.cedula_cliente = cedula_cliente;
	}
	public long getCedula_usuario() {
		return cedula_usuario;
	}
	public void setCedula_usuario(long cedula_usuario) {
		this.cedula_usuario = cedula_usuario;
	}
	public long getIva_venta() {
		return iva_venta;
	}
	public void setIva_venta(long iva_venta) {
		this.iva_venta = iva_venta;
	}
	public long getTotal_venta() {
		return total_venta;
	}
	public void setTotal_venta(long total_venta) {
		this.total_venta = total_venta;
	}
	public long getValor_venta() {
		return valor_venta;
	}
	public void setValor_venta(long valor_venta) {
		this.valor_venta = valor_venta;
	}
	

}
