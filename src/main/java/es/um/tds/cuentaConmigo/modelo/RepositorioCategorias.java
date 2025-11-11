package es.um.tds.cuentaConmigo.modelo;

import java.util.LinkedList;
import java.util.List;

public class RepositorioCategorias {
	
	private static List<Categoria> categorias = new LinkedList<>();
	
	public static void guardarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
	
	public static List<Categoria> getCategorias() {
        return new LinkedList<>(categorias);
    }

}
