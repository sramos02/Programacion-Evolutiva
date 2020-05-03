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
				List<element>  fenotipoMutado = poblacion.getIndividuo(i).getCromosoma().getFenotipoList();


				//Se selecciona al azar un símbolo terminal dentro del individuo,
				//se sustituye por otro diferente del conjunto de símbolos terminales posibles				
				while(fenotipoMutado.get(r).getTipo() == "funcion") 
			    	r = rand.nextInt(poblacion.getIndividuo(i).getSizeCromosoma());
				
				
				element nuevo = new terminal(poblacion.getFuncion().getNumVariables());
				terminal aux2 =  (terminal) nuevo;
				nuevo.setTipo("terminal");
				nuevo.setValor(aux2.nuevoTerminal(poblacion.getFuncion().getNumVariables()));
				
				while(nuevo.getValor() == poblacion.getIndividuo(i).getCromosoma().getFenotipoList().get(r).getValor())
					nuevo.setValor(aux2.nuevoTerminal(poblacion.getFuncion().getNumVariables()));	
				
				//Cambiamos el genotipo
				poblacion.getIndividuo(i).getCromosoma().getGenotipo().setNodoArbol(nuevo, r);
				
				//Cambiamos el fenotipo
				poblacion.getIndividuo(i).getCromosoma().getGenotipo().representa(fenotipoMutado);
				poblacion.getIndividuo(i).getCromosoma().setFenotipo(fenotipoMutado);
			}
		}
	}
}