package es.um.tds.cuentaConmigo.vista;

import java.io.IOException;
import es.um.tds.App;
import java.util.LinkedList;
import java.util.List;

import es.um.tds.cuentaConmigo.controlador.ControladorDeModelo;
import es.um.tds.cuentaConmigo.modelo.Gasto;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
    private VBox VBoxResultados;

    public void mostrarResultados(List<Gasto> gastosFiltrados) {

        VBoxResultados.getChildren().clear(); // Limpia resultados anteriores

        for (Gasto g : gastosFiltrados) {

            // --- Contenedor principal ---
            HBox fila = new HBox();
            fila.setAlignment(Pos.CENTER_LEFT);
            fila.setSpacing(20);
            fila.getStyleClass().add("resultadoGasto");
            fila.setPadding(new Insets(10, 0, 10, 0));

            // --- GridPane con columnas ---
            GridPane grid = new GridPane();
            grid.setHgap(20);
            grid.setAlignment(Pos.CENTER_LEFT);
            HBox.setMargin(grid, new Insets(0, 0, 0, 10));

            // Columna 1 (fecha): 120px
            ColumnConstraints col1 = new ColumnConstraints(120);

            // Columna 2 (cantidad): 80px
            ColumnConstraints col2 = new ColumnConstraints(60);

            // Columna 3 (categoría): expansiva
            ColumnConstraints col3 = new ColumnConstraints();
            col3.setHgrow(Priority.ALWAYS);

            grid.getColumnConstraints().addAll(col1, col2, col3);

            // --- Labels del gasto ---
            Label fechaLabel = new Label(g.getFecha().toString());
            fechaLabel.setFont(Font.font(14));
            GridPane.setHgrow(fechaLabel, Priority.NEVER);

            Label cantidadLabel = new Label(String.valueOf(g.getCantidad()));
            cantidadLabel.setFont(Font.font(14));
            GridPane.setHgrow(cantidadLabel, Priority.NEVER);

            Label categoriaLabel = new Label(g.getCategoria().getTipo());
            categoriaLabel.setFont(Font.font(14));
            GridPane.setHgrow(categoriaLabel, Priority.ALWAYS); // se expande

            // Añadirlos al grid
            grid.add(fechaLabel, 0, 0);
            grid.add(cantidadLabel, 1, 0);
            grid.add(categoriaLabel, 2, 0);

            // --- Botón editar ---
            Button editarBtn = new Button("Editar");
            editarBtn.setFont(Font.font(14));
            editarBtn.getStyleClass().add("editBtn");

            // Añadir grid + botón a la fila
            fila.getChildren().addAll(grid, editarBtn);

            // Insertar en el VBox principal
            VBoxResultados.getChildren().add(fila);
        }
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
    	        	
    	            
    	            //System.out.println("Se ha obtenido la lista de tamaño " + listaCategorias.size());
    	        }
    	    }
    	}
    	
    	for (Node n : VBoxMeses.getChildren()) {
    	    if (n instanceof CheckBox) {
    	        CheckBox cb = (CheckBox) n;
    	        if (cb.isSelected()) {
    	        	 switch (cb.getText()) {
	    	            case "Enero": listaMeses.add("JANUARY");
	    	            break;
	    	            case "Febrero": listaMeses.add("FEBRUARY");
	    	            break;
	    	            case "Marzo" : listaMeses.add("MARCH");
	    	            break;
	    	            case "Abril" : listaMeses.add("APRIL");
	    	            break;
	    	            case "Mayo" : listaMeses.add("MAY");
	    	            break;
	    	            case "Junio" : listaMeses.add("JUNE");
	    	            break;
	    	            case "Julio" : listaMeses.add("JULY");
	    	            break;
	    	            case "Agosto" : listaMeses.add("AUGUST");
	    	            break;
	    	            case "Septiembre" : listaMeses.add("SEPTEMBER");
	    	            break;
	    	            case "Octubre" : listaMeses.add("OCTOBER");
	    	            break;
	    	            case "Noviembre" : listaMeses.add("NOVEMBER");
	    	            break;
	    	            case "Diciembre" : listaMeses.add("DECEMBER");
	    	            break; // o puedes lanzar excepción
	    	        };
    	            
    	            //System.out.println("Se ha obtenido la lista de tamaño " + listaMeses.size());
    	        }
    	    }
    	}
    	
    	if(listaMeses.isEmpty() && listaCategorias.isEmpty()) {
    		errorFiltrar.setText("No hay ningún filtro aplicado");
    	} else {
    		List<Gasto> gastosFiltrados = controlador.filtrarGastos(listaCategorias, listaMeses);
    		if (gastosFiltrados.size() > 0) {
    			mostrarResultados(gastosFiltrados);
    		}
    		//int tamano = gastosFiltrados.size();
    		//System.out.println("Se ha obtenido la lista de tamaño " + tamano);
    	}
    	
    }
    
    @FXML
    private Button categoriaNuevaBtn;
    
    @FXML
    private TextField categoriaNuevaText;
    
    @FXML
    private Label errorNuevaCategoria;
    
    public void crearNuevaCategoria() {
    	errorNuevaCategoria.setText("");
    	
    	String categoriaTexto = categoriaNuevaText.getText();
    	if(categoriaTexto.isEmpty()) {
    		errorNuevaCategoria.setText("Rellena el campo nueva categoría");
    		return;
    	}
    	
    	controlador.addNuevaCategoria(categoriaTexto);
    	
    	cbCategoria1.getItems().add(categoriaTexto);
    	cbCategoria2.getItems().add(categoriaTexto);
    	
    	CheckBox nuevoCheckBox = new CheckBox(categoriaTexto);
    	nuevoCheckBox.getStyleClass().add("checkbox");
    	
        VBoxCategorias.getChildren().add(nuevoCheckBox);

        categoriaNuevaText.clear();
    	
    }
    
    
    
    
    
}
