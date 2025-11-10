package es.um.tds.cuentaConmigo.modelo;

public enum TipoLimite {
	
	SEMANAL("semanal"), MENSUAL("mensual"), SEMANAL_CATEGORIA("semanal"), MENSUAL_CATEGORIA("mensual");
	
	 private final String descripcion;

    TipoLimite(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
