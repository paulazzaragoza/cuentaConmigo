package es.um.tds.cuentaConmigo.modelo;

public class Limite {

	//propiedades
	private double cantidad;
	private TipoLimite tipo;
	private Categoria categoria = null;
	
	//constructores
	public Limite(double cantidad, TipoLimite tipo) {
		this.cantidad = cantidad;
		this.tipo = tipo;
	}
	
	public Limite(double cantidad, TipoLimite tipo, Categoria categoria) {
		this(cantidad, tipo);
		this.categoria = categoria;
	}
	
	//getters y setters

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	//métodos
	
	//método para obtener el texto de la notificación cuando un límite se haya superado
	public String getTextoNotificacion() {
		if(this.categoria == null) {
			return "Se ha superado el límite " + this.tipo + " de " + this.cantidad + " €";
		} else {
			return "Se ha superado el límite " + this.tipo + " de " + this.cantidad + " € de la categoría " + this.categoria;
		}
		
	}
	
}
