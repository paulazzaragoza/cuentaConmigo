package es.um.tds.cuentaConmigo;

import java.util.HashMap;
import java.util.Map;

public class CuentaCompartida {
	
	//propiedades
	private Map<String, Integer> balanceCuentas;
	//private final int id;
	
	//constructor
	public CuentaCompartida() {
		balanceCuentas = new HashMap<String, Integer>();
		//id = 0;
	}
	
	public void actualizarBalance(String persona, int cantidad) {
		int cantidadActual = balanceCuentas.get(persona);
		balanceCuentas.put(persona, cantidadActual + cantidad);
	}
	
}
