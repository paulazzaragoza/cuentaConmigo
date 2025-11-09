package es.um.tds.cuentaConmigo;

public class FactoriaCategorias {
	
	public static Categoria crearCategoria(String nombre) {
		return new Categoria(nombre);
	}

}
