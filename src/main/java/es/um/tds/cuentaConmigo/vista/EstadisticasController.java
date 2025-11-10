package es.um.tds.cuentaConmigo.vista;

import java.io.IOException;
import es.um.tds.App;

import javafx.fxml.FXML;

public class EstadisticasController {

    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void switchToCuentasCompartidas() throws IOException {
        App.setRoot("cuentasComp");
    }
}