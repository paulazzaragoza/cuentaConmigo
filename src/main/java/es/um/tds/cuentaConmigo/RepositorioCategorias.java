package es.um.tds.cuentaConmigo;

import java.util.LinkedList;
import java.util.List;

public class RepositorioCategorias {
	
	private List<Categoria> categorias = new LinkedList<>();
	
	public void guardarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
	
	public List<Categoria> getCategorias() {
        return new LinkedList<>(categorias);
    }

}
