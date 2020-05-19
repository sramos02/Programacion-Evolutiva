package seleccion;

import model.algoritmo;
import poblacion.poblacion;

public class algoritmoTorneoDeter extends algoritmoTorneo{

	public algoritmoTorneoDeter() {
		super("torneoDeter");
	}

	public poblacion ini(poblacion p, algoritmo f) {
		iniSeleccionados(p);
		seleccionar(p, f);
		getSeleccionados().iniBest();
		getSeleccionados().setInit(p.getAlgInit());
		return getSeleccionados();
	}

}
