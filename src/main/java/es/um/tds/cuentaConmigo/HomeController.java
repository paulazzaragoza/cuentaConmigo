package es.um.tds.cuentaConmigo;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
public class HomeController {
	
	@FXML
    private ChoiceBox<String> cbMeses;


    @FXML
    private ChoiceBox<String> cbTipoLimite;


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
        // ChoiceBox Meses del año
        cbMeses.getItems().addAll(
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        );
        cbMeses.getSelectionModel().selectFirst(); // seleccionar Enero por defecto

        // ChoiceBox Categorías desde el repositorio
        //List<Categoria> categorias = RepositorioCategorias.getCategorias(); // suponiendo tu método
        //cbCategorias.getItems().addAll(categorias);
        //cbCategorias.getSelectionModel().selectFirst(); // seleccionar la primera categoría

        // Tipo de límite
        cbTipoLimite.getItems().addAll("Mensual", "Semanal");
        cbTipoLimite.getSelectionModel().selectFirst(); // seleccionar "Mensual" por defecto
    }

    
    
}
