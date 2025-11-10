package es.um.tds.cuentaConmigo.vista;

import java.io.IOException;

import es.um.tds.cuentaConmigo.modelo.Gasto;
import javafx.fxml.*;
import javafx.scene.control.*;

public class EditarGastoController {

	private Gasto gasto;
	
	@FXML
	private TextField cantidadEdit;
	
	@FXML
	private TextField descripcionEdit;
	
	@FXML
	private ChoiceBox<String> categoriaEdit;
	
	@FXML
	private Button guardarBtn;
	
	@FXML
    public void initialize() {
		categoriaEdit.getItems().addAll("Trasnporte", "Alimentación", "Entretenimiento");
		
    }
	
	public void setGasto(Gasto gasto) {
		this.gasto=gasto;
		cantidadEdit.setText(String.valueOf(gasto.getCantidad()));
		descripcionEdit.setText(gasto.getDescripcion());
		categoriaEdit.setValue(gasto.getCategoria().getTipo());
	}
	
//	private void guardarCambios() {
//        // actualizamos el objeto con los nuevos valores
//        gasto.setCantidad(Double.parseDouble(cantidadEdit.getText()));
//        gasto.setDescripcion(descripcionEdit.getText());
//        gasto.setCategoria(categoriaEdit.getValue());
//
//        // cerramos la ventana
//        Stage stage = (Stage) guardarBtn.getScene().getWindow();
//        stage.close();
//    }
	
	
	
}
