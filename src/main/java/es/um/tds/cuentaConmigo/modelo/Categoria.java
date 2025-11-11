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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria c = (Categoria) o;
        return tipo.equalsIgnoreCase(c.tipo);
    }
	
}
