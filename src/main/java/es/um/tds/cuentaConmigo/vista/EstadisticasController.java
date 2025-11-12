package es.um.tds.cuentaConmigo.vista;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import es.um.tds.App;
import es.um.tds.cuentaConmigo.controlador.ControladorDeModelo;
import es.um.tds.cuentaConmigo.modelo.Gasto;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EstadisticasController {
	
	private ControladorDeModelo controlador = new ControladorDeModelo();
	
	@FXML
	private TableView<Gasto> tablaGastos;
	
	@FXML
	private TableColumn<Gasto, Integer> colId;
	
	@FXML
	private TableColumn<Gasto, String> colDesc;
	
	@FXML
	private TableColumn<Gasto, String> colCat;
	
	@FXML
	private TableColumn<Gasto, String> colFecha;
	
	@FXML
	private TableColumn<Gasto, Double> colCant;
	
	@FXML 
	private PieChart pieGastos;
	
	@FXML
	private BarChart<String, Number> graficoGastos;
	
	@FXML
	private CategoryAxis ejeX;
	
	@FXML
	private NumberAxis ejeY;
	
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void switchToCuentasCompartidas() throws IOException {
        App.setRoot("cuentasComp");
    }
    
    @FXML
    public void initialize() {
    	inicializarTabla();
    	inicializarPie();
    	inicializarGrafico();
    }
    
    public void inicializarTabla() {
    	
    	colId.setCellValueFactory(cellData ->
        new SimpleObjectProperty<>(cellData.getValue().getId())
    							);
    	
    	colDesc.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getDescripcion())
    								);
    	
    	colCat.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getCategoria().getTipo())
    								);
    	
    	colFecha.setCellValueFactory(cellData ->
        new SimpleStringProperty(cellData.getValue().getFecha().toString())
    								);
    	
    	colCant.setCellValueFactory(cellData ->
        new SimpleObjectProperty<>(cellData.getValue().getCantidad())
    								);

    	List<Gasto> gastos = controlador.getGastos();

    	ObservableList<Gasto> gastosObservables = FXCollections.observableArrayList(gastos);
    	
    	tablaGastos.setItems(gastosObservables);
    }
    
    public void inicializarPie(){
    	List<Gasto> gastos = controlador.getGastos();
    	
    	Map<String, Double> sumaPorCategoria = new HashMap<>();
    	
    	for(Gasto g : gastos) {
    		String tipo = g.getCategoria().getTipo();
    		sumaPorCategoria.put(tipo, sumaPorCategoria.getOrDefault(tipo, 0.0)+g.getCantidad());
    	}
    	
    	ObservableList<PieChart.Data> datosPie = FXCollections.observableArrayList();
    	
    	for (Map.Entry<String, Double> entrada : sumaPorCategoria.entrySet()) {
    	    datosPie.add(new PieChart.Data(entrada.getKey(), entrada.getValue()));
    	}
    	
    	pieGastos.setData(datosPie);
    	
    	
    	//Mostrar valores absolutos
//    	pieGastos.getData().forEach(data ->
//        data.nameProperty().bind(
//            Bindings.concat(
//                data.getName(), " (", String.format("%.1f", data.getPieValue()), "€)"
//            		)
//        		)
//    	);
    	
    	
    	
    	//Mostrar porcentajes
//    	for (PieChart.Data data : pieGastos.getData()) {
//    	    // Calcular el porcentaje sobre el total
//    	    double total = datosPie.stream()
//    	            .mapToDouble(PieChart.Data::getPieValue)
//    	            .sum();
//    	    double porcentaje = (data.getPieValue() / total) * 100;
//
//    	    // Crear etiqueta personalizada
//    	    data.nameProperty().bind(
//    	        Bindings.concat(
//    	            data.getName(), " (", String.format("%.1f%%", porcentaje), ")"
//    	        )
//    	    );
//    	}

    	
    }
    
    public void inicializarGrafico() {
    	
    	List<Gasto> gastos = controlador.getGastos();
    	
    	Map<String, Double> sumaPorCategoria = new HashMap<>();
    	
    	for(Gasto g : gastos) {
    		String tipo = g.getCategoria().getTipo();
    		sumaPorCategoria.put(tipo, sumaPorCategoria.getOrDefault(tipo, 0.0)+g.getCantidad());
    	}
    	
    	XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Gastos por categoría");
        
        for (Map.Entry<String, Double> entrada : sumaPorCategoria.entrySet()) {
            serie.getData().add(new XYChart.Data<>(entrada.getKey(), entrada.getValue()));
        }
        
        graficoGastos.getData().clear();
        graficoGastos.getData().add(serie);
        
        ejeX.setCategories(FXCollections.observableArrayList(sumaPorCategoria.keySet()));
        
        double maxBarScale = 0.2; // 20% del ancho disponible

     // Recorrer todas las barras y aplicar la escala horizontal
     graficoGastos.lookupAll(".chart-bar").forEach(barra -> barra.setScaleX(maxBarScale));
    }
}