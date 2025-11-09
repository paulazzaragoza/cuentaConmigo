package es.um.tds.cuentaConmigo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ControladorDeModelo {
	
	//private RepositorioGastos repoGastos;
	//private RepositorioLimites repoLimites;
	
	//Método para añadir gasto
	public void addGasto(String descripcion, String nombreCategoria, double cantidad) {
		
		Categoria categoria = FactoriaCategorias.crearCategoria(nombreCategoria);
		Gasto gasto = FactoriaGastos.crearGasto(descripcion, categoria, cantidad);
		RepositorioGastos.guardarGasto(gasto);
	}
	
	//Método para editar gasto
	
	//Método para borrar gasto
	
	//Método para crear nuevas categorias
	
	//Filtrar 
	public List<Gasto> filtrarGastos(List<String> listaCategorias, List<String> listaMeses) {
		if(!listaCategorias.isEmpty()) {
			List<Categoria> listaCat = listaCategorias.stream().map(nombre -> FactoriaCategorias.crearCategoria(nombre)).collect(Collectors.toList());
			if(listaMeses.isEmpty()) {
				return RepositorioGastos.filtrarGastosCategoria(listaCat);
			} else {
				return RepositorioGastos.filtrarGastosMesesCategorias(listaMeses, listaCat);
			}
		} else {
			return RepositorioGastos.filtrarGastosMeses(listaMeses);
		}
		
		
	}
	
	//Definir un limite
	public void addLimite(double cantidad, String tipo, String nombreCategoria) {
		TipoLimite tipoLim;
		if(nombreCategoria.equals("Sin categoría")) {
			if(tipo.equals("Mensual")) {
				tipoLim = TipoLimite.MENSUAL;
			} else {
				tipoLim = TipoLimite.SEMANAL;
			}
		}else {
			if(tipo.equals("Mensual")) {
				tipoLim = TipoLimite.MENSUAL_CATEGORIA;
			} else {
				tipoLim = TipoLimite.SEMANAL_CATEGORIA;
			}
		}
		
		Categoria categoria = FactoriaCategorias.crearCategoria(nombreCategoria);
		Limite limite = FactoriaLimites.crearLimite(cantidad, tipoLim, categoria);
		RepositorioLimites.guardarLimite(limite);
		System.out.println("Limite nuevo: " + cantidad + " | " + tipoLim + " | " + categoria);
	}
	
	
	//Notificar de un limite
		//y añadirlo al historial
	
	//Creacion de cuenta compartida
	
	//Repartir los gastos
	
	//Importacion de gastos
		//XML
		//JSON

}
