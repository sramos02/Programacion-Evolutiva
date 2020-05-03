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
				int numVars = poblacion.getIndividuo(i).getCromosoma().getNumVars();
				initMethod init = poblacion.getIndividuo(i).getCromosoma().getInit();
				genes nuevo = new genes(init, numVars);
				poblacion.getIndividuo(i).setGen(nuevo);
			}
		}
	}

}
