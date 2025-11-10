module es.um.tds.cuentaConmigo {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens es.um.tds to javafx.fxml;
    opens es.um.tds.cuentaConmigo.vista;
    exports es.um.tds;
    exports es.um.tds.cuentaConmigo.vista;
}
