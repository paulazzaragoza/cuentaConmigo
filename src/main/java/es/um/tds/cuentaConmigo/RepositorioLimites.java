package es.um.tds.cuentaConmigo;

import java.util.LinkedList;
import java.util.List;

public class RepositorioLimites {
	
private List<Limite> limites = new LinkedList<>();
	
	public void guardarLimite(Limite limite) {
        limites.add(limite);
    }
	
	public List<Limite> getLimites() {
        return new LinkedList<>(limites);
    }


}
