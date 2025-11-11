package es.um.tds.cuentaConmigo.modelo;

public class FactoriaLimites {

	public static Limite crearLimite(double cantidad, TipoLimite tipo, Categoria categoria) {
		if(categoria == null) {
			return new Limite(cantidad, tipo);
		}
		return new Limite(cantidad, tipo, categoria);
	}
}
