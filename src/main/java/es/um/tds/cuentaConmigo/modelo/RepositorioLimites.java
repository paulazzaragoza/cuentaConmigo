package es.um.tds.cuentaConmigo.modelo;

import java.util.LinkedList;
import java.util.List;

public class RepositorioLimites {
	
private static List<Limite> limites = new LinkedList<>();
	
	public static void guardarLimite(Limite limite) {
        limites.add(limite);
    }
	
	public static List<Limite> getLimites() {
        return new LinkedList<>(limites);
    }


}
