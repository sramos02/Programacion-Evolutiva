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
				poblacion.getIndividuo(i).calcularFenotipo();
				Random rand = new Random();
				List<element>  fenotipoMutado = poblacion.getIndividuo(i).getCromosoma().getFenotipoList();
				int r = rand.nextInt(fenotipoMutado.size());

				//Se selecciona al azar un s�mbolo terminal dentro del individuo,
				//se sustituye por otro diferente del conjunto de s�mbolos terminales posibles				
				while(fenotipoMutado.get(r).getTipo().equalsIgnoreCase("funcion")) 
			    	r = rand.nextInt(fenotipoMutado.size());
				
				element nuevo = new terminal(poblacion.getFuncion().getNumVariables());
				terminal aux2 =  (terminal) nuevo;

				while(nuevo.getValor().equalsIgnoreCase(poblacion.getIndividuo(i).getCromosoma().getFenotipoList().get(r).getValor())) {
					nuevo.setValor(aux2.nuevoTerminal(poblacion.getFuncion().getNumVariables()));
					aux2 =  (terminal) nuevo;
				}
				poblacion.getIndividuo(i).getCromosoma().getGenotipo().setNodoArbol(nuevo, r);
			}
			//Calculamos el nuevo Fitness
			poblacion.getIndividuo(i).calcularFenotipo();
			poblacion.getIndividuo(i).calcularFitness();
		}
	}
	
}