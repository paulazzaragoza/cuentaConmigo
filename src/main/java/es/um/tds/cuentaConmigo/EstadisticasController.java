package es.um.tds.cuentaConmigo;

import java.io.IOException;
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