package es.um.tds.cuentaConmigo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class HomeController {

	private ControladorDeModelo controlador = new ControladorDeModelo();

    @FXML
    private ChoiceBox<String> cbTipoLimite;
    
    @FXML
    private ChoiceBox<String> cbCategoria1;
    
    @FXML
    private ChoiceBox<String> cbCategoria2;


    @FXML
    private void switchToEstadisticas() throws IOException {
        App.setRoot("estadisticas");
    }
    
    @FXML
    private void switchToCuentasCompartidas() throws IOException {
        App.setRoot("cuentasComp");
    }
    
    
    @FXML
    public void initialize() {
        inicializarChoiceBoxes();
    }

    private void inicializarChoiceBoxes() {

        // ChoiceBox Categorías 
        //List<Categoria> categorias = RepositorioCategorias.getCategorias(); //cuando tengamos la persistencia
    	cbCategoria1.getItems().addAll("Transporte", "Alimentación", "Entretenimiento");
    	cbCategoria1.getSelectionModel().selectFirst(); // seleccionar la primera categoría
        //cbCategorias.getItems().addAll(categorias);
    	cbCategoria2.getItems().addAll( "Sin categoría", "Transporte", "Alimentación", "Entretenimiento");
        cbCategoria2.getSelectionModel().selectFirst(); // seleccionar la primera categoría

        // Tipo de límite
        cbTipoLimite.getItems().addAll("Mensual", "Semanal");
        cbTipoLimite.getSelectionModel().selectFirst(); // seleccionar "Mensual" por defecto
    }
    
    @FXML
    private TextField cantidadGasto;

    @FXML
    private TextArea descripcionGasto;

    @FXML
    private Label errorLabel;

    @FXML
    private Button anadirGastoBtn;

    @FXML
    private void manejadorAnadirGasto() {
        errorLabel.setText(""); 
        String cantidadTexto = cantidadGasto.getText().trim();
        String descripcionTexto = descripcionGasto.getText().trim();

        //Comprobación campos vacíos
        if (cantidadTexto.isEmpty() || descripcionTexto.isEmpty()) {
            errorLabel.setText("Debes rellenar todos los campos.");
            return;
        }

        //Validar campos
        String cantidadNormalizada = cantidadTexto.replace(",", ".");
        double cantidad;
        try {
            cantidad = Double.parseDouble(cantidadNormalizada);
        } catch (NumberFormatException e) {
            errorLabel.setText("La cantidad debe ser un número válido.");
            return;
        }

        //Cantidad mayor que cero
        if (cantidad <= 0) {
            errorLabel.setText("La cantidad debe ser mayor que cero.");
            return;
        }
        
        String categoria = cbCategoria1.getValue();

        // Si todo está bien añadir gasto y guardarlo
        //System.out.println("Gasto válido: " + cantidad + " | " + descripcionTexto + " | " + categoria);
        controlador.addGasto(descripcionTexto, categoria, cantidad);
        
        
        //Notificar que el gasto se ha añadido
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gasto añadido");
        alert.setHeaderText(null);
        alert.setContentText("El gasto se ha añadido correctamente.");
        alert.show();

        // limpiar campos
        cantidadGasto.clear();
        descripcionGasto.clear();
    }
    
    @FXML
    private Button addLimiteBtn;
    
    @FXML
    private TextField cantidadLimite;
    
    @FXML
    private Label errorLimite;
    
    public void manejadorAddLimite() {
    	
    	errorLimite.setText("");
    	String cantidadTexto = cantidadLimite.getText().trim();
    	
    	//Comprobación campos vacíos
        if (cantidadTexto.isEmpty()) {
            errorLimite.setText("Debes rellenar todos los campos.");
            return;
        }

        //Validar campos
        String cantidadNormalizada = cantidadTexto.replace(",", ".");
        double cantidad;
        try {
            cantidad = Double.parseDouble(cantidadNormalizada);
        } catch (NumberFormatException e) {
            errorLimite.setText("La cantidad debe ser un número válido.");
            return;
        }

        //Cantidad mayor que cero
        if (cantidad <= 0) {
            errorLimite.setText("La cantidad debe ser mayor que cero.");
            return;
        }
        
        String categoria = cbCategoria2.getValue();
        String tipo = cbTipoLimite.getValue();
        
        controlador.addLimite(cantidad, tipo, categoria);
    	
    	
    }
    
    @FXML
    private VBox VBoxCategorias;
    
    @FXML
    private VBox VBoxMeses;
    
    @FXML
    private Button filtrarBtn;
    
    @FXML
    private Label errorFiltrar;
    
    
    public void filtrarGastos() {
    	
    	errorFiltrar.setText("");
    	
    	List<String> listaCategorias = new LinkedList<>();
    	List<String> listaMeses = new LinkedList<>();
    	
    	for (Node n : VBoxCategorias.getChildren()) {
    	    if (n instanceof CheckBox) {
    	        CheckBox cb = (CheckBox) n;
    	        if (cb.isSelected()) {
    	            listaCategorias.add(cb.getText());
    	        }
    	    }
    	}
    	
    	for (Node n : VBoxMeses.getChildren()) {
    	    if (n instanceof CheckBox) {
    	        CheckBox cb = (CheckBox) n;
    	        if (cb.isSelected()) {
    	            listaMeses.add(cb.getText());
    	        }
    	    }
    	}
    	
    	if(listaMeses.isEmpty() && listaCategorias.isEmpty()) {
    		errorFiltrar.setText("No hay ningún filtro aplicado");
    	} else {
    		List<Gasto> gastosFiltrados = controlador.filtrarGastos(listaCategorias, listaMeses);
    		int tamano = gastosFiltrados.size();
    		System.out.println("Se ha obtenido la lista de tamaño " + tamano);
    	}
    	
    }
    
    
}
