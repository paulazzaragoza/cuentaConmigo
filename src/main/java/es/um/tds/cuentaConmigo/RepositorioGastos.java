package es.um.tds.cuentaConmigo;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorioGastos {

	private List<Gasto> gastos = new LinkedList<>();
	
	public void guardarGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public List<Gasto> getGastos() {
        return new LinkedList<>(gastos);
    }

    public Gasto buscarGastoPorId(int id) {
        return gastos.stream()
            .filter(g -> g.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public void eliminarGasto(int id) {
        gastos.removeIf(g -> g.getId() == id);
    }
    
    public List<Gasto> filtrarGastosCategoria(List<Categoria> categorias){
    	return gastos.stream()
                .filter(g -> categorias.contains(g.getCategoria()))
                .collect(Collectors.toList());
    }
    
    public List<Gasto> filtrarGastosMeses(List<String> meses){
    	return gastos.stream()
                .filter(g -> meses.contains(g.getFecha().getMonth().toString().toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Gasto> filtrarGastosMesesCategorias(List<String> meses, List<Categoria> categorias){
    	return gastos.stream()
                .filter(g -> meses.contains(g.getFecha().getMonth().toString().toLowerCase()) && categorias.contains(g.getCategoria()))
                .collect(Collectors.toList());
    }
    
    
    
    
}
