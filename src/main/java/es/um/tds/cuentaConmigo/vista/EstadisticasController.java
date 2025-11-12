package es.um.tds.cuentaConmigo.vista;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import es.um.tds.App;
import es.um.tds.cuentaConmigo.controlador.ControladorDeModelo;
import es.um.tds.cuentaConmigo.modelo.Gasto;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
}