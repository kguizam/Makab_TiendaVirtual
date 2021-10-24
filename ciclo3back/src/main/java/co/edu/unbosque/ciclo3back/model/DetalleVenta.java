package co.edu.unbosque.ciclo3back.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class DetalleVenta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo_detalle_venta;
	
	private int cantidad_producto;
	private long codigo_producto;
	private long codigo_venta;
	private long valor_total;
	private long valor_venta;
	private long valor_iva;
	
	public int getCodigo_detalle_venta() {
		return codigo_detalle_venta;
	}
	public void setCodigo_detalle_venta(int codigo_detalle_venta) {
		this.codigo_detalle_venta = codigo_detalle_venta;
	}
	public int getCantidad_producto() {
		return cantidad_producto;
	}
	public void setCantidad_producto(int cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}
	public long getCodigo_producto() {
		return codigo_producto;
	}
	public void setCodigo_producto(long codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	public long getCodigo_venta() {
		return codigo_venta;
	}
	public void setCodigo_venta(long codigo_venta) {
		this.codigo_venta = codigo_venta;
	}
	public long getValor_total() {
		return valor_total;
	}
	public void setValor_total(long valor_total) {
		this.valor_total = valor_total;
	}
	public long getValor_venta() {
		return valor_venta;
	}
	public void setValor_venta(long valor_venta) {
		this.valor_venta = valor_venta;
	}
	public long getValor_iva() {
		return valor_iva;
	}
	public void setValor_iva(long valor_iva) {
		this.valor_iva = valor_iva;
	}

}
