package es.um.tds.cuentaConmigo;

public class FactoriaCategorias {
	
	public Categoria crearCategoria(String nombre) {
		return new Categoria(nombre);
	}

}
