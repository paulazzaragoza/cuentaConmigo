package es.um.tds.cuentaConmigo;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void switchToEstadisticas() throws IOException {
        App.setRoot("estadisticas");
    }
    
    @FXML
    private void switchToCuentasCompartidas() throws IOException {
        App.setRoot("cuentasComp");
    }
}
