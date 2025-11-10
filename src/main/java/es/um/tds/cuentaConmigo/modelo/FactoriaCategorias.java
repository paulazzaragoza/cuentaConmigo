package es.um.tds.cuentaConmigo.modelo;

public class FactoriaCategorias {
	
	public static Categoria crearCategoria(String nombre) {
		return new Categoria(nombre);
	}

}
