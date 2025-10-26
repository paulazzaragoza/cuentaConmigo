package es.um.tds.cuentaConmigo;

import java.io.IOException;

import javafx.fxml.FXML;

public class cuentasCompController {

	@FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }
	
	@FXML
    private void switchToEstadisticas() throws IOException {
        App.setRoot("estadisticas");
    }
}
