package es.um.tds.cuentaConmigo.modelo;

public class Categoria {
	
	//propiedades
	private String tipo;
	
	//constructor
	public Categoria(String nombre) {
		tipo = nombre;
	}

	//getters y setters
	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return tipo;
	}
	
}
