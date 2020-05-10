package init;

import model.arbol;

public class RampedHalf extends initMethod{

	public RampedHalf(initMethod old) {
		super(old);
	}

	public RampedHalf() {
		super("ramped");
	}

	@Override
	public arbol crearArbol(int profundidad, int numVars) {
		arbol ret = null;
		int tamGrupo = numVars/profundidad;
		
		for(int i = 0; i < profundidad; i++) {
			
		}
		
		return ret;
	}

}
