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
				List<element>  fenotipoMutado = poblacion.getIndividuo(i).getCromosoma().getFenotipoList();
				int r = rand.nextInt(fenotipoMutado.size());

				//Se selecciona al azar un símbolo terminal dentro del individuo,
				//se sustituye por otro diferente del conjunto de símbolos terminales posibles				
				while(fenotipoMutado.get(r).getTipo() == "funcion") 
			    	r = rand.nextInt(fenotipoMutado.size());
				
				
				element nuevo = new terminal(poblacion.getFuncion().getNumVariables());
				terminal aux2 =  (terminal) nuevo;
				nuevo.setTipo("terminal");
				nuevo.setValor(aux2.nuevoTerminal(poblacion.getFuncion().getNumVariables()));
				
				while(nuevo.getValor() == poblacion.getIndividuo(i).getCromosoma().getFenotipoList().get(r).getValor())
					nuevo.setValor(aux2.nuevoTerminal(poblacion.getFuncion().getNumVariables()));	
				
				//Cambiamos el genotipo
				poblacion.getIndividuo(i).getCromosoma().getGenotipo().setNodoArbol(nuevo, r);
				
				//Cambiamos el fenotipo
				fenotipoMutado.set(r, nuevo);
				poblacion.getIndividuo(i).getCromosoma().setFenotipo(fenotipoMutado);
				System.out.println();
			}
		}
	}
}