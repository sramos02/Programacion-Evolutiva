package mutacion;

import java.util.List;
import java.util.Random;

import model.element;
import model.terminal;
import poblacion.poblacion;

public class mutTerminal extends mutacion {

	@Override
	public void mutar(poblacion poblacion, double probMutacion) {
		
		for(int i = 0; i < poblacion.getSize(); i++) {
			double prob = Math.random()%1;
			
			if(prob < probMutacion){
				Random rand = new Random();
				int r = rand.nextInt(poblacion.getIndividuo(i).getSizeCromosoma());
				List<element> aux = poblacion.getIndividuo(i).getCromosoma().getFenotipoList();
				
				//Fuerza a encontrar un terminal que mutar una vez decide que un individuo muta
				while(aux.get(r).getTipo() == "funcion") {
			    	r = rand.nextInt(poblacion.getIndividuo(i).getSizeCromosoma());
				}
			
				element nuevo = new terminal(poblacion.getFuncion().getNumVariables());
				terminal aux2 =  (terminal) nuevo;
				
				nuevo.setTipo("terminal");
				nuevo.setValor(aux2.nuevoTerminal(poblacion.getFuncion().getNumVariables()));
				
				aux.set(r, nuevo);
			}
		}
	}
}