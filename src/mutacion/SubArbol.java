package mutacion;

import java.util.Random;

import model.element;
import poblacion.poblacion;

public class SubArbol extends mutacion {

	@Override
	public void mutar(poblacion poblacion, double probMutacion) {
		for(int i = 0; i < poblacion.getSize(); i++) {
			double prob = Math.random()%1;
			
			if(prob < probMutacion){
				Random rand = new Random();
				int r = rand.nextInt(poblacion.getIndividuo(i).getSizeCromosoma());
				
				element raiz = poblacion.getIndividuo(i).getCromosoma().getFenotipoList().get(r);
			}
		}
	}

}
