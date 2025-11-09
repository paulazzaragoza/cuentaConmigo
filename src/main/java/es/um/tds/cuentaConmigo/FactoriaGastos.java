package es.um.tds.cuentaConmigo;

public class FactoriaGastos {
	
	public static Gasto crearGasto(String descripcion,Categoria categoria, double cantidad) {
		return new Gasto(descripcion, categoria, cantidad);
	}

}
