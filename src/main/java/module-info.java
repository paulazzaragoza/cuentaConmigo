module es.um.tds.cuentaConmigo {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens es.um.tds.cuentaConmigo to javafx.fxml;
    exports es.um.tds.cuentaConmigo;
}
