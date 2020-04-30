package mutacion;

import java.util.List;
import java.util.Random;

import model.element;
import model.funcion;
import poblacion.poblacion;

public class mutFuncional extends mutacion{

	@Override
	public void mutar(poblacion poblacion, double probMutacion) {
		
		for(int i = 0; i < poblacion.getSize(); i++) {
			double prob = Math.random()%1;
			
			if(prob < probMutacion){
				Random rand = new Random();
				int r = rand.nextInt(poblacion.getIndividuo(i).getSizeCromosoma());
				List<element> aux = poblacion.getIndividuo(i).getCromosoma().getFenotipoList();
				
				while(aux.get(r).getTipo() == "terminal") {
			    	r = rand.nextInt(poblacion.getIndividuo(i).getSizeCromosoma());
				}
			
				element nuevo = new funcion(poblacion.getUseIfs());
				funcion aux2 = (funcion) nuevo;
				nuevo.setTipo("funcion");
				nuevo.setValor(aux2.nuevaFuncion());
				
				aux.set(r, nuevo);
			}
		}
	}
}