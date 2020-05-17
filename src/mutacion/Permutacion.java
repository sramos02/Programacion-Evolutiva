package mutacion;

import java.util.List;
import java.util.Random;

import model.arbol;
import model.element;
import poblacion.poblacion;

public class Permutacion extends mutacion {

	@Override
	public void mutar(poblacion poblacion, double probMutacion) {
		for(int i = 0; i < poblacion.getSize(); i++) {
			double prob = Math.random()%1;
			
			if(prob < probMutacion){
				Random rand = new Random();
				List<element> fenotipoMutado = poblacion.getIndividuo(i).getCromosoma().getFenotipoList();
				int r = rand.nextInt(fenotipoMutado.size());
				int cont = 0;
				//Escogemos una posicion valida
				while(cont < 10 && (fenotipoMutado.get(r).getTipo().equalsIgnoreCase("terminal") || 
						   fenotipoMutado.get(r).getValor().equalsIgnoreCase("IF") || 
						   fenotipoMutado.get(r).getValor().equalsIgnoreCase("NOT") )) {
			    	r = rand.nextInt(fenotipoMutado.size());
			    	cont++;//Por si solo hay ifs o nots
				}
				if(cont < 10) {
					poblacion.getIndividuo(i).getCromosoma().getGenotipo().permutarHijos(r);
					//Cambiamos el fenotipo
					poblacion.getIndividuo(i).calcularFenotipo();
					poblacion.getIndividuo(i).calcularFitness();
				}
			}
		}
	}

	private arbol generaArbol(List<element> fenotipoMutado, int r) {		
		if(fenotipoMutado.get(r).getTipo().equalsIgnoreCase("terminal")) {
			return new arbol(fenotipoMutado.get(r));
		}
		else {
			if(fenotipoMutado.get(r).getValor().equalsIgnoreCase("NOT")) {
				r++;
				return new arbol(fenotipoMutado.get(r), generaArbol(fenotipoMutado, r));	
			}			
			else if (fenotipoMutado.get(r).getValor().equalsIgnoreCase("AND") || fenotipoMutado.get(r).getValor().equalsIgnoreCase("OR")) {
				r++;
				arbol izq = new arbol(fenotipoMutado.get(r), generaArbol(fenotipoMutado, r));
				r++;
				arbol der = new arbol(fenotipoMutado.get(r), generaArbol(fenotipoMutado, r));
				return new arbol(fenotipoMutado.get(r), izq, der);
			}
			else if (fenotipoMutado.get(r).getValor().equalsIgnoreCase("IF")) {
				arbol izq = new arbol(fenotipoMutado.get(r), generaArbol(fenotipoMutado, r+1));
				arbol der = new arbol(fenotipoMutado.get(r), generaArbol(fenotipoMutado, r+2));
				arbol cen = new arbol(fenotipoMutado.get(r), generaArbol(fenotipoMutado, r+3));
				return new arbol(fenotipoMutado.get(r), izq, der, cen);
			}		
		}
		return null;
	}
}