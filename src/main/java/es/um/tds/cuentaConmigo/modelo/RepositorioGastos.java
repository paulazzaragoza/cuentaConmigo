package es.um.tds.cuentaConmigo.modelo;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorioGastos {

	private static List<Gasto> gastos = new LinkedList<>();
	
	public static void guardarGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public static List<Gasto> getGastos() {
        return new LinkedList<>(gastos);
    }

    public static Gasto buscarGastoPorId(int id) {
        return gastos.stream()
            .filter(g -> g.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public static void eliminarGasto(int id) {
        gastos.removeIf(g -> g.getId() == id);
    }
    
    public static List<Gasto> filtrarGastosCategoria(List<Categoria> categorias){
    	return gastos.stream()
                .filter(g -> categorias.contains(g.getCategoria()))
                .collect(Collectors.toList());
    }
    
    public static List<Gasto> filtrarGastosMeses(List<String> meses){
    	return gastos.stream()
                .filter(g -> meses.contains(g.getFecha().getMonth().toString()))
                .collect(Collectors.toList());
    }
    
    public static List<Gasto> filtrarGastosMesesCategorias(List<String> meses, List<Categoria> categorias){
    	return gastos.stream()
                .filter(g -> meses.contains(g.getFecha().getMonth().toString().toLowerCase()) && categorias.contains(g.getCategoria()))
                .collect(Collectors.toList());
    }
    
    
    
    
}
