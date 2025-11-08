package es.um.tds.cuentaConmigo;

import java.time.LocalDate;

public class Gasto {
	
	//propiedades
	private final int id;
	private static int contador = 0;
	private String descripcion;
	private double cantidad;
	private LocalDate fecha;
	private Categoria categoria;
	
	//constructor
	public Gasto(String descripcion, Categoria categoria, double cantidad) {
		this.id = contador++;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.cantidad = cantidad;
		this.fecha = LocalDate.now();
	}

	//getters y setters
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCantidad() {
		return cantidad;
	}
	
	public int getId() {
		return this.id;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	

}
