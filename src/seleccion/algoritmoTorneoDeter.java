package seleccion;
import java.util.Random;

import model.algoritmo;
import poblacion.poblacion;

public class algoritmoTorneoDeter extends algoritmoTorneo{

	public algoritmoTorneoDeter() {
		super("torneoDeter");
		// TODO Auto-generated constructor stub
	}

	public poblacion ini(poblacion p, algoritmo f) {
		iniSeleccionados(p);
		seleccionar(p, f);
		getSeleccionados().iniBest();
		getSeleccionados().setInit(p.getAlgInit());
		return getSeleccionados();
	}

}
