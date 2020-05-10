package mutacion;

import genetica.genes;
import init.initMethod;
import poblacion.poblacion;

public class Arbol extends mutacion {

	@Override
	public void mutar(poblacion poblacion, double probMutacion) {
		for(int i = 0; i < poblacion.getSize(); i++) {
			double prob = Math.random()%1;
			if(prob < probMutacion){
				poblacion.getIndividuo(i).getCromosoma().getGenotipo().mutarNodo(poblacion.getAlgInit(), poblacion.getNumVariables());
			}
		}
	}

}
